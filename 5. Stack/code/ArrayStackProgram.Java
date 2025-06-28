// File: ArrayStackProgram.java

/**
 * Implements a Stack data structure using a fixed-size array.
 * It follows the Last-In, First-Out (LIFO) principle.
 */
public class ArrayStackProgram {
    private int[] stackArray; // The array to store stack elements.
    private int top;         // Index of the top-most element in the stack.
                             // -1 indicates an empty stack.
    private int capacity;    // Maximum number of elements the stack can hold.

    /**
     * Constructor for ArrayStackProgram.
     * Initializes an empty stack with a specified maximum capacity.
     * @param capacity The maximum number of elements this stack can store.
     */
    public ArrayStackProgram(int capacity) {
        this.capacity = capacity;
        this.stackArray = new int[capacity]; // Create the array with the given capacity.
        this.top = -1; // Initially, the stack is empty.
        System.out.println("ArrayStackProgram created with capacity: " + capacity);
    }

    /**
     * Adds an item to the top of the stack.
     * Time Complexity: O(1) - Constant time operation.
     * Checks for Stack Overflow before pushing.
     * @param item The integer value to be pushed onto the stack.
     */
    public void push(int item) {
        if (isFull()) { // Check if the stack has reached its maximum capacity.
            System.out.println("Stack Overflow! Cannot push " + item + ". Stack is full.");
            return; // Exit the method if the stack is full.
        }
        stackArray[++top] = item; // 1. Increment 'top' (move to the next available slot).
                                  // 2. Place the 'item' at the new 'top' index.
        System.out.println("Pushed: " + item + ". Top index: " + top);
    }

    /**
     * Removes and returns the item from the top of the stack.
     * Time Complexity: O(1) - Constant time operation.
     * Checks for Stack Underflow before popping.
     * @return The integer value that was removed from the top of the stack,
     * or -1 if the stack was empty (indicating Stack Underflow).
     */
    public int pop() {
        if (isEmpty()) { // Check if the stack is empty.
            System.out.println("Stack Underflow! Cannot pop from an empty stack.");
            return -1; // Returning -1 as an indicator of failure.
                       // In robust applications, you might throw a NoSuchElementException.
        }
        int poppedItem = stackArray[top--]; // 1. Retrieve the item at the current 'top' index.
                                            // 2. Decrement 'top' (conceptually removing the item from the stack's view).
        System.out.println("Popped: " + poppedItem + ". Top index: " + top);
        return poppedItem; // Return the removed item.
    }

    /**
     * Returns the item at the top of the stack without removing it.
     * Time Complexity: O(1) - Constant time operation.
     * @return The integer value at the top of the stack,
     * or -1 if the stack is empty.
     */
    public int peek() {
        if (isEmpty()) { // Check if the stack is empty.
            System.out.println("Stack is empty. No item to peek.");
            return -1; // Returning -1 as an indicator of failure.
        }
        System.out.println("Peeked: " + stackArray[top]); // Print the item for demonstration.
        return stackArray[top]; // Return the item at the 'top' index.
    }

    /**
     * Checks if the stack contains any items.
     * Time Complexity: O(1) - Constant time operation.
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return top == -1; // The stack is empty if 'top' is -1.
    }

    /**
     * Checks if the stack is full. This method is specific to array-backed stacks.
     * Time Complexity: O(1) - Constant time operation.
     * @return true if the stack is full, false otherwise.
     */
    public boolean isFull() {
        return top == capacity - 1; // The stack is full if 'top' is at the last valid index.
    }

    /**
     * Returns the current number of elements in the stack.
     * Time Complexity: O(1) - Constant time operation.
     * @return The number of elements currently in the stack.
     */
    public int size() {
        return top + 1; // Since 'top' is 0-indexed, if top is 0, size is 1. If top is -1, size is 0.
    }

    /**
     * Utility method to print the elements of the stack from top to bottom.
     * Time Complexity: O(N) - Linear time, as it iterates through all elements.
     */
    public void printStack() {
        if (isEmpty()) { // Handle the case of an empty stack.
            System.out.println("Stack: (empty)");
            return;
        }
        System.out.print("Stack (Top to Bottom): ");
        for (int i = top; i >= 0; i--) { // Iterate from the 'top' index down to 0.
            System.out.print(stackArray[i] + " ");
        }
        System.out.println(); // Print a newline at the end for clean output.
    }

    /**
     * Main method to demonstrate all basic stack operations using an array-backed implementation.
     */
    public static void main(String[] args) {
        ArrayStackProgram stack = new ArrayStackProgram(3); // Create a new stack instance with capacity 3.

        System.out.println("\n--- Initial State ---");
        System.out.println("Is stack empty? " + stack.isEmpty()); // Expected: true
        System.out.println("Stack size: " + stack.size());       // Expected: 0
        stack.printStack(); // Expected: Stack: (empty)

        System.out.println("\n--- Pushing Elements ---");
        stack.push(10); // Push 10. Stack: [10]
        stack.printStack();
        stack.push(20); // Push 20. Stack: [20, 10]
        stack.printStack();
        stack.push(30); // Push 30. Stack: [30, 20, 10]
        stack.printStack();

        System.out.println("\n--- Stack Full Check ---");
        System.out.println("Is stack full? " + stack.isFull()); // Expected: true
        stack.push(40); // Expected: Stack Overflow! (Attempts to push beyond capacity)

        System.out.println("\n--- Peeking at Top Element ---");
        stack.peek(); // Expected: 30

        System.out.println("\n--- Popping Elements ---");
        stack.pop(); // Pop 30. Stack: [20, 10]
        stack.printStack();
        System.out.println("Current stack size: " + stack.size()); // Expected: 2

        stack.pop(); // Pop 20. Stack: [10]
        stack.printStack();

        stack.pop(); // Pop 10. Stack: []
        stack.printStack();

        System.out.println("\n--- Final State ---");
        System.out.println("Is stack empty? " + stack.isEmpty()); // Expected: true
        System.out.println("Stack size: " + stack.size());       // Expected: 0

        System.out.println("\n--- Testing Underflow Conditions ---");
        stack.pop(); // Attempt to pop from an empty stack.
        stack.peek(); // Attempt to peek from an empty stack.
    }
}
