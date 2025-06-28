// File: MyStackProgram.java

/**
 * Represents a single node in the stack.
 * Each node stores an integer data and a reference to the next node below it.
 */
class StackNode {
    int data;     // The data value stored in this node.
    StackNode next; // Reference to the next node in the stack (towards the bottom).

    /**
     * Constructor for StackNode.
     * @param data The integer value to store in this node.
     */
    public StackNode(int data) {
        this.data = data;
        this.next = null; // A newly created node doesn't point to anything initially.
    }
}

/**
 * Implements a Stack data structure using a singly linked list.
 * It follows the Last-In, First-Out (LIFO) principle.
 */
public class MyStackProgram {
    private StackNode top; // Reference to the top-most node of the stack.
                           // If 'top' is null, the stack is empty.
    private int size;      // Keeps track of the current number of elements in the stack.

    /**
     * Constructor for MyStackProgram.
     * Initializes an empty stack.
     */
    public MyStackProgram() {
        this.top = null; // Stack starts empty.
        this.size = 0;   // Initial size is zero.
        System.out.println("MyStackProgram (LinkedList-based Stack) created.");
    }

    /**
     * Adds an item to the top of the stack.
     * Time Complexity: O(1) - Constant time operation.
     * @param item The integer value to be pushed onto the stack.
     */
    public void push(int item) {
        StackNode newNode = new StackNode(item); // 1. Create a new node with the given item.
        newNode.next = top;                      // 2. Make the new node's 'next' pointer point to the current 'top'.
                                                 //    This links the new node to the rest of the stack.
        top = newNode;                           // 3. Update 'top' to refer to the new node, making it the new top.
        size++;                                  // 4. Increment the stack's size.
        System.out.println("Pushed: " + item + ". Current stack size: " + size);
    }

    /**
     * Removes and returns the item from the top of the stack.
     * Time Complexity: O(1) - Constant time operation.
     * @return The integer value that was removed from the top of the stack,
     * or -1 if the stack was empty (indicating Stack Underflow).
     */
    public int pop() {
        if (isEmpty()) { // 1. Check for Stack Underflow condition.
            System.out.println("Stack Underflow! Cannot pop from an empty stack.");
            return -1; // Returning -1 as an indicator of failure. In real applications,
                       // you might throw a NoSuchElementException.
        }
        int poppedItem = top.data; // 2. Retrieve the data from the current 'top' node.
        top = top.next;            // 3. Move 'top' to the next node (effectively removing the old top).
                                   //    The old 'top' node is now unreferenced and will be garbage collected.
        size--;                    // 4. Decrement the stack's size.
        System.out.println("Popped: " + poppedItem + ". Current stack size: " + size);
        return poppedItem;         // 5. Return the popped item.
    }

    /**
     * Returns the item at the top of the stack without removing it.
     * Time Complexity: O(1) - Constant time operation.
     * @return The integer value at the top of the stack,
     * or -1 if the stack is empty.
     */
    public int peek() {
        if (isEmpty()) { // 1. Check if the stack is empty.
            System.out.println("Stack is empty. No item to peek.");
            return -1; // Returning -1 as an indicator of failure.
        }
        System.out.println("Peeked: " + top.data); // 2. Print the item for demonstration.
        return top.data; // 3. Return the data from the 'top' node.
    }

    /**
     * Checks if the stack contains any items.
     * Time Complexity: O(1) - Constant time operation.
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return top == null; // The stack is empty if 'top' is pointing to null.
    }

    /**
     * Returns the current number of elements in the stack.
     * Time Complexity: O(1) - Constant time operation because 'size' is explicitly maintained.
     * @return The number of elements in the stack.
     */
    public int size() {
        return size; // Return the current count of elements.
    }

    /**
     * Utility method to print the elements of the stack from top to bottom.
     * Time Complexity: O(N) - Linear time, as it traverses all elements.
     */
    public void printStack() {
        if (isEmpty()) { // Handle the case of an empty stack.
            System.out.println("Stack: (empty)");
            return;
        }
        System.out.print("Stack (Top to Bottom): ");
        StackNode current = top; // Start traversal from the 'top' node.
        while (current != null) { // Loop until the end of the linked list (null).
            System.out.print(current.data + " ");
            current = current.next; // Move to the next node down.
        }
        System.out.println(); // Print a newline at the end for clean output.
    }

    /**
     * Main method to demonstrate all basic stack operations.
     */
    public static void main(String[] args) {
        MyStackProgram stack = new MyStackProgram(); // Create a new stack instance.

        System.out.println("\n--- Initial State ---");
        System.out.println("Is stack empty? " + stack.isEmpty()); // Should be true.
        System.out.println("Stack size: " + stack.size());       // Should be 0.
        stack.printStack(); // Should print (empty).

        System.out.println("\n--- Pushing Elements ---");
        stack.push(10); // Push 10. Stack: [10]
        stack.printStack();
        stack.push(20); // Push 20. Stack: [20, 10]
        stack.printStack();
        stack.push(30); // Push 30. Stack: [30, 20, 10]
        stack.printStack();

        System.out.println("\n--- Checking Current State ---");
        System.out.println("Is stack empty? " + stack.isEmpty()); // Should be false.
        System.out.println("Stack size: " + stack.size());       // Should be 3.

        System.out.println("\n--- Peeking at Top Element ---");
        stack.peek(); // Should peek 30.

        System.out.println("\n--- Popping Elements ---");
        stack.pop(); // Pop 30. Stack: [20, 10]
        stack.printStack();
        System.out.println("Current stack size: " + stack.size()); // Should be 2.

        stack.pop(); // Pop 20. Stack: [10]
        stack.printStack();

        System.out.println("\n--- Peeking After Pops ---");
        stack.peek(); // Should peek 10.

        stack.pop(); // Pop 10. Stack: []
        stack.printStack();

        System.out.println("\n--- Final State ---");
        System.out.println("Is stack empty? " + stack.isEmpty()); // Should be true.
        System.out.println("Stack size: " + stack.size());       // Should be 0.

        System.out.println("\n--- Testing Underflow Conditions ---");
        stack.pop(); // Attempt to pop from an empty stack.
        stack.peek(); // Attempt to peek from an empty stack.
    }
}
