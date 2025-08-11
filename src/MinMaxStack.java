public class MinMaxStack {
    private Node head;

    private static class Node {
        int val;
        int min;
        int max;
        Node next;

        Node(int val, int min, int max, Node next) {
            this.val = val;
            this.min = min;
            this.max = max;
            this.next = next;
        }
    }

    public MinMaxStack() {
        // Constructor
    }

    public void push(int val) {
        if (head == null) {
            head = new Node(val, val, val, null);
        } else {
            int currentMin = Math.min(val, head.min);
            int currentMax = Math.max(val, head.max);
            Node newNode = new Node(val, currentMin, currentMax, head);
            head = newNode;
        }
    }

    public int pop() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }
        int val = head.val;
        head = head.next;
        return val;
    }

    public int peek() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return head.val;
    }

    public int getMin() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return head.min;
    }

    public int getMax() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return head.max;
    }
}