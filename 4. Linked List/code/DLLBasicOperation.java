// DoublyNode.java (Typically an inner class within your DoublyLinkedList)
class DoublyNode {
    int data;          // The actual value stored in this node.
    DoublyNode next;   // Reference to the next node in the sequence.
    DoublyNode prev;   // Reference to the previous node in the sequence (the new addition!).

    // Constructor for the DoublyNode class
    public DoublyNode(int data) {
        this.data = data;  // Initialize the data.
        this.next = null;  // A new node doesn't point forward yet.
        this.prev = null;  // A new node doesn't point backward yet.
                           // These will be set when the node is added to the list.
    }
}
public class DoublyLinkedList {
    // 'head' pointer: Points to the very first node. null if the list is empty.
    DoublyNode head;
    // 'tail' pointer: Points to the very last node. null if the list is empty.
    DoublyNode tail;
    // 'size': Keeps track of the current number of nodes in the list.
    int size;

    // Constructor for our DoublyLinkedList
    public DoublyLinkedList() {
        this.head = null; // New list starts empty.
        this.tail = null; // Tail is also null.
        this.size = 0;    // Size is zero.
    }

    // --- Basic Operations on a Doubly Linked List ---

    // Operation 1: Check if the list is empty
    // Time Complexity: O(1)
    // We just look at the head pointer.
    public boolean isEmpty() {
        return head == null;
    }

    // Operation 2: Get the current number of elements in the list
    // Time Complexity: O(1) (because we maintain a 'size' variable)
    public int getSize() {
        return size;
    }

    // Operation 3: Add a new node to the front (head) of the list
    // Time Complexity: O(1) - Super fast!
    public void addAtHead(int data) {
        DoublyNode newNode = new DoublyNode(data); // 1. Create a new Node.

        if (isEmpty()) { // 2. Special Case: If the list is completely empty.
            head = newNode; // New node is both the head...
            tail = newNode; // ...and the tail.
        } else { // 3. General Case: If the list already has nodes.
            newNode.next = head; // a. New node's 'next' points to the current head.
            head.prev = newNode; // b. The *current head's* 'prev' pointer now points to the new node. (Crucial for two-way link!)
            head = newNode;      // c. New node becomes the new head.
        }
        size++;                  // 4. Increment the size.
        System.out.println("Added " + data + " at head. List size: " + size);
    }

    // Operation 4: Add a new node to the end (tail) of the list
    // Time Complexity: O(1) - Also super fast because we have a 'tail' pointer!
    public void addAtTail(int data) {
        DoublyNode newNode = new DoublyNode(data); // 1. Create a new Node.

        if (isEmpty()) { // 2. Special Case: If the list is completely empty.
            head = newNode; // New node is both the head...
            tail = newNode; // ...and the tail. (Same as addAtHead for empty list)
        } else { // 3. General Case: If the list already has nodes.
            tail.next = newNode; // a. Current tail's 'next' points to the new node.
            newNode.prev = tail; // b. New node's 'prev' pointer points to the current tail. (Crucial for two-way link!)
            tail = newNode;      // c. New node becomes the new tail.
        }
        size++;                  // 4. Increment the size.
        System.out.println("Added " + data + " at tail. List size: " + size);
    }

    // Operation 5: Add a new node at a specific index
    // Time Complexity: O(N) - We might have to traverse to find the insertion spot.
    // Optimization: If index is in first half, traverse from head; if in second half, traverse from tail.
    public void addAtIndex(int data, int index) {
        // 1. Basic validation for the index.
        // Index must be within [0, size] (size is allowed for adding at the very end).
        if (index < 0 || index > size) {
            System.out.println("Error: Index " + index + " is out of bounds for current size " + size);
            return;
        }

        // 2. Handle edge cases: Adding at head or tail using existing O(1) methods.
        if (index == 0) {
            addAtHead(data);
            return;
        }
        if (index == size) {
            addAtTail(data);
            return;
        }

        // 3. For adding in the middle:
        DoublyNode newNode = new DoublyNode(data); // a. Create the new Node.
        DoublyNode current = head;                 // b. Start 'current' from the head.

        // c. Traverse to the node *BEFORE* the desired insertion point.
        //    (e.g., if you want to insert at index 2, you stop at index 1).
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        // At this point, 'current' is the node that will come *before* our newNode.
        // And 'current.next' is the node that will come *after* our newNode.

        newNode.next = current.next;    // d. New node's 'next' points to the node that was originally at 'index'.
        newNode.prev = current;         // e. New node's 'prev' points to 'current'.

        current.next.prev = newNode;    // f. The node originally at 'index' now has its 'prev' pointing to the new node.
                                        //    (Connecting the right side to the new node).
        current.next = newNode;         // g. 'current' now points to the new node.
                                        //    (Connecting the left side to the new node).
        size++;                         // 4. Increment size.
        System.out.println("Added " + data + " at index " + index + ". List size: " + size);
    }
    
    // Operation 6: Delete a node from the front (head) of the list
    // Time Complexity: O(1) - Constant time.
    public void deleteFromHead() {
        if (isEmpty()) { // 1. Check for empty list.
            System.out.println("List is empty. Cannot delete from head.");
            return;
        }
        int deletedData = head.data; // Store data of node being deleted (optional).

        if (head == tail) { // 2. Special Case: If there's only one node in the list.
            head = null; // List becomes empty.
            tail = null;
        } else { // 3. General Case: Multiple nodes.
            head = head.next;   // a. Head pointer moves to the next node.
            head.prev = null;   // b. The *new* head's 'prev' pointer must be set to null. (Important!)
        }
        size--;                 // 4. Decrement size.
        System.out.println("Deleted " + deletedData + " from head. List size: " + size);
    }

    // Operation 7: Delete a node from the end (tail) of the list
    // Time Complexity: O(1) - Constant time, thanks to the 'tail' pointer!
    public void deleteFromTail() {
        if (isEmpty()) { // 1. Check for empty list.
            System.out.println("List is empty. Cannot delete from tail.");
            return;
        }
        int deletedData = tail.data; // Store data of node being deleted (optional).

        if (head == tail) { // 2. Special Case: If there's only one node.
            head = null; // List becomes empty.
            tail = null;
        } else { // 3. General Case: Multiple nodes.
            tail = tail.prev;   // a. Tail pointer moves to the previous node (the new tail).
            tail.next = null;   // b. The *new* tail's 'next' pointer must be set to null. (Important!)
        }
        size--;                 // 4. Decrement size.
        System.out.println("Deleted " + deletedData + " from tail. List size: " + size);
    }

    // Operation 8: Delete a node at a specific index
    // Time Complexity: O(N) - We might need to traverse to the node to be deleted.
    public void deleteAtIndex(int index) {
        // 1. Index validation.
        if (index < 0 || index >= size) { // Index must be within [0, size-1] for deletion.
            System.out.println("Error: Index " + index + " is out of bounds for current size " + size);
            return;
        }

        // 2. Handle edge cases: deleting head or tail using existing O(1) methods.
        if (index == 0) {
            deleteFromHead();
            return;
        }
        if (index == size - 1) {
            deleteFromTail();
            return;
        }

        // 3. For deleting a node in the middle:
        DoublyNode current = head; // a. Start 'current' from head.

        // b. Traverse to the node that needs to be deleted.
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        // At this point, 'current' is the node to be deleted.
        // 'current.prev' is the node before it.
        // 'current.next' is the node after it.

        int deletedData = current.data; // Store data (optional).

        current.prev.next = current.next; // c. The node *before* 'current' now points to the node *after* 'current'.
        current.next.prev = current.prev; // d. The node *after* 'current' now points to the node *before* 'current'.
                                         //    (These two lines bypass 'current' from both sides).

        // Optional: Help garbage collector by nullifying deleted node's pointers.
        current.next = null;
        current.prev = null;
        
        size--; // 4. Decrement size.
        System.out.println("Deleted " + deletedData + " at index " + index + ". List size: " + size);
    }
    
    // Operation 9: Search for a specific value in the list (forward traversal)
    // Time Complexity: O(N) - Must check up to N nodes.
    public boolean search(int data) {
        DoublyNode current = head; // 1. Start from the head.
        while (current != null) { // 2. Traverse until the end.
            if (current.data == data) { // 3. Check data.
                return true; // Found!
            }
            current = current.next; // 4. Move forward.
        }
        return false; // Not found.
    }

    // Operation 10: Traverse and print all elements in the list (forward direction)
    // Time Complexity: O(N)
    public void printListForward() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        DoublyNode current = head; // Start from the head.
        System.out.print("Forward List: ");
        while (current != null) { // Loop until we hit null (the end).
            System.out.print(current.data + " <-> ");
            current = current.next; // Move forward.
        }
        System.out.println("null"); // Indicate the end.
    }

    // Operation 11: Traverse and print all elements in the list (backward direction)
    // Time Complexity: O(N)
    public void printListBackward() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        DoublyNode current = tail; // Start from the tail.
        System.out.print("Backward List: ");
        while (current != null) { // Loop until we hit null (the beginning).
            System.out.print(current.data + " <-> ");
            current = current.prev; // Move backward.
        }
        System.out.println("null"); // Indicate the beginning.
    }

    // --- Main Method to Test Our Doubly Linked List Operations ---
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        System.out.println("--- Initial State ---");
        System.out.println("Is list empty? " + dll.isEmpty()); // Expected: true
        System.out.println("List size: " + dll.getSize());     // Expected: 0
        dll.printListForward();
        dll.printListBackward();

        System.out.println("\n--- Adding Elements ---");
        dll.addAtHead(10); // List: 10
        dll.printListForward();
        dll.printListBackward();

        dll.addAtTail(30); // List: 10 <-> 30
        dll.printListForward();
        dll.printListBackward();

        dll.addAtHead(5);  // List: 5 <-> 10 <-> 30
        dll.printListForward();
        dll.printListBackward();

        dll.addAtIndex(20, 2); // Insert 20 at index 2. List: 5 <-> 10 <-> 20 <-> 30
        dll.printListForward();
        dll.printListBackward();

        dll.addAtIndex(0, 0);  // Insert 0 at index 0. List: 0 <-> 5 <-> 10 <-> 20 <-> 30
        dll.printListForward();
        dll.printListBackward();

        dll.addAtIndex(40, dll.getSize()); // Insert 40 at the end. List: 0 <-> 5 <-> 10 <-> 20 <-> 30 <-> 40
        dll.printListForward();
        dll.printListBackward();

        System.out.println("Current list size: " + dll.getSize()); // Expected: 6

        System.out.println("\n--- Searching Elements ---");
        System.out.println("Search for 10: " + dll.search(10));   // Expected: true
        System.out.println("Search for 25: " + dll.search(25));   // Expected: false

        System.out.println("\n--- Deleting Elements ---");
        dll.deleteFromHead(); // Delete 0. List: 5 <-> 10 <-> 20 <-> 30 <-> 40
        dll.printListForward();
        dll.printListBackward();

        dll.deleteFromTail(); // Delete 40. List: 5 <-> 10 <-> 20 <-> 30
        dll.printListForward();
        dll.printListBackward();

        dll.deleteAtIndex(1); // Delete 10 (at index 1). List: 5 <-> 20 <-> 30
        dll.printListForward();
        dll.printListBackward();

        dll.deleteFromHead(); // Delete 5. List: 20 <-> 30
        dll.printListForward();
        dll.printListBackward();
        
        dll.deleteFromTail(); // Delete 30. List: 20
        dll.printListForward();
        dll.printListBackward();

        dll.deleteFromHead(); // Delete 20. List: empty
        dll.printListForward();
        dll.printListBackward();
        System.out.println("Is list empty? " + dll.isEmpty()); // Expected: true
        System.out.println("List size: " + dll.getSize());     // Expected: 0

        // Test deletions on an empty list
        dll.deleteFromHead(); // Expected: Cannot delete from head.
        dll.deleteFromTail(); // Expected: Cannot delete from tail.
        dll.deleteAtIndex(0); // Expected: Index out of bounds.
    }
}
