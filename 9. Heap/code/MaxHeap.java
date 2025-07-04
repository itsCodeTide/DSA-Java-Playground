Heap in detail: https://www.tutorialspoint.com/data_structures_algorithms/heap_data_structure.htm
// File: MaxHeap.java

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException; // For handling empty heap operations

/**
 * Implements a Max-Heap data structure.
 * A Max-Heap is a complete binary tree where the value of each node is
 * greater than or equal to the value of its children.
 * This implementation uses an ArrayList as the underlying data structure.
 */
public class MaxHeap {
    private List<Integer> heap; // The ArrayList to store heap elements.

    /**
     * Constructor for MaxHeap.
     * Initializes an empty Max-Heap.
     */
    public MaxHeap() {
        this.heap = new ArrayList<>();
        System.out.println("MaxHeap created.");
    }

    // --- Helper Methods for Heap Navigation ---

    /**
     * Returns the index of the parent of the node at the given index.
     * Time Complexity: O(1)
     * @param childIndex The index of the child node.
     * @return The index of the parent node.
     */
    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    /**
     * Returns the index of the left child of the node at the given index.
     * Time Complexity: O(1)
     * @param parentIndex The index of the parent node.
     * @return The index of the left child node.
     */
    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /**
     * Returns the index of the right child of the node at the given index.
     * Time Complexity: O(1)
     * @param parentIndex The index of the parent node.
     * @return The index of the right child node.
     */
    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    /**
     * Checks if a node at the given index has a parent.
     * Time Complexity: O(1)
     * @param index The index of the node.
     * @return true if the node has a parent, false otherwise.
     */
    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    /**
     * Checks if a node at the given index has a left child.
     * Time Complexity: O(1)
     * @param index The index of the node.
     * @return true if the node has a left child, false otherwise.
     */
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < heap.size();
    }

    /**
     * Checks if a node at the given index has a right child.
     * Time Complexity: O(1)
     * @param index The index of the node.
     * @return true if the node has a right child, false otherwise.
     */
    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < heap.size();
    }

    /**
     * Returns the value of the parent of the node at the given index.
     * @param index The index of the child node.
     * @return The integer value of the parent.
     */
    private int parent(int index) {
        return heap.get(getParentIndex(index));
    }

    /**
     * Returns the value of the left child of the node at the given index.
     * @param index The index of the parent node.
     * @return The integer value of the left child.
     */
    private int leftChild(int index) {
        return heap.get(getLeftChildIndex(index));
    }

    /**
     * Returns the value of the right child of the node at the given index.
     * @param index The index of the parent node.
     * @return The integer value of the right child.
     */
    private int rightChild(int index) {
        return heap.get(getRightChildIndex(index));
    }

    /**
     * Swaps two elements in the heap's underlying ArrayList.
     * Time Complexity: O(1)
     * @param index1 The index of the first element.
     * @param index2 The index of the second element.
     */
    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    // --- Basic Heap Operations ---

    /**
     * Returns the number of elements currently in the heap.
     * Time Complexity: O(1)
     * @return The current size of the heap.
     */
    public int size() {
        return heap.size();
    }

    /**
     * Checks if the heap is empty.
     * Time Complexity: O(1)
     * @return true if the heap contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Returns the maximum element in the Max-Heap without removing it.
     * This is always the root element.
     * Time Complexity: O(1)
     * @return The maximum element.
     * @throws NoSuchElementException if the heap is empty.
     */
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty. Cannot peek.");
        }
        System.out.println("Peeked: " + heap.get(0));
        return heap.get(0); // The root is always at index 0.
    }

    /**
     * Inserts a new element into the Max-Heap.
     * The new element is added to the end and then "heapified up" to maintain the heap property.
     * Time Complexity: O(log N) on average and worst case.
     * @param item The integer element to insert.
     */
    public void insert(int item) {
        heap.add(item); // Add the new item to the end of the heap.
        heapifyUp();    // Restore heap property by moving the new item up.
        System.out.println("Inserted: " + item);
    }

    /**
     * Restores the heap property by moving an element up the heap from its current position.
     * This is typically called after inserting a new element at the end.
     * Time Complexity: O(log N) in worst case (moves from leaf to root).
     */
    private void heapifyUp() {
        int currentIndex = heap.size() - 1; // Start from the last element (newly inserted).
        // While the current node has a parent and its value is greater than its parent's value:
        while (hasParent(currentIndex) && parent(currentIndex) < heap.get(currentIndex)) {
            swap(currentIndex, getParentIndex(currentIndex)); // Swap current with its parent.
            currentIndex = getParentIndex(currentIndex);      // Move up to the parent's position.
        }
    }

    /**
     * Removes and returns the maximum element from the Max-Heap.
     * The root element is removed, replaced by the last element, and then "heapified down".
     * Time Complexity: O(log N) on average and worst case.
     * @return The maximum element that was removed.
     * @throws NoSuchElementException if the heap is empty.
     */
    public int extractMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty. Cannot extract max.");
        }
        int max = heap.get(0); // The maximum element is always at the root (index 0).
        heap.set(0, heap.get(heap.size() - 1)); // Move the last element to the root.
        heap.remove(heap.size() - 1);           // Remove the last element (which is now duplicated at root).
        heapifyDown();                          // Restore heap property by moving the new root down.
        System.out.println("Extracted Max: " + max);
        return max;
    }

    /**
     * Restores the heap property by moving an element down the heap from the root.
     * This is typically called after removing the root element.
     * Time Complexity: O(log N) in worst case (moves from root to leaf).
     */
    private void heapifyDown() {
        int currentIndex = 0; // Start from the root.
        // While the current node has at least one child:
        while (hasLeftChild(currentIndex)) {
            int largerChildIndex = getLeftChildIndex(currentIndex); // Assume left child is larger.
            // If there's a right child and it's larger than the left child, pick the right child.
            if (hasRightChild(currentIndex) && rightChild(currentIndex) > leftChild(currentIndex)) {
                largerChildIndex = getRightChildIndex(currentIndex);
            }

            // If the current node is already larger than its largest child, heap property is satisfied.
            if (heap.get(currentIndex) >= heap.get(largerChildIndex)) {
                break;
            } else { // Otherwise, swap with the larger child.
                swap(currentIndex, largerChildIndex);
            }
            currentIndex = largerChildIndex; // Move down to the larger child's position.
        }
    }

    /**
     * Builds a heap from a given list of elements.
     * This method can be used to initialize a heap efficiently from an array.
     * Time Complexity: O(N) - surprisingly, building a heap from N elements is linear time.
     * @param elements The list of elements to build the heap from.
     */
    public void buildHeap(List<Integer> elements) {
        this.heap = new ArrayList<>(elements); // Copy elements to the heap.
        // Start heapifying down from the last non-leaf node up to the root.
        // The last non-leaf node is at index (size/2 - 1).
        for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
            heapifyDownFromIndex(i); // Call heapifyDown from a specific index.
        }
        System.out.println("Heap built from elements: " + elements);
    }

    /**
     * Helper method for buildHeap to heapify down from a specific index.
     * @param currentIndex The index from which to start heapifying down.
     */
    private void heapifyDownFromIndex(int currentIndex) {
        // This is essentially the same logic as heapifyDown(), but starting from a given index.
        while (getLeftChildIndex(currentIndex) < heap.size()) { // Check for left child existence
            int largerChildIndex = getLeftChildIndex(currentIndex);

            // Check if right child exists and is larger than left child
            if (getRightChildIndex(currentIndex) < heap.size() &&
                heap.get(getRightChildIndex(currentIndex)) > heap.get(largerChildIndex)) {
                largerChildIndex = getRightChildIndex(currentIndex);
            }

            // If current node is greater than or equal to its largest child, stop.
            if (heap.get(currentIndex) >= heap.get(largerChildIndex)) {
                break;
            } else { // Otherwise, swap and continue heapifying down.
                swap(currentIndex, largerChildIndex);
            }
            currentIndex = largerChildIndex;
        }
    }


    /**
     * Utility method to print the heap's underlying array representation.
     */
    public void printHeapArray() {
        if (isEmpty()) {
            System.out.println("Heap (array representation): []");
            return;
        }
        System.out.println("Heap (array representation): " + heap);
    }

    /**
     * Main method to demonstrate basic Max-Heap operations.
     */
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();

        System.out.println("--- Initial State ---");
        System.out.println("Is heap empty? " + maxHeap.isEmpty()); // true
        System.out.println("Heap size: " + maxHeap.size());       // 0
        maxHeap.printHeapArray();

        System.out.println("\n--- Insert Operations ---");
        maxHeap.insert(10); // [10]
        maxHeap.printHeapArray();
        maxHeap.insert(5);  // [10, 5]
        maxHeap.printHeapArray();
        maxHeap.insert(15); // [15, 5, 10] (15 moves up)
        maxHeap.printHeapArray();
        maxHeap.insert(20); // [20, 15, 10, 5] (20 moves up)
        maxHeap.printHeapArray();
        maxHeap.insert(8);  // [20, 15, 10, 5, 8]
        maxHeap.printHeapArray();

        System.out.println("\n--- Peek Operation ---");
        maxHeap.peek(); // Expected: 20

        System.out.println("\n--- Extract Max Operations ---");
        maxHeap.extractMax(); // Removes 20. Heapify down.
        maxHeap.printHeapArray(); // Expected: [15, 8, 10, 5] (order might vary slightly depending on heapifyDown path)
        System.out.println("Heap size: " + maxHeap.size()); // 4

        maxHeap.extractMax(); // Removes 15.
        maxHeap.printHeapArray(); // Expected: [10, 8, 5]
        System.out.println("Heap size: " + maxHeap.size()); // 3

        System.out.println("\n--- Building Heap from a List ---");
        List<Integer> initialElements = new ArrayList<>();
        initialElements.add(4);
        initialElements.add(1);
        initialElements.add(3);
        initialElements.add(2);
        initialElements.add(16);
        initialElements.add(9);
        initialElements.add(10);

        MaxHeap newHeap = new MaxHeap();
        newHeap.buildHeap(initialElements); // Build from [4, 1, 3, 2, 16, 9, 10]
        newHeap.printHeapArray(); // Expected: [16, 4, 10, 2, 1, 9, 3] (after buildHeap)

        newHeap.peek(); // Expected: 16
        newHeap.extractMax(); // Removes 16
        newHeap.printHeapArray(); // Expected: [10, 4, 9, 2, 1, 3]

        System.out.println("\n--- Emptying Heap ---");
        while (!newHeap.isEmpty()) {
            newHeap.extractMax();
            newHeap.printHeapArray();
        }

        System.out.println("\n--- Testing Empty Heap Operations ---");
        System.out.println("Is newHeap empty? " + newHeap.isEmpty()); // true
        try {
            newHeap.peek();
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            newHeap.extractMax();
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
