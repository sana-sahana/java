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

    // Custom exception for full queue
    static class QueueFullException extends Exception {
        public QueueFullException(String message) {
            super(message);
        }
    }

    // Custom exception for empty queue
    static class QueueEmptyException extends Exception {
        public QueueEmptyException(String message) {
            super(message);
        }
    }

    // Adds a new job to the queue
    public void enqueue(int jobId) throws QueueFullException {
        if (size == queue.length) {
            throw new QueueFullException("Service queue is full!");
        }

        rear = (rear + 1) % queue.length;
        queue[rear] = jobId;
        size++;

        System.out.println("Job added to pending queue.");
    }

    // Removes the first job from the queue
    public int dequeue() throws QueueEmptyException {
        if (size == 0) {
            throw new QueueEmptyException("No pending jobs available!");
        }

        int jobId = queue[front];
        front = (front + 1) % queue.length;
        size--;

        return jobId;
    }

    // Removes a particular job from the queue
    public void removeJob(int jobId) {
        int temp[] = new int[queue.length];
        int newSize = 0;

        for (int i = 0; i < size; i++) {
            int index = (front + i) % queue.length;
            if (queue[index] != jobId) {
                temp[newSize] = queue[index];
                newSize++;
            }
        }

        queue = temp;
        front = 0;
        size = newSize;
        rear = size - 1;
    }

    // Checks whether queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Displays all pending jobs
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No pending jobs!");
            return;
        }

        System.out.println("\n===== PENDING JOBS =====");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % queue.length;
            System.out.println("Job ID: " + queue[index]);
        }
    }
}
