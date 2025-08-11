package utility;

public class CalculatSumRunnable implements Runnable {
    private int[] arr;
    private int start;
    private int end;
    private long sum;

    public CalculatSumRunnable(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.sum = 0;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }
    }

    public long getSum() {
        return sum;
    }
}