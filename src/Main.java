import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static class Counter {
        private int count;
        private Lock lock = new ReentrantLock();

        public void increment() {
            lock.lock();
            try {
                this.count++;
            } finally {
                lock.unlock();
            }
        }

        public int getCount() {
            return count;
        }
    }

    public static class UpdateCounterUptoNThread implements Runnable {
        private Counter counter;
        private int n;

        public UpdateCounterUptoNThread(Counter counter, int n) {
            this.counter = counter;
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 0; i < n; i++) {
                counter.increment();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        int n = 10000;
        Thread t1 = new Thread(new UpdateCounterUptoNThread(counter, n));
        Thread t2 = new Thread(new UpdateCounterUptoNThread(counter, n));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Count: " + counter.getCount());
    }
}