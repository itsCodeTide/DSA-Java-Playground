Hello there, my fellow data structure explorers!We've talked about linear structures, hierarchical trees, and even flexible graphs. Now, let's dive into another powerful tree-based data structure that's not quite a Binary Search Tree, but equally important: the Heap!Imagine you're managing a list of tasks, but they don't just get done in the order they arrived (like a Queue) or based on their name (like a BST). Instead, you always need to pick the most urgent task. Or perhaps the least urgent task. This is precisely the kind of problem Heaps are designed to solve efficiently!⛰️ What Exactly is a Heap?A Heap is a specialized tree-based data structure that satisfies the heap property. It's typically implemented as a complete binary tree.Think of it like:A Tournament Bracket (but upside down): The best player is always at the very top. Below them are slightly less good players, and so on.A Hierarchy of Importance: The most important item is always at the top, and as you go down, items become less important relative to their parents.The Two Core Properties of a Heap:For any data structure to be called a "Heap," it must satisfy two crucial properties:Completeness (Structural Property):A heap is a complete binary tree. This means all levels of the tree are fully filled, except possibly the last level, and the nodes in the last level are filled from left to right.This property is vital because it allows us to efficiently represent the heap using a simple array (we'll see how in a moment!).Heap Property (Ordering Property):This property defines the relationship between a parent node and its children. There are two types:Max-Heap: For every node N, the value of N is greater than or equal to the values of its children. This means the largest element is always at the root.Min-Heap: For every node N, the value of N is less than or equal to the values of its children. This means the smallest element is always at the root.🏔️ Types of Heaps: Max-Heap vs. Min-HeapThe ordering property gives us two primary types of heaps:1. Max-HeapThe value of the root node is the maximum element in the entire heap.Every parent node is greater than or equal to its children.        20 (Max)
       /  \
      15   10
     / \   /
    5   8 3
2. Min-HeapThe value of the root node is the minimum element in the entire heap.Every parent node is less than or equal to its children.        3 (Min)
       / \
      5   10
     / \  /
    15 8 20
📏 Heap Representation: The Power of Arrays!One of the most elegant aspects of a heap is how it's stored. Because it's a complete binary tree, we don't need explicit left and right pointers like in a traditional TreeNode. We can perfectly map a heap to a simple array (or ArrayList in Java)!Here's how the mapping works for a node at index i (0-indexed array):Parent of node i: (i - 1) / 2Left Child of node i: 2 * i + 1Right Child of node i: 2 * i + 2Example:Heap (Tree View):
            100 (index 0)
           /   \
  (index 1) 80    90 (index 2)
           / \   / \
(index 3) 70 60 50 40 (index 6)

Array Representation:
[100, 80, 90, 70, 60, 50, 40]
  0    1   2   3   4   5   6
Node at index 1 (80):Parent: (1-1)/2 = 0 (100)Left Child: 2*1+1 = 3 (70)Right Child: 2*1+2 = 4 (60)This array representation is incredibly efficient in terms of space (no overhead for pointers per node) and allows for O(1) access to parent/child nodes.🚀 Basic Operations on a HeapThe core operations in a heap are designed to maintain the heap property after insertions or deletions.1. insert(item): Adding a New ElementConcept:Always add the new item to the very end of the heap (the next available position in the array). This maintains the completeness property.Now, the item might violate the heap property (e.g., in a Max-Heap, the new item might be larger than its parent). To fix this, we perform a "heapifyUp" operation.heapifyUp() (or "Bubble Up" / "Swim"):Start from the newly inserted item's position.Compare it with its parent.If it's in the correct position (e.g., smaller than parent in Max-Heap), stop.If it violates the heap property (e.g., larger than parent in Max-Heap), swap it with its parent.Continue this process, moving up the tree, until the heap property is restored or the item reaches the root.Time Complexity: O(logN) in both average and worst cases, because in the worst case, the new item might travel from a leaf all the way to the root (height of the tree).2. peek(): Getting the Top ElementConcept: Returns the highest priority element (maximum in Max-Heap, minimum in Min-Heap) without removing it.Time Complexity: O(1), as it's always the element at index 0 (the root).3. extractMax() / extractMin(): Removing the Top ElementConcept:The element to be removed is always the root (the max in a Max-Heap, min in a Min-Heap).To maintain completeness and prepare for re-heapifying, replace the root with the very last element in the heap (the last element in the array).Remove the last element from its original position (it's now at the root).Now, the new root might violate the heap property. To fix this, we perform a "heapifyDown" operation.heapifyDown() (or "Bubble Down" / "Sink"):Start from the root (the element that was just moved there).Compare it with its children.If it's in the correct position (e.g., larger than both children in Max-Heap), stop.If it violates the heap property, swap it with its largest child (for Max-Heap) or smallest child (for Min-Heap). This is crucial to maintain the ordering.Continue this process, moving down the tree, until the heap property is restored or the item reaches a leaf node.Time Complexity: O(logN) in both average and worst cases, because the element might travel from the root all the way to a leaf.4. isEmpty(): Checking for EmptinessConcept: Checks if the heap contains any elements.Time Complexity: O(1), simply check if the underlying array/list is empty.5. size(): Getting the Number of ElementsConcept: Returns the total count of elements in the heap.Time Complexity: O(1), simply return the size of the underlying array/list.6. buildHeap(elements): Building a Heap from an ArrayConcept: Given an arbitrary array of elements, transform it into a valid heap.Naive Approach: Insert each element one by one using insert(). This would be NtimesO(logN)=O(NlogN).Efficient Approach:Place all elements into the array in any order.Start from the last non-leaf node (which is at index (N/2) - 1 for a 0-indexed array of size N).For each node from this last non-leaf node up to the root (index 0), perform a heapifyDown() operation.This process effectively "sinks" larger (or smaller) elements down into their correct positions.Time Complexity: Surprisingly, building a heap using this method is O(N) (linear time). This is a common interview question and a very efficient algorithm!📈 Advantages and Use Cases of HeapsHeaps are incredibly useful due to their efficiency in managing priority:Advantages:Efficient Priority Retrieval: O(1) to get the max/min element (peek).Efficient Priority Management: O(logN) for insert and extractMax/Min.Efficient Construction: O(N) to build a heap from an unsorted array.Space Efficient: O(N) space, as it uses a simple array.Common Use Cases:Priority Queues: This is the most common application. Heaps are the underlying data structure for efficient priority queues.Task scheduling (e.g., operating systems scheduling processes based on priority).Event simulation (processing events in chronological order).Emergency room triage (patients with higher urgency seen first).Heap Sort: A comparison-based sorting algorithm that uses a heap. It has O(NlogN) time complexity.Graph Algorithms:Dijkstra's Algorithm (shortest path): Uses a min-priority queue to efficiently select the next closest unvisited vertex.Prim's Algorithm (minimum spanning tree): Uses a min-priority queue to select the next edge with the smallest weight.K-th Largest/Smallest Element: Efficiently finding the k-th largest or smallest element in a stream of data or a large dataset.Median Finding: Can be used to efficiently find the median of a stream of numbers using two heaps (one min-heap, one max-heap).💻 Java Code Implementation of a Max-HeapHere's a complete Java implementation of a MaxHeap, demonstrating all the basic operations we've discussed.// File: MaxHeap.java

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException; // For handling empty heap operations

/**
 * Implements a Max-Heap data structure.
 * A Max-Heap is a complete binary tree where the value of each node is
 * greater than or equal to the value of its children.
 * This implementation uses an ArrayList as the underlying data structure
 * for efficient array-based heap operations.
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

    // --- Helper Methods for Heap Navigation (O(1) Time Complexity) ---

    /**
     * Returns the index of the parent of the node at the given childIndex.
     * @param childIndex The index of the child node.
     * @return The index of the parent node.
     */
    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    /**
     * Returns the index of the left child of the node at the given parentIndex.
     * @param parentIndex The index of the parent node.
     * @return The index of the left child node.
     */
    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /**
     * Returns the index of the right child of the node at the given parentIndex.
     * @param parentIndex The index of the parent node.
     * @return The index of the right child node.
     */
    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    /**
     * Checks if a node at the given index has a parent.
     * @param index The index of the node.
     * @return true if the node has a parent, false otherwise.
     */
    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    /**
     * Checks if a node at the given index has a left child.
     * @param index The index of the node.
     * @return true if the node has a left child, false otherwise.
     */
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < heap.size();
    }

    /**
     * Checks if a node at the given index has a right child.
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
     * This is always the root element (at index 0).
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
     * The new element is added to the end of the array and then "heapified up"
     * to maintain the heap property.
     * Time Complexity: O(log N) on average and worst case, where N is the number of elements.
     * @param item The integer element to insert.
     */
    public void insert(int item) {
        heap.add(item); // Add the new item to the end of the heap (maintains completeness).
        heapifyUp();    // Restore heap property by moving the new item up if necessary.
        System.out.println("Inserted: " + item);
    }

    /**
     * Restores the heap property by moving an element up the heap from its current position.
     * This is typically called after inserting a new element at the end.
     * It compares the current node with its parent and swaps them if the heap property is violated.
     * Time Complexity: O(log N) in worst case (moves from leaf to root).
     */
    private void heapifyUp() {
        int currentIndex = heap.size() - 1; // Start from the last element (newly inserted).
        // While the current node has a parent AND its value is greater than its parent's value:
        while (hasParent(currentIndex) && parent(currentIndex) < heap.get(currentIndex)) {
            swap(currentIndex, getParentIndex(currentIndex)); // Swap current node with its parent.
            currentIndex = getParentIndex(currentIndex);      // Move up to the parent's position to continue checking.
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
        int max = heap.get(0); // Store the maximum element (root).
        heap.set(0, heap.get(heap.size() - 1)); // Move the last element to the root position.
        heap.remove(heap.size() - 1);           // Remove the last element (which is now duplicated at root).
        heapifyDown();                          // Restore heap property by moving the new root down.
        System.out.println("Extracted Max: " + max);
        return max;
    }

    /**
     * Restores the heap property by moving an element down the heap from the root.
     * This is typically called after removing the root element and replacing it with the last element.
     * It compares the current node with its children and swaps it with the larger child if the heap property is violated.
     * Time Complexity: O(log N) in worst case (moves from root to leaf).
     */
    private void heapifyDown() {
        int currentIndex = 0; // Start from the root (index 0).
        // While the current node has at least a left child (meaning it's not a leaf):
        while (hasLeftChild(currentIndex)) {
            int largerChildIndex = getLeftChildIndex(currentIndex); // Assume left child is the larger one initially.

            // If there's a right child AND it's larger than the left child, then the right child is the larger one.
            if (hasRightChild(currentIndex) && rightChild(currentIndex) > leftChild(currentIndex)) {
                largerChildIndex = getRightChildIndex(currentIndex);
            }

            // If the current node's value is greater than or equal to its largest child's value,
            // the heap property is satisfied at this node, so we can stop.
            if (heap.get(currentIndex) >= heap.get(largerChildIndex)) {
                break;
            } else { // Otherwise, swap the current node with its larger child.
                swap(currentIndex, largerChildIndex);
            }
            currentIndex = largerChildIndex; // Move down to the larger child's position to continue checking.
        }
    }

    /**
     * Builds a heap from a given list of elements efficiently.
     * This method initializes the heap with the given elements and then
     * performs heapifyDown operations from the last non-leaf node up to the root
     * to establish the heap property.
     * Time Complexity: O(N), where N is the number of elements.
     * @param elements The list of integer elements to build the heap from.
     */
    public void buildHeap(List<Integer> elements) {
        this.heap = new ArrayList<>(elements); // Copy elements to the heap's internal list.
        // Start heapifying down from the last non-leaf node up to the root (index 0).
        // The last non-leaf node is at index (size/2 - 1) in a 0-indexed array.
        for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
            heapifyDownFromIndex(i); // Call heapifyDown from a specific index.
        }
        System.out.println("Heap built from elements: " + elements);
    }

    /**
     * Helper method for buildHeap to heapify down from a specific starting index.
     * This is essentially the same logic as heapifyDown(), but it starts from a given index
     * instead of always from the root.
     * @param currentIndex The index from which to start heapifying down.
     */
    private void heapifyDownFromIndex(int currentIndex) {
        while (getLeftChildIndex(currentIndex) < heap.size()) { // Check for left child existence
            int largerChildIndex = getLeftChildIndex(currentIndex);

            // Check if right child exists and is larger than the left child
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
     * This shows the internal state of the heap as stored in the ArrayList.
     */
    public void printHeapArray() {
        if (isEmpty()) {
            System.out.println("Heap (array representation): []");
            return;
        }
        System.out.println("Heap (array representation): " + heap);
    }

    /**
     * Main method to demonstrate all basic Max-Heap operations.
     */
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();

        System.out.println("--- Initial State ---");
        System.out.println("Is heap empty? " + maxHeap.isEmpty()); // Expected: true
        System.out.println("Heap size: " + maxHeap.size());       // Expected: 0
        maxHeap.printHeapArray();

        System.out.println("\n--- Insert Operations ---");
        maxHeap.insert(10); // Heap: [10]
        maxHeap.printHeapArray();
        maxHeap.insert(5);  // Heap: [10, 5]
        maxHeap.printHeapArray();
        maxHeap.insert(15); // Heap: [15, 5, 10] (15 moves up from index 2 to 0)
        maxHeap.printHeapArray();
        maxHeap.insert(20); // Heap: [20, 15, 10, 5] (20 moves up from index 3 to 0)
        maxHeap.printHeapArray();
        maxHeap.insert(8);  // Heap: [20, 15, 10, 5, 8] (8 stays at index 4)
        maxHeap.printHeapArray();

        System.out.println("\n--- Peek Operation ---");
        maxHeap.peek(); // Expected: 20 (the current maximum element)

        System.out.println("\n--- Extract Max Operations ---");
        maxHeap.extractMax(); // Removes 20. Last element (8) moves to root, then heapifies down.
        maxHeap.printHeapArray(); // Expected: [15, 8, 10, 5] (order might vary slightly depending on heapifyDown path)
        System.out.println("Heap size: " + maxHeap.size()); // Expected: 4

        maxHeap.extractMax(); // Removes 15.
        maxHeap.printHeapArray(); // Expected: [10, 8, 5]
        System.out.println("Heap size: " + maxHeap.size()); // Expected: 3

        System.out.println("\n--- Building Heap from a List ---");
        List<Integer> initialElements = new ArrayList<>();
        initialElements.add(4);
        initialElements.add(1);
        initialElements.add(3);
        initialElements.add(2);
        initialElements.add(16);
        initialElements.add(9);
        initialElements.add(10);

        MaxHeap newHeap = new MaxHeap(); // Create a new heap instance
        newHeap.buildHeap(initialElements); // Build from the list
        newHeap.printHeapArray(); // Expected: [16, 4, 10, 2, 1, 9, 3] (after buildHeap, which is O(N))

        newHeap.peek(); // Expected: 16
        newHeap.extractMax(); // Removes 16
        newHeap.printHeapArray(); // Expected: [10, 4, 9, 2, 1, 3]

        System.out.println("\n--- Emptying Heap ---");
        while (!newHeap.isEmpty()) {
            newHeap.extractMax(); // Continuously extract max until empty
            newHeap.printHeapArray();
        }

        System.out.println("\n--- Testing Empty Heap Operations ---");
        System.out.println("Is newHeap empty? " + newHeap.isEmpty()); // Expected: true
        try {
            newHeap.peek(); // This should throw an exception
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            newHeap.extractMax(); // This should throw an exception
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
