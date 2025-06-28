// File: SimpleArrayQueueDemo.java

/**
 * Implements a simple (linear) Queue data structure using a fixed-size array.
 * This implementation is prone to "wasted space" at the front of the array
 * after multiple dequeue operations.
 * It follows the First-In, First-Out (FIFO) principle.
 */
public class SimpleArrayQueueDemo {
    private int[] queueArray; // The array to store queue elements.
    private int front;        // Index of the first element (front of the queue).
    private int rear;         // Index of the last element (rear of the queue).
    private int size;         // Current number of elements in the queue.
    private int capacity;     // Maximum number of elements the queue can hold.

    /**
     * Constructor for SimpleArrayQueueDemo.
     * Initializes an empty queue with a specified maximum capacity.
     * @param capacity The maximum number of elements this queue can store.
     */
    public SimpleArrayQueueDemo(int capacity) {
        this.capacity = capacity;
        this.queueArray = new int[capacity];
        this.front = 0;   // Front typically starts at index 0.
        this.rear = -1;   // Rear starts at -1, indicating no elements yet.
        this.size = 0;    // Current size is 0.
        System.out.println("SimpleArrayQueueDemo created with capacity: " + capacity);
    }

    /**
     * Adds an item to the rear of the queue.
     * Time Complexity: O(1) - Constant time operation.
     * @param item The integer value to be enqueued.
     */
    public void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue is Full! Cannot enqueue " + item);
            return;
        }
        rear++; // Move rear to the next available slot.
        queueArray[rear] = item; // Place the item at the new rear.
        size++; // Increment the size.
        System.out.println("Enqueued: " + item + ". Current size: " + size);
    }

    /**
     * Removes and returns the item from the front of the queue.
     * Time Complexity: O(1) - Constant time operation.
     * @return The integer value that was dequeued, or -1 if the queue was empty.
     */
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty! Cannot dequeue.");
            return -1; // Indicate failure.
        }
        int dequeuedItem = queueArray[front]; // Get the item from the front.
        front++; // Move front to the next element. The old spot is now "empty".
        size--; // Decrement the size.
        System.out.println("Dequeued: " + dequeuedItem + ". Current size: " + size);
        return dequeuedItem;
    }

    /**
     * Returns the item at the front of the queue without removing it.
     * Time Complexity: O(1) - Constant time operation.
     * @return The integer value at the front, or -1 if the queue is empty.
     */
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is Empty! No item to peek.");
            return -1;
        }
        System.out.println("Peeked: " + queueArray[front]);
        return queueArray[front];
    }

    /**
     * Checks if the queue contains any items.
     * Time Complexity: O(1) - Constant time operation.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0; // The queue is empty if its size is 0.
    }

    /**
     * Checks if the queue is full.
     * Time Complexity: O(1) - Constant time operation.
     * @return true if the queue is full, false otherwise.
     */
    public boolean isFull() {
        return size == capacity; // The queue is full if its size equals capacity.
    }

    /**
     * Returns the current number of elements in the queue.
     * Time Complexity: O(1) - Constant time operation.
     * @return The number of elements in the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Utility method to print the elements of the queue from front to rear.
     * Note: This method only prints valid elements. It does NOT show the wasted space at the front.
     * Time Complexity: O(N) - Linear time, as it iterates through all elements.
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue: (empty)");
            return;
        }
        System.out.print("Queue (Front to Rear): ");
        // Iterate from the current 'front' index up to 'rear' (inclusive).
        for (int i = front; i <= rear; i++) {
            System.out.print(queueArray[i] + " ");
        }
        System.out.println();
    }

    /**
     * Main method to demonstrate all basic operations of the SimpleArrayQueueDemo.
     */
    public static void main(String[] args) {
        // Create a queue with a small capacity (e.g., 5) to observe behavior.
        SimpleArrayQueueDemo queue = new SimpleArrayQueueDemo(5);

        System.out.println("\n--- Initial State ---");
        System.out.println("Is queue empty? " + queue.isEmpty()); // Expected: true
        System.out.println("Queue size: " + queue.size());       // Expected: 0
        queue.printQueue();

        System.out.println("\n--- Enqueueing Elements ---");
        queue.enqueue(10); // Queue: [10,_,_,_,_]
        queue.printQueue();
        queue.enqueue(20); // Queue: [10,20,_,_,_]
        queue.printQueue();
        queue.enqueue(30); // Queue: [10,20,30,_,_]
        queue.printQueue();

        System.out.println("\n--- Dequeueing Elements (Illustrates Wasted Space) ---");
        queue.dequeue(); // Dequeue 10. Queue: [_,20,30,_,_]. Front moves to index 1.
        queue.printQueue();
        System.out.println("Current front index: " + queue.front + ", rear index: " + queue.rear);
        System.out.println("Current queue size: " + queue.size()); // Expected: 2

        queue.dequeue(); // Dequeue 20. Queue: [_,_,30,_,_]. Front moves to index 2.
        queue.printQueue();
        System.out.println("Current front index: " + queue.front + ", rear index: " + queue.rear);

        System.out.println("\n--- Enqueueing More Elements ---");
        queue.enqueue(40); // Queue: [_,_,30,40,_]
        queue.printQueue();
        queue.enqueue(50); // Queue: [_,_,30,40,50]
        queue.printQueue();

        System.out.println("Is queue full? " + queue.isFull()); // Expected: false (space at 0,1 unused)

        queue.enqueue(60); // Expected: Queue is Full! (rear reached capacity-1, cannot enqueue more)
        queue.printQueue(); // Still [_,_,30,40,50]

        System.out.println("\n--- Draining the Queue ---");
        while (!queue.isEmpty()) {
            queue.dequeue();
            queue.printQueue();
        }

        System.out.println("\n--- Final State ---");
        System.out.println("Is queue empty? " + queue.isEmpty()); // Expected: true
        System.out.println("Queue size: " + queue.size());       // Expected: 0

        System.out.println("\n--- Testing Underflow Conditions ---");
        queue.dequeue(); // Attempt to dequeue from an empty queue.
        queue.peek();    // Attempt to peek from an empty queue.
    }
}
