import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class RobotTracker implements MessageTracker {
    private final BlockingQueue<Message> queue;

    public RobotTracker() {
        this.queue = new LinkedBlockingQueue<>(10);
    }

    @Override
    public void push(Message message) {
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Failed to push message: " + e.getMessage());
        }
    }

    @Override
    public Message poll() {
        try {
            return queue.poll(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Failed to poll message: " + e.getMessage());
            return null;
        }
    }
}