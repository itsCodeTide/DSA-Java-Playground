// Node.java (This is usually an inner class, but we'll put it here for clarity)
class Node {
    int data;     // This is where we store the actual value (e.g., a number, a String, etc.)
                  // We'll use 'int' for simplicity, but it could be any data type or even an object.
    Node next;    // This is the 'pointer' or 'reference' to the next Node in the sequence.
                  // If this is null, it means this is the last node in the list.

    // Constructor for the Node class
    public Node(int data) {
        this.data = data; // Initialize the data part of the node
        this.next = null; // A new node, when created, doesn't point to anything yet.
                          // It will be linked later by list operations.
    }
}
public class SLLBasicOperation {
    // The 'head' pointer: This is the reference to the first node of our linked list.
    // If 'head' is null, it means the list is empty.
    Node head;

    // We'll also keep track of the 'size' of the list. This is optional but makes
    // getting the size an O(1) operation, instead of having to traverse the list.
    int size;

    // Constructor for our SinglyLinkedList
    public SinglyLinkedList() {
        this.head = null; // A brand new list starts empty!
        this.size = 0;    // And its size is 0.
    }

    // --- Basic Operations on a Singly Linked List ---

    // Operation 1: Check if the list is empty
    // Time Complexity: O(1) - constant time, super fast!
    // We just look at the head pointer.
    public boolean isEmpty() {
        return head == null;
    }

    // Operation 2: Get the current number of elements in the list
    // Time Complexity: O(1) - constant time, if we maintain a 'size' variable.
    // If we didn't have 'size', we'd have to traverse and count (O(N)).
    public int getSize() {
        return size;
    }

    // Operation 3: Add a new node to the front (head) of the list
    // This is also often called 'addFirst' or 'prepend'.
    // Time Complexity: O(1) - constant time. We just change a few pointers at the beginning.
    public void addAtHead(int data) {
        Node newNode = new Node(data); // 1. Create a new Node with the given data.
        
        newNode.next = head;           // 2. Make the new node point to whatever the current 'head' is pointing to.
                                       //    (If list was empty, head was null, so newNode.next becomes null)
        head = newNode;                // 3. Make the 'head' pointer now point to our new node.
                                       //    This effectively puts the new node at the beginning.
        size++;                        // 4. Increment the size of the list.
        System.out.println("Added " + data + " at head. List size: " + size);
    }

    // Operation 4: Add a new node to the end (tail) of the list
    // This is also often called 'addLast' or 'append'.
    // Time Complexity: O(N) - linear time. In the worst case, we have to traverse the entire list
    // to find the last node.
    public void addAtTail(int data) {
        Node newNode = new Node(data); // 1. Create a new Node.

        if (isEmpty()) {               // 2. Special case: If the list is empty,
            head = newNode;            //    the new node becomes the head (and only node).
        } else {                       // 3. If the list is NOT empty:
            Node current = head;       //    a. Start a 'current' pointer from the head.
            while (current.next != null) { // b. Traverse the list until 'current' is the last node.
                                           //    (The last node is the one whose 'next' is null)
                current = current.next;    //    Move 'current' to the next node.
            }
            current.next = newNode;        // c. Make the original last node point to our new node.
        }
        size++;                        // 4. Increment the size.
        System.out.println("Added " + data + " at tail. List size: " + size);
    }

    // Operation 5: Add a new node at a specific index
    // Time Complexity: O(N) - linear time, as we might need to traverse to the insertion point.
    public void addAtIndex(int data, int index) {
        // 1. Basic validation for the index.
        // Index must be within [0, size] (size is allowed for adding at the very end).
        if (index < 0 || index > size) {
            System.out.println("Error: Index " + index + " is out of bounds for current size " + size);
            return;
        }

        // 2. Handle edge cases: Adding at head or tail using existing methods.
        if (index == 0) {
            addAtHead(data);
            return;
        }
        if (index == size) { // If index is equal to the current size, it means add to tail
            addAtTail(data);
            return;
        }

        // 3. For adding in the middle:
        Node newNode = new Node(data);      // a. Create the new Node.
        Node current = head;                // b. Start 'current' from the head.

        // c. Traverse to the node *BEFORE* the desired insertion point.
        //    (e.g., if you want to insert at index 2, you stop at index 1).
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        // At this point, 'current' is the node that will come *before* our newNode.
        
        newNode.next = current.next;    // d. Make the new node point to what 'current' was pointing to.
                                        //    (This links the new node to the rest of the list).
        current.next = newNode;         // e. Make 'current' point to the new node.
                                        //    (This inserts the new node into the chain).
        size++;                         // 4. Increment size.
        System.out.println("Added " + data + " at index " + index + ". List size: " + size);
    }

    // Operation 6: Delete a node from the front (head) of the list
    // This is also often called 'deleteFirst'.
    // Time Complexity: O(1) - constant time.
    public void deleteFromHead() {
        if (isEmpty()) { // 1. Check for empty list (cannot delete from an empty list).
            System.out.println("List is empty. Cannot delete from head.");
            return;
        }
        int deletedData = head.data; // Store data of the node being deleted (optional, for output)
        head = head.next;           // 2. Make 'head' point to the next node.
                                    //    The original head node is now unreferenced and will be
                                    //    cleaned up by Java's garbage collector.
        size--;                     // 3. Decrement size.
        System.out.println("Deleted " + deletedData + " from head. List size: " + size);
    }

    // Operation 7: Delete a node from the end (tail) of the list
    // This is also often called 'deleteLast'.
    // Time Complexity: O(N) - linear time. We need to find the second-to-last node.
    public void deleteFromTail() {
        if (isEmpty()) { // 1. Check for empty list.
            System.out.println("List is empty. Cannot delete from tail.");
            return;
        }
        
        if (head.next == null) { // 2. Special case: If there's only one node in the list.
            int deletedData = head.data;
            head = null;         // List becomes empty.
            size--;
            System.out.println("Deleted " + deletedData + " from tail. List size: " + size);
            return;
        }

        // 3. For multiple nodes:
        Node current = head;      // a. Start 'current' from the head.
        Node previous = null;     // b. Also keep a 'previous' pointer, initially null.

        // c. Traverse until 'current' is the last node.
        //    (This means 'current.next' is null).
        //    'previous' will end up being the second-to-last node.
        while (current.next != null) {
            previous = current;      // Move 'previous' to 'current's position.
            current = current.next;  // Move 'current' to the next node.
        }
        // At this point, 'current' is the last node, and 'previous' is the second-to-last.
        
        int deletedData = current.data; // Store data of the node being deleted.
        previous.next = null;           // d. Make the second-to-last node (previous) point to null.
                                        //    This severs the link to the original last node.
        size--;                         // 4. Decrement size.
        System.out.println("Deleted " + deletedData + " from tail. List size: " + size);
    }

    // Operation 8: Delete a node by its value
    // Time Complexity: O(N) - linear time. We might need to search the entire list.
    public void deleteByValue(int data) {
        if (isEmpty()) { // 1. Check for empty list.
            System.out.println("List is empty. Cannot delete value " + data + ".");
            return;
        }

        // 2. Special case: The head node contains the data to be deleted.
        if (head.data == data) {
            deleteFromHead(); // Reuse existing method!
            return;
        }

        // 3. For deleting a node somewhere in the middle or at the tail:
        Node current = head;       // a. Start 'current' from head.
        Node previous = null;      // b. Keep a 'previous' pointer.

        // c. Traverse the list until 'current' is the node containing the 'data'
        //    OR 'current' becomes null (meaning data not found).
        while (current != null && current.data != data) {
            previous = current;      // Move 'previous' to 'current's position.
            current = current.next;  // Move 'current' to the next node.
        }

        if (current == null) { // 4. If 'current' is null here, it means the data was not found.
            System.out.println("Value " + data + " not found in the list. No deletion performed.");
        } else { // 5. If 'current' is NOT null, it points to the node we want to delete.
            int deletedData = current.data; // Store data of deleted node.
            previous.next = current.next;   // Make the 'previous' node bypass 'current'
                                            // and point directly to what 'current' was pointing to.
            // Help garbage collector by nullifying 'current's next pointer (optional)
            current.next = null; 
            size--;                         // 6. Decrement size.
            System.out.println("Deleted " + deletedData + " by value. List size: " + size);
        }
    }

    // Operation 9: Search for a specific value in the list
    // Time Complexity: O(N) - linear time, as we might need to check every node.
    public boolean search(int data) {
        Node current = head; // 1. Start from the head.
        while (current != null) { // 2. Traverse the list until you reach the end.
            if (current.data == data) { // 3. Check if the current node's data matches.
                return true; // If found, immediately return true!
            }
            current = current.next; // 4. Move to the next node.
        }
        return false; // 5. If the loop finishes without finding the data, it's not in the list.
    }

    // Operation 10: Traverse and print all elements in the list
    // Time Complexity: O(N) - linear time, as we visit each node once.
    public void printList() {
        if (isEmpty()) { // 1. Handle empty list case.
            System.out.println("List is empty.");
            return;
        }
        Node current = head; // 2. Start from the head.
        System.out.print("List: ");
        while (current != null) { // 3. Loop until 'current' becomes null (end of list).
            System.out.print(current.data + " -> ");
            current = current.next; // 4. Move to the next node.
        }
        System.out.println("null"); // 5. Conventionally indicate the end of the list.
    }

    // --- Main Method to Test Our Singly Linked List Operations ---
    public static void main(String[] args) {
        SinglyLinkedList myList = new SinglyLinkedList();

        System.out.println("--- Initial State ---");
        System.out.println("Is list empty? " + myList.isEmpty()); // Expected: true
        System.out.println("List size: " + myList.getSize());     // Expected: 0
        myList.printList(); // Expected: List is empty.

        System.out.println("\n--- Adding Elements ---");
        myList.addAtHead(10); // List: 10 -> null
        myList.printList();

        myList.addAtTail(30); // List: 10 -> 30 -> null
        myList.printList();

        myList.addAtHead(5);  // List: 5 -> 10 -> 30 -> null
        myList.printList();

        myList.addAtIndex(20, 2); // Insert 20 at index 2. List: 5 -> 10 -> 20 -> 30 -> null
        myList.printList();

        myList.addAtIndex(0, 0);  // Insert 0 at index 0. List: 0 -> 5 -> 10 -> 20 -> 30 -> null
        myList.printList();

        myList.addAtIndex(40, myList.getSize()); // Insert 40 at the end. List: 0 -> 5 -> 10 -> 20 -> 30 -> 40 -> null
        myList.printList();

        System.out.println("Current list size: " + myList.getSize()); // Expected: 6
        System.out.println("Is list empty? " + myList.isEmpty());    // Expected: false

        System.out.println("\n--- Searching Elements ---");
        System.out.println("Search for 10: " + myList.search(10));   // Expected: true
        System.out.println("Search for 25: " + myList.search(25));   // Expected: false
        System.out.println("Search for 0: " + myList.search(0));     // Expected: true
        System.out.println("Search for 40: " + myList.search(40));   // Expected: true

        System.out.println("\n--- Deleting Elements ---");
        myList.deleteFromHead(); // Delete 0. List: 5 -> 10 -> 20 -> 30 -> 40 -> null
        myList.printList();

        myList.deleteFromTail(); // Delete 40. List: 5 -> 10 -> 20 -> 30 -> null
        myList.printList();

        myList.deleteByValue(20); // Delete 20. List: 5 -> 10 -> 30 -> null
        myList.printList();

        myList.deleteByValue(5);  // Delete 5 (head). List: 10 -> 30 -> null
        myList.printList();

        myList.deleteByValue(30); // Delete 30 (tail). List: 10 -> null
        myList.printList();

        myList.deleteFromTail(); // Delete 10 (last node). List: empty
        myList.printList();
        System.out.println("Is list empty? " + myList.isEmpty()); // Expected: true
        System.out.println("List size: " + myList.getSize());     // Expected: 0

        // Test deletions on an empty list
        myList.deleteFromHead(); // Expected: Cannot delete from head.
        myList.deleteFromTail(); // Expected: Cannot delete from tail.
        myList.deleteByValue(99); // Expected: Value not found.
    }
}
