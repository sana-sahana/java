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

    // Adds a new job to the queue
    public void enqueue(int jobId) throws Exception {
        if (size == queue.length) {
            System.out.println("Service queue is full!");
            return;
        }

        rear = (rear + 1) % queue.length;
        queue[rear] = jobId;
        size++;

        System.out.println("Job added to pending queue.");
    }
}
