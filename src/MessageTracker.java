public interface MessageTracker {
    void push(Message message);

    Message poll();
}