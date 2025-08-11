package utility;

import java.util.Scanner;

public class CounterController {

    public static void main(String[] args) {
        // Create an instance of our counter thread
        Counter counterThread = new Counter();
        counterThread.start(); // Start the thread, which begins counting immediately

        // Instructions for the user
        System.out.println("Counter started. Enter 'stop' to pause, 'start' to resume, or 'exit' to quit.");

        // Use a Scanner to read user commands from the console
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();

                if ("stop".equalsIgnoreCase(command)) {
                    counterThread.pauseCounting();
                    System.out.println("Counting paused.");
                } else if ("start".equalsIgnoreCase(command)) {
                    counterThread.resumeCounting();
                    System.out.println("Counting resumed.");
                } else if ("exit".equalsIgnoreCase(command)) {
                    counterThread.stopCounting();
                    break; // Exit the input loop, which will end the program
                }
            }
        }

        System.out.println("Exiting application.");
    }
}

/**
 * The Counter class runs as a separate thread to count and print numbers.
 * It can be paused, resumed, and stopped safely from another thread.
 */
class Counter extends Thread {
    // 'volatile' ensures that changes to this variable are visible across threads.
    private volatile boolean paused = false;
    private volatile boolean finished = false;
    private int count = 0;
    // An object used as a lock for thread synchronization.
    private final Object lock = new Object();

    @Override
    public void run() {
        while (!finished) {
            try {
                // This is the main logic block for the thread.
                synchronized (lock) {
                    // If 'paused' is true, the thread will wait here until notified.
                    // Using a 'while' loop prevents "spurious wakeups".
                    while (paused) {
                        lock.wait();
                    }
                }

                // If the 'finished' flag was set while waiting, exit the main loop.
                if (finished) {
                    break;
                }

                // Perform the core task: print the count and sleep.
                System.out.println(count++);
                Thread.sleep(100); // Wait for 100 milliseconds

            } catch (InterruptedException e) {
                // If the thread is interrupted, treat it as a signal to stop.
                finished = true;
            }
        }
        System.out.println("Counter thread finished.");
    }

    /**
     * Pauses the counter.
     */
    public void pauseCounting() {
        paused = true;
    }

    /**
     * Resumes the counter.
     */
    public void resumeCounting() {
        // Synchronize on the lock to safely change the flag and notify.
        synchronized (lock) {
            paused = false;
            // Wakes up the single thread that is waiting on this object's monitor.
            lock.notify();
        }
    }

    /**
     * Stops the counter permanently.
     */
    public void stopCounting() {
        finished = true;
        // We must also "resume" the thread in case it is currently paused.
        // Otherwise, it would remain in a wait state forever and never check the
        // 'finished' flag.
        resumeCounting();
    }
}