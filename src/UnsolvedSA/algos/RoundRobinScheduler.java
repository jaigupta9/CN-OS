package UnsolvedSA.algos;

import UnsolvedSA.model.Task;
import java.util.LinkedList;
import java.util.Queue;

public class RoundRobinScheduler implements Scheduler {
    private final Queue<Task> queue;
    private final int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        this.queue = new LinkedList<>();
        this.timeQuantum = timeQuantum;
    }

    @Override
    public void addTask(Task task) {
        queue.add(task);
    }

    @Override
    public void run() {
        System.out.println("Starting Round Robin Scheduler with time quantum: " + timeQuantum);
        while (!queue.isEmpty()) {
            Task currentTask = queue.poll();
            System.out.println("Executing task: " + currentTask.getName() + " for " + timeQuantum + " units.");
            // In a real scenario, you'd handle tasks that need more time
            // by re-queuing them. For this example, we'll assume tasks
            // are completed within the time quantum.
        }
        System.out.println("All tasks have been executed.");
    }
}