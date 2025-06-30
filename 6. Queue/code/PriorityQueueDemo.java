// File: PriorityQueueDemo.java
import java.util.PriorityQueue;
import java.util.Collections; // For reverse order example

/**
 * Demonstrates basic operations of Java's built-in PriorityQueue.
 * By default, it acts as a Min-Heap (smallest element has highest priority).
 * We also show how to make it a Max-Heap.
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        System.out.println("--- Default Priority Queue (Min-Heap: Smallest has highest priority) ---");
        // Create a default PriorityQueue (stores integers).
        // By default, it's a min-heap, meaning the smallest element has the highest priority.
        PriorityQueue<Integer> minPriorityQueue = new PriorityQueue<>();

        // 1. offer(E e) / add(E e): Adds an element to the priority queue.
        //    'offer' is generally preferred as it returns false if the queue is full (for bounded queues),
        //    while 'add' throws an IllegalStateException.
        // Time Complexity: O(log N) - due to maintaining the heap property.
        System.out.println("\n--- Adding elements (offer) ---");
        minPriorityQueue.offer(30);
        System.out.println("Queue after offer(30): " + minPriorityQueue); // Note: Internal array order not necessarily sorted
        minPriorityQueue.offer(10);
        System.out.println("Queue after offer(10): " + minPriorityQueue);
        minPriorityQueue.offer(50);
        System.out.println("Queue after offer(50): " + minPriorityQueue);
        minPriorityQueue.offer(20);
        System.out.println("Queue after offer(20): " + minPriorityQueue);

        // 2. peek(): Retrieves (but does not remove) the head of this queue.
        //    (The element with the highest priority, which is the root of the heap).
        // Time Complexity: O(1) - Constant time.
        System.out.println("\n--- Peeking at the highest priority element ---");
        System.out.println("Highest priority element (peek): " + minPriorityQueue.peek()); // Expected: 10 (smallest)

        // 3. poll(): Retrieves and removes the head of this queue.
        //    (The element with the highest priority).
        //    Returns null if the queue is empty (safe).
        // Time Complexity: O(log N) - due to heap property maintenance after removal.
        System.out.println("\n--- Removing elements by priority (poll) ---");
        System.out.println("Polling: " + minPriorityQueue.poll()); // Expected: 10
        System.out.println("Queue after poll: " + minPriorityQueue);
        System.out.println("Polling: " + minPriorityQueue.poll()); // Expected: 20
        System.out.println("Queue after poll: " + minPriorityQueue);

        // 4. isEmpty(): Checks if the queue is empty.
        // Time Complexity: O(1) - Constant time.
        System.out.println("\n--- Checking emptiness ---");
        System.out.println("Is queue empty? " + minPriorityQueue.isEmpty()); // Expected: false

        // 5. size(): Returns the number of elements in the queue.
        // Time Complexity: O(1) - Constant time.
        System.out.println("Current queue size: " + minPriorityQueue.size()); // Expected: 2

        minPriorityQueue.poll(); // Poll 30
        minPriorityQueue.poll(); // Poll 50
        System.out.println("Is queue empty after all polls? " + minPriorityQueue.isEmpty()); // Expected: true
        System.out.println("Polling from empty queue (returns null): " + minPriorityQueue.poll()); // Safe, returns null

        System.out.println("\n--- Custom Priority Queue (Max-Heap: Largest has highest priority) ---");
        // Create a PriorityQueue that acts as a max-heap (largest element has highest priority).
        // We pass Collections.reverseOrder() to the constructor as a Comparator.
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        maxPriorityQueue.offer(30);
        maxPriorityQueue.offer(10);
        maxPriorityQueue.offer(50);
        maxPriorityQueue.offer(20);
        System.out.println("Queue (Max-Heap) after adding elements: " + maxPriorityQueue);

        System.out.println("Highest priority element (peek): " + maxPriorityQueue.peek()); // Expected: 50
        System.out.println("Polling: " + maxPriorityQueue.poll()); // Expected: 50
        System.out.println("Polling: " + maxPriorityQueue.poll()); // Expected: 30
        System.out.println("Queue (Max-Heap) after polls: " + maxPriorityQueue);
    }
}
