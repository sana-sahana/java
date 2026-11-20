public class ServiceQueue {

    private int queue[];
    private int front;
    private int rear;
    private int size;

    // Constructor to initialize circular queue
    public ServiceQueue(int capacity) {
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
}
