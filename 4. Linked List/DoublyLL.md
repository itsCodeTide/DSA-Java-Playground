Alright, my fellow data structure explorers\! We've ventured into the realm of Linked Lists, understanding how they connect data like a treasure hunt with clues. We started with the basic "one-way street" of the Singly Linked List (SLL), where each item only knows who comes *next*.

Now, let's upgrade our chain\! What if we want to easily go *backwards* on our treasure hunt? What if, when we find a treasure, we also want a clue to where the *previous* treasure was, not just the next one?

Enter the **Doubly Linked List (DLL)** – it's like giving each of your treasure clues a "go back" button\!

-----

## Doubly Linked Lists: The Two-Way Street of Data

A **Doubly Linked List** is a sequence of elements where each element (called a **Node**) has not just one, but **two pointers** (or references):

1.  A pointer to the **next** `Node` in the sequence.
2.  A pointer to the **previous** `Node` in the sequence.

This two-way linking allows for efficient traversal in both forward and backward directions.

### The Upgraded Building Block: The `DoublyNode`

Our `Node` now gets an extra room\!

Each `DoublyNode` has three main parts:

  * **Data:** The actual piece of information you want to store.
  * **`next` Pointer:** A reference to the **next** `DoublyNode`.
  * **`prev` Pointer:** A reference to the **previous** `DoublyNode`.

Think of it like a train carriage that's specifically designed for two-way travel:
`[ Points to Previous Carriage (Prev) | Your Item (Data) | Points to Next Carriage (Next) ]`

The very first `DoublyNode` (the `Head`) will have its `prev` pointer pointing to `null` (because there's nothing before it). Similarly, the very last `DoublyNode` (the `Tail`) will have its `next` pointer pointing to `null` (because there's nothing after it).

#### Visualizing a Doubly Linked List:

```
      HEAD                                        TAIL
        |                                           |
        V                                           V
null <-- [ Data1 | prev | next ] <--> [ Data2 | prev | next ] <--> [ Data3 | prev | next ] --> null
```

You'll usually keep track of both the `Head` (first `Node`) and the `Tail` (last `Node`) in a Doubly Linked List for quicker access to both ends.

-----

### Why Choose a Doubly Linked List? (Advantages over SLL/Arrays)

  * **Bidirectional Traversal:** The biggest advantage\! You can move forwards and backwards through the list easily. This is super handy for things like "undo" functionalities or navigating lists in both directions.
  * **Efficient Deletion (of a given node):** If you have a direct reference to a `Node` you want to delete, you can remove it in $O(1)$ time. Why? Because you can easily access its `prev` and `next` nodes using the pointers already in the node you want to delete. In a Singly Linked List, if you want to delete a node, you first need to find its *previous* node, which often requires traversing from the `Head` ($O(N)$).
  * **Still Dynamic Size:** Like SLLs, DLLs can grow and shrink as needed, without fixed size limitations.

### The Trade-offs (Disadvantages compared to SLLs)

  * **More Memory:** Each `Node` requires an extra pointer (`prev`), consuming slightly more memory per node than an SLL. For very large lists with small data elements, this overhead can add up.
  * **More Complex Operations:** Every insertion or deletion operation requires updating *two* pointers (`next` and `prev`) instead of just one. This means your code will be a bit more intricate and prone to subtle bugs if not handled carefully.
  * **No Random Access:** Still no direct jump to the 5th element. You still have to traverse from `Head` or `Tail` ($O(N)$) to reach an element at a specific index.

-----

### Let's Build a Doubly Linked List in Java\!

We'll follow a similar structure to our SLL, but pay close attention to managing both `next` and `prev` pointers.

#### Step 1: The `DoublyNode` Class

This is our upgraded `Node` blueprint.

```java
// DoublyNode.java (Often an inner class of DoublyLinkedList)
class DoublyNode {
    int data;          // The data this node holds
    DoublyNode next;   // Reference to the next node
    DoublyNode prev;   // Reference to the previous node

    // Constructor to create a new DoublyNode
    public DoublyNode(int data) {
        this.data = data;
        this.next = null; // Initially points to nothing forward
        this.prev = null; // Initially points to nothing backward
    }
}
```

#### Step 2: The `DoublyLinkedList` Class

This class will manage the entire list, keeping track of both `head` and `tail` pointers.

```java
public class DoublyLinkedList {
    DoublyNode head; // Points to the first node in the list
    DoublyNode tail; // Points to the last node in the list
    int size;        // Keeps track of the number of nodes

    // Constructor to initialize an empty Doubly Linked List
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // --- Core Operations ---

    // 1. Check if the list is empty
    // Time Complexity: O(1)
    public boolean isEmpty() {
        return head == null; // If head is null, list is empty
    }

    // 2. Get the current size of the list
    // Time Complexity: O(1)
    public int getSize() {
        return size;
    }

    // 3. Adding a new node to the front (head) of the list
    // Time Complexity: O(1)
    public void addAtHead(int data) {
        DoublyNode newNode = new DoublyNode(data); // Create new node

        if (isEmpty()) { // Case 1: List is empty
            head = newNode;
            tail = newNode; // New node is both head and tail
        } else { // Case 2: List is not empty
            newNode.next = head; // New node points to the current head
            head.prev = newNode; // Current head's 'prev' points to new node
            head = newNode;      // New node becomes the new head
        }
        size++;
        System.out.println("Added " + data + " at head. List size: " + size);
    }

    // 4. Adding a new node to the end (tail) of the list
    // Time Complexity: O(1) (because we have a 'tail' pointer!)
    public void addAtTail(int data) {
        DoublyNode newNode = new DoublyNode(data); // Create new node

        if (isEmpty()) { // Case 1: List is empty
            head = newNode;
            tail = newNode; // New node is both head and tail
        } else { // Case 2: List is not empty
            tail.next = newNode; // Current tail's 'next' points to new node
            newNode.prev = tail; // New node's 'prev' points to current tail
            tail = newNode;      // New node becomes the new tail
        }
        size++;
        System.out.println("Added " + data + " at tail. List size: " + size);
    }

    // 5. Adding a new node at a specific index
    // Time Complexity: O(N) because we might need to traverse to the index
    public void addAtIndex(int data, int index) {
        if (index < 0 || index > size) { // Basic validation
            System.out.println("Error: Index " + index + " is out of bounds for size " + size);
            return;
        }
        if (index == 0) { // If index is 0, add at head
            addAtHead(data);
            return;
        }
        if (index == size) { // If index is size, add at tail
            addAtTail(data);
            return;
        }

        DoublyNode newNode = new DoublyNode(data);
        DoublyNode current = head;
        // Traverse to the node *before* the desired insertion point
        // For example, if index is 2, we want to stop at index 1 (the node at index-1)
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        // 'current' is now the node before the insertion point
        // 'current.next' is the node that was originally at 'index'

        newNode.next = current.next;    // New node points to the node originally at 'index'
        newNode.prev = current;         // New node's 'prev' points to 'current'

        current.next.prev = newNode;    // The node originally at 'index' now has its 'prev' pointing to new node
        current.next = newNode;         // 'current' now points to new node

        size++;
        System.out.println("Added " + data + " at index " + index + ". List size: " + size);
    }
    
    // 6. Deleting a node from the head
    // Time Complexity: O(1)
    public void deleteFromHead() {
        if (isEmpty()) {
            System.out.println("List is empty. Cannot delete from head.");
            return;
        }
        int deletedData = head.data;
        if (head == tail) { // If there's only one node
            head = null;
            tail = null;
        } else {
            head = head.next;   // Head moves to the next node
            head.prev = null;   // The new head's 'prev' must be null
        }
        size--;
        System.out.println("Deleted " + deletedData + " from head. List size: " + size);
    }

    // 7. Deleting a node from the tail
    // Time Complexity: O(1) (because we have a 'tail' pointer!)
    public void deleteFromTail() {
        if (isEmpty()) {
            System.out.println("List is empty. Cannot delete from tail.");
            return;
        }
        int deletedData = tail.data;
        if (head == tail) { // If there's only one node
            head = null;
            tail = null;
        } else {
            tail = tail.prev;   // Tail moves to the previous node
            tail.next = null;   // The new tail's 'next' must be null
        }
        size--;
        System.out.println("Deleted " + deletedData + " from tail. List size: " + size);
    }

    // 8. Deleting a node at a specific index
    // Time Complexity: O(N)
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) { // Index validation
            System.out.println("Error: Index " + index + " is out of bounds for size " + size);
            return;
        }
        if (index == 0) { // Delete from head
            deleteFromHead();
            return;
        }
        if (index == size - 1) { // Delete from tail
            deleteFromTail();
            return;
        }

        DoublyNode current = head;
        for (int i = 0; i < index; i++) { // Traverse to the node to be deleted
            current = current.next;
        }
        // 'current' is the node to be deleted
        
        current.prev.next = current.next; // Node before 'current' now points to node after 'current'
        current.next.prev = current.prev; // Node after 'current' now points to node before 'current'
        
        // Help garbage collector by nullifying pointers (optional, but good practice)
        current.next = null;
        current.prev = null;
        
        size--;
        System.out.println("Deleted node at index " + index + ". List size: " + size);
    }

    // 9. Traversing and printing the list (forward)
    // Time Complexity: O(N)
    public void printListForward() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        DoublyNode current = head;
        System.out.print("Forward List: ");
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // 10. Traversing and printing the list (backward)
    // Time Complexity: O(N)
    public void printListBackward() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        DoublyNode current = tail; // Start from the tail
        System.out.print("Backward List: ");
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev; // Move backwards
        }
        System.out.println("null");
    }

    // --- Main method to test our DoublyLinkedList ---
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        System.out.println("Is list empty? " + dll.isEmpty()); // true

        dll.addAtHead(10); // List: 10
        dll.printListForward();
        dll.printListBackward();

        dll.addAtTail(30); // List: 10 <-> 30
        dll.printListForward();
        dll.printListBackward();

        dll.addAtHead(5);  // List: 5 <-> 10 <-> 30
        dll.printListForward();
        dll.printListBackward();

        dll.addAtIndex(20, 2); // List: 5 <-> 10 <-> 20 <-> 30
        dll.printListForward();
        dll.printListBackward();

        dll.addAtIndex(0, 0); // List: 0 <-> 5 <-> 10 <-> 20 <-> 30
        dll.printListForward();
        dll.printListBackward();
        
        dll.addAtIndex(40, dll.getSize()); // List: 0 <-> 5 <-> 10 <-> 20 <-> 30 <-> 40
        dll.printListForward();
        dll.printListBackward();

        System.out.println("Current list size: " + dll.getSize()); // 6

        System.out.println("\n--- Deletion Demos ---");
        dll.deleteFromHead(); // List: 5 <-> 10 <-> 20 <-> 30 <-> 40
        dll.printListForward();
        dll.printListBackward();

        dll.deleteFromTail(); // List: 5 <-> 10 <-> 20 <-> 30
        dll.printListForward();
        dll.printListBackward();

        dll.deleteAtIndex(1); // Delete 10 (at index 1). List: 5 <-> 20 <-> 30
        dll.printListForward();
        dll.printListBackward();

        System.out.println("Final list size: " + dll.getSize()); // 3
        
        // Delete remaining
        dll.deleteFromHead();
        dll.deleteFromHead();
        dll.deleteFromHead();
        dll.printListForward(); // Should be empty
        dll.printListBackward(); // Should be empty
        System.out.println("Final list size: " + dll.getSize()); // 0
        dll.deleteFromHead(); // Test deleting from empty list
    }
}
```

### Advanced Concepts/Problems for Doubly Linked Lists

DLLs solve some problems more easily than SLLs.

#### 1\. Reversing a Doubly Linked List

This is actually simpler than reversing a Singly Linked List because you don't need a `nextTemp` pointer\! You just swap `next` and `prev` for each node, and then swap `head` and `tail`.

```java
// Method within DoublyLinkedList class
public void reverseList() {
    if (head == null || head.next == null) {
        System.out.println("List is too short to reverse or is empty.");
        return;
    }

    DoublyNode current = head;
    DoublyNode temp = null; // Temporary node to swap next and prev pointers

    // Traverse the list and swap next and prev pointers for each node
    while (current != null) {
        temp = current.prev;     // Store current.prev
        current.prev = current.next; // Set current.prev to current.next
        current.next = temp;     // Set current.next to old current.prev (now stored in temp)
        current = current.prev;  // Move to the next node (which was original previous)
    }

    // After the loop, the original head is the new tail, and original tail is new head.
    temp = head; // Use temp to swap head and tail
    head = tail;
    tail = temp;
    
    System.out.println("Doubly List reversed!");
    printListForward(); // Print forward to see the reversed order
}
```

#### 2\. Removing Duplicates from a Doubly Linked List

If your DLL is sorted, removing duplicates becomes straightforward because duplicates are adjacent. If it's unsorted, you might need a `HashSet` to keep track of seen elements.

**For a sorted DLL:**

```java
// Method within DoublyLinkedList class (assuming sorted list)
public void removeDuplicatesSorted() {
    if (head == null || head.next == null) {
        System.out.println("No duplicates to remove or list is too short.");
        return;
    }

    DoublyNode current = head;
    while (current != null && current.next != null) {
        if (current.data == current.next.data) {
            // Duplicate found: current.next is the duplicate node
            DoublyNode duplicateNode = current.next;
            
            // Bypass the duplicateNode
            current.next = duplicateNode.next;
            if (duplicateNode.next != null) { // If not the last duplicate
                duplicateNode.next.prev = current;
            } else { // If the duplicate was the tail
                tail = current; // Update tail
            }
            // Help garbage collector
            duplicateNode.next = null;
            duplicateNode.prev = null;
            size--;
            System.out.println("Removed duplicate: " + duplicateNode.data);
        } else {
            current = current.next; // Move to the next node only if no duplicate was removed
        }
    }
    System.out.println("Duplicates removal complete (sorted list).");
    printListForward();
}
```

-----

### When to Use a Doubly Linked List?

You'd typically consider a Doubly Linked List over a Singly Linked List when:

  * **You frequently need to traverse the list in both directions (forward and backward).**
  * **You often need to delete a specific `Node` when you already have a reference to it.** (e.g., in caches or memory management where you have direct pointers to elements).
  * **You need efficient insertions and deletions at both ends of the list (Head and Tail).**
  * **Implementing other data structures that benefit from two-way linking,** like a Least Recently Used (LRU) cache (where you might need to move items to the front or delete from the middle).

### A Final Thought: The "Why" is as Important as the "How"

Understanding the mechanics of a Doubly Linked List – how pointers are manipulated during insertions and deletions – is crucial. But equally important is understanding *when* to use it. If your application doesn't require backward traversal or frequent middle deletions with a direct reference, the simpler Singly Linked List (or even an `ArrayList` for many common cases) might be more appropriate due to its lower memory overhead and simpler code.

You've now seen the full power of linked structures with the Doubly Linked List. Keep practicing these pointer manipulations, and you'll master this fundamental data structure\!
