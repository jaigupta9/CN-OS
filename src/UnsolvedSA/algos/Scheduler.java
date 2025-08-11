package UnsolvedSA.algos;

import UnsolvedSA.model.Task;

public interface Scheduler {
    void addTask(Task task);

    void run();
}