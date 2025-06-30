// File: DequeDemo.java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException; // For handling exceptions like remove() on empty deque

/**
 * Demonstrates basic operations of Java's built-in Deque interface,
 * using ArrayDeque as the implementation.
 * Deque allows additions and removals from both ends efficiently.
 */
public class DequeDemo {

    public static void main(String[] args) {
        // Create a Deque (using ArrayDeque implementation).
        // ArrayDeque is generally preferred for its performance.
        // You could also use: Deque<String> myDeque = new LinkedList<>();
        Deque<String> myDeque = new ArrayDeque<>();

        System.out.println("--- Initial State ---");
        System.out.println("Is Deque empty? " + myDeque.isEmpty()); // Expected: true
        System.out.println("Deque size: " + myDeque.size());       // Expected: 0

        System.out.println("\n--- Adding Elements to Both Ends ---");

        // 1. addLast(E e) / offerLast(E e): Adds an element to the end (tail) of the deque.
        //    (This acts like 'enqueue' for a regular Queue)
        // Time Complexity: O(1)
        myDeque.addLast("Task A"); // Deque: [Task A]
        System.out.println("addLast(\"Task A\"): " + myDeque);
        myDeque.addLast("Task B"); // Deque: [Task A, Task B]
        System.out.println("addLast(\"Task B\"): " + myDeque);

        // 2. addFirst(E e) / offerFirst(E e): Adds an element to the front (head) of the deque.
        //    (This acts like 'push' for a Stack)
        // Time Complexity: O(1)
        myDeque.addFirst("Urgent Task X"); // Deque: [Urgent Task X, Task A, Task B]
        System.out.println("addFirst(\"Urgent Task X\"): " + myDeque);
        myDeque.addFirst("Critical Task Y"); // Deque: [Critical Task Y, Urgent Task X, Task A, Task B]
        System.out.println("addFirst(\"Critical Task Y\"): " + myDeque);

        System.out.println("\n--- Peeking at Both Ends ---");

        // 3. peekFirst(): Retrieves (but does not remove) the first element.
        //    (Like 'peek' for a Queue or a Stack). Returns null if empty.
        // Time Complexity: O(1)
        System.out.println("First element (peekFirst): " + myDeque.peekFirst()); // Expected: Critical Task Y
        // 4. peekLast(): Retrieves (but does not remove) the last element.
        //    Returns null if empty.
        // Time Complexity: O(1)
        System.out.println("Last element (peekLast): " + myDeque.peekLast());   // Expected: Task B

        System.out.println("\n--- Removing Elements from Both Ends ---");

        // 5. removeFirst() / pollFirst(): Retrieves and removes the first element.
        //    (Like 'dequeue' for a Queue, or 'pop' for a Stack).
        //    'removeFirst()' throws NoSuchElementException if empty. 'pollFirst()' returns null if empty (safer).
        // Time Complexity: O(1)
        System.out.println("Removed from front (removeFirst): " + myDeque.removeFirst()); // Expected: Critical Task Y
        System.out.println("Deque after removeFirst: " + myDeque); // [Urgent Task X, Task A, Task B]

        // 6. removeLast() / pollLast(): Retrieves and removes the last element.
        //    'removeLast()' throws NoSuchElementException if empty. 'pollLast()' returns null if empty.
        // Time Complexity: O(1)
        System.out.println("Removed from rear (removeLast): " + myDeque.removeLast());   // Expected: Task B
        System.out.println("Deque after removeLast: " + myDeque);   // [Urgent Task X, Task A]

        // --- Demonstrating Stack-like Behavior ---
        System.out.println("\n--- Using Stack-like Methods (push/pop/peek) ---");
        // Deque directly implements these methods for convenience.
        myDeque.push("New Stack Item"); // Equivalent to addFirst()
        System.out.println("After push(\"New Stack Item\"): " + myDeque);
        System.out.println("Stack-like peek: " + myDeque.peek()); // Equivalent to peekFirst()
        System.out.println("Stack-like pop: " + myDeque.pop());   // Equivalent to removeFirst()
        System.out.println("Deque after pop: " + myDeque);

        System.out.println("\n--- Current Deque State ---");
        System.out.println("Current Deque size: " + myDeque.size()); // Expected: 2 (Urgent Task X, Task A)

        // Drain the deque using pollFirst (queue-like dequeue)
        while (!myDeque.isEmpty()) {
            System.out.println("Polling (queue-like): " + myDeque.pollFirst());
        }

        System.out.println("\n--- Final State ---");
        System.out.println("Is Deque empty? " + myDeque.isEmpty()); // Expected: true
        System.out.println("Deque size: " + myDeque.size());       // Expected: 0

        System.out.println("\n--- Testing Empty Deque Conditions ---");
        // Using pollFirst()/pollLast()/peekFirst()/peekLast() are safe (return null)
        System.out.println("pollFirst from empty: " + myDeque.pollFirst()); // Expected: null
        System.out.println("peekLast from empty: " + myDeque.peekLast());   // Expected: null

        // Using removeFirst()/removeLast()/pop() will throw NoSuchElementException if empty!
        try {
            myDeque.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage() + " - Cannot removeFirst from empty Deque!");
        }
    }
}
