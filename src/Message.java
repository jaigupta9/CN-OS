public class Message {
    private final String content;
    private final int sequenceId;

    public Message(int sequenceId, String content) {
        this.sequenceId = sequenceId;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getSequenceId() {
        return sequenceId;
    }
}