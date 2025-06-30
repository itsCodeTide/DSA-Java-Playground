// File: CircularArrayQueueDemo.java

/**
 * Implements a Queue data structure using a fixed-size circular array.
 * It efficiently reuses array space by wrapping around.
 * It follows the First-In, First-Out (FIFO) principle.
 */
public class CircularArrayQueueDemo {
    private int[] queueArray; // The array to store queue elements.
    private int front;        // Index of the first element (front of the queue).
    private int rear;         // Index of the last element (rear of the queue).
    private int size;         // Current number of elements in the queue.
    private int capacity;     // Maximum number of elements the queue can hold.

    /**
     * Constructor for CircularArrayQueueDemo.
     * Initializes an empty queue with a specified maximum capacity.
     * @param capacity The maximum number of elements this queue can store.
     */
    public CircularArrayQueueDemo(int capacity) {
        this.capacity = capacity;
        this.queueArray = new int[capacity];
        this.front = 0;   // Front typically starts at index 0.
        this.rear = -1;   // Rear starts at -1, indicating no elements yet.
        this.size = 0;    // Current size is 0.
        System.out.println("CircularArrayQueueDemo created with capacity: " + capacity);
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
        // Calculate the new 'rear' index, wrapping around if needed using modulo.
        rear = (rear + 1) % capacity;
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
            return -1; // Indicate failure. In a production system, you might throw an exception.
        }
        int dequeuedItem = queueArray[front]; // Get the item from the front.
        // Calculate the new 'front' index, wrapping around if needed.
        front = (front + 1) % capacity;
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
        return size == 0;
    }

    /**
     * Checks if the queue is full.
     * Time Complexity: O(1) - Constant time operation.
     * @return true if the queue is full, false otherwise.
     */
    public boolean isFull() {
        return size == capacity;
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
     * Time Complexity: O(N) - Linear time, as it iterates through all elements.
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue: (empty)");
            return;
        }
        System.out.print("Queue (Front to Rear): ");
        for (int i = 0; i < size; i++) {
            System.out.print(queueArray[(front + i) % capacity] + " ");
        }
        System.out.println();
    }

    /**
     * Main method to demonstrate all basic operations of the CircularArrayQueueDemo.
     */
    public static void main(String[] args) {
        // Create a queue with a small capacity (e.g., 3) to easily observe full/wrap-around behavior.
        CircularArrayQueueDemo queue = new CircularArrayQueueDemo(3);

        System.out.println("\n--- Initial State ---");
        System.out.println("Is queue empty? " + queue.isEmpty()); // Expected: true
        System.out.println("Queue size: " + queue.size());       // Expected: 0
        queue.printQueue();

        System.out.println("\n--- Enqueueing Elements ---");
        queue.enqueue(10); // Queue: [10, _, _] (front=0, rear=0)
        queue.printQueue();
        queue.enqueue(20); // Queue: [10, 20, _] (front=0, rear=1)
        queue.printQueue();
        queue.enqueue(30); // Queue: [10, 20, 30] (front=0, rear=2)
        queue.printQueue();

        System.out.println("\n--- Queue Full Check ---");
        System.out.println("Is queue full? " + queue.isFull()); // Expected: true
        queue.enqueue(40); // Expected: Queue is Full!

        System.out.println("\n--- Peeking at Front Element ---");
        queue.peek(); // Expected: 10

        System.out.println("\n--- Dequeueing Elements ---");
        queue.dequeue(); // Dequeue 10. Queue: [_, 20, 30] (front=1, rear=2)
        queue.printQueue();
        System.out.println("Current queue size: " + queue.size()); // Expected: 2

        System.out.println("\n--- Enqueueing to Re-use Space (Circular Behavior) ---");
        queue.enqueue(40); // Enqueue 40. Queue: [40, 20, 30] (front=1, rear=0 - wrapped!)
        queue.printQueue();

        System.out.println("\n--- Continuing Dequeue & Enqueue ---");
        queue.dequeue(); // Dequeue 20. Queue: [40, _, 30] (front=2, rear=0)
        queue.printQueue();
        queue.dequeue(); // Dequeue 30. Queue: [40, _, _] (front=0, rear=0 - wrapped!)
        queue.printQueue();
        queue.dequeue(); // Dequeue 40. Queue: [_, _, _] (front=1, rear=0 - wrapped!)
        queue.printQueue();

        System.out.println("\n--- Final State ---");
        System.out.println("Is queue empty? " + queue.isEmpty()); // Expected: true
        System.out.println("Queue size: " + queue.size());       // Expected: 0

        System.out.println("\n--- Testing Underflow Conditions ---");
        queue.dequeue(); // Attempt to dequeue from an empty queue.
        queue.peek();    // Attempt to peek from an empty queue.
    }
}
