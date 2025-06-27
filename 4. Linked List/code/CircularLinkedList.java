// Node.java (Often an inner class within your CircularLinkedList)
class Node {
    int data;     // The actual value stored in this node.
    Node next;    // Reference to the next node in the sequence.

    // Constructor for the Node class
    public Node(int data) {
        this.data = data;
        this.next = null; // A newly created node doesn't point to anything initially.
    }
}
public class CircularLinkedList {
    // 'last' pointer: Points to the last node in the list.
    // From 'last', you can always find the head (head = last.next).
    Node last; 
    int size; // Keeps track of the number of nodes in the list.

    // Constructor for an empty Circular Linked List
    public CircularLinkedList() {
        this.last = null; // An empty list has no 'last' node.
        this.size = 0;    // And its size is 0.
    }

    // --- Basic Operations on a Circular Linked List ---

    // Operation 1: Check if the list is empty
    // Time Complexity: O(1)
    // We just check if the 'last' pointer is null.
    public boolean isEmpty() {
        return last == null;
    }

    // Operation 2: Get the current number of elements in the list
    // Time Complexity: O(1) (because we maintain a 'size' variable)
    public int getSize() {
        return size;
    }

    // Operation 3: Add a new node to the front (head) of the list
    // Time Complexity: O(1) - Very efficient!
    public void addFirst(int data) {
        Node newNode = new Node(data); // 1. Create a new Node.

        if (isEmpty()) { // 2. Special Case: If the list is completely empty.
            last = newNode;        // New node is the only node, so it's 'last'.
            newNode.next = newNode; // It points to itself to form a circle of one node.
        } else { // 3. General Case: If the list already has nodes.
            newNode.next = last.next; // a. New node's 'next' points to the current head (which is `last.next`).
            last.next = newNode;      // b. The current `last` node's 'next' now points to the new node,
                                      //    making the new node the head.
        }
        size++;                       // 4. Increment the size.
        System.out.println("Added " + data + " at first. List size: " + size);
    }

    // Operation 4: Add a new node to the end (tail) of the list
    // Time Complexity: O(1) - Very efficient!
    public void addLast(int data) {
        Node newNode = new Node(data); // 1. Create a new Node.

        if (isEmpty()) { // 2. Special Case: If the list is completely empty.
            addFirst(data); // Re-use the addFirst logic for convenience (it handles the single node case).
            return;
        } else { // 3. General Case: If the list already has nodes.
            newNode.next = last.next; // a. New node's 'next' points to the current head (which is `last.next`).
            last.next = newNode;      // b. The current `last` node's 'next' now points to the new node.
            last = newNode;           // c. The new node becomes the new `last` node.
        }
        size++;                       // 4. Increment the size.
        System.out.println("Added " + data + " at last. List size: " + size);
    }
    
    // Operation 5: Delete a node from the front (head) of the list
    // Time Complexity: O(1) - Constant time.
    public void deleteFirst() {
        if (isEmpty()) { // 1. Check for empty list.
            System.out.println("List is empty. Cannot delete from first.");
            return;
        }
        int deletedData = last.next.data; // Store data of the head node (last.next) for output.
        
        if (size == 1) { // 2. Special Case: If there's only one node in the list.
            last = null; // List becomes empty.
        } else { // 3. General Case: Multiple nodes.
            last.next = last.next.next; // The `last` node's `next` pointer now bypasses the old head
                                       // and points to the new head (the second node).
        }
        size--;                        // 4. Decrement the size.
        System.out.println("Deleted " + deletedData + " from first. List size: " + size);
    }

    // Operation 6: Delete a node from the end (tail) of the list
    // Time Complexity: O(N) - Linear time. Even with a `last` pointer, to delete the last node,
    // you need to find the *second-to-last* node to update its `next` pointer to point to the head.
    // This requires traversal.
    public void deleteLast() {
        if (isEmpty()) { // 1. Check for empty list.
            System.out.println("List is empty. Cannot delete from last.");
            return;
        }
        int deletedData = last.data; // Store data of the last node for output.

        if (size == 1) { // 2. Special Case: If there's only one node in the list.
            last = null; // List becomes empty.
        } else { // 3. General Case: Multiple nodes.
            Node current = last.next; // a. Start from the head.

            // b. Traverse until 'current' is the node *before* the 'last' node.
            //    (i.e., current.next is the 'last' node)
            while (current.next != last) {
                current = current.next;
            }
            // At this point, 'current' is the new last node.
            current.next = last.next; // c. The new `last` node's `next` points to the head.
            last = current;           // d. Update the `last` pointer to `current`.
        }
        size--;                        // 4. Decrement size.
        System.out.println("Deleted " + deletedData + " from last. List size: " + size);
    }
    
    // Operation 7: Traverse and print all elements in the list
    // Time Complexity: O(N) - We visit each node once.
    // CRITICAL: A stop condition is needed, as there's no 'null' to terminate the loop.
    public void printList() {
        if (isEmpty()) { // 1. Handle empty list case.
            System.out.println("List is empty.");
            return;
        }
        Node current = last.next; // 2. Start from the head (which is last.next).
        System.out.print("Circular List: ");
        do { // 3. Use a do-while loop to ensure at least one node is processed, even if size is 1.
            System.out.print(current.data + " -> ");
            current = current.next; // 4. Move to the next node.
        } while (current != last.next); // 5. The loop continues until 'current' comes back to the starting node (head).
        System.out.println("(goes back to " + last.next.data + ")"); // Indicate the circular nature.
    }

    // Operation 8: Search for a specific value in the list
    // Time Complexity: O(N) - We might need to check every node.
    // CRITICAL: A stop condition is needed, same as printList.
    public boolean search(int data) {
        if (isEmpty()) { // 1. Handle empty list.
            return false;
        }
        Node current = last.next; // 2. Start from the head.
        do { // 3. Loop through the list.
            if (current.data == data) { // 4. Check if current node's data matches.
                return true; // Found!
            }
            current = current.next; // 5. Move to the next node.
        } while (current != last.next); // 6. Continue until we've completed one full circle.
        return false; // 7. If the loop finishes without finding data, it's not present.
    }

    // --- Main Method to Test Our Circular Linked List Operations ---
    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();

        System.out.println("--- Initial State ---");
        System.out.println("Is list empty? " + cll.isEmpty()); // Expected: true
        System.out.println("List size: " + cll.getSize());     // Expected: 0
        cll.printList(); // Expected: List is empty.

        System.out.println("\n--- Adding Elements ---");
        cll.addFirst(10); // Add to empty list. List: (10) -> 10
        cll.printList();
        System.out.println("Head (last.next) data: " + cll.last.next.data + ", Last data: " + cll.last.data);

        cll.addLast(20);  // Add 20. List: (10) -> 20 -> 10
        cll.printList();
        System.out.println("Head data: " + cll.last.next.data + ", Last data: " + cll.last.data);

        cll.addFirst(5);  // Add 5. List: (5) -> 10 -> 20 -> 5
        cll.printList();
        System.out.println("Head data: " + cll.last.next.data + ", Last data: " + cll.last.data);

        cll.addLast(25);  // Add 25. List: (5) -> 10 -> 20 -> 25 -> 5
        cll.printList();
        System.out.println("Head data: " + cll.last.next.data + ", Last data: " + cll.last.data);
        
        System.out.println("Current list size: " + cll.getSize()); // Expected: 4

        System.out.println("\n--- Searching Elements ---");
        System.out.println("Search for 20: " + cll.search(20));   // Expected: true
        System.out.println("Search for 100: " + cll.search(100)); // Expected: false

        System.out.println("\n--- Deleting Elements ---");
        cll.deleteFirst(); // Delete 5. List: (10) -> 20 -> 25 -> 10
        cll.printList();
        System.out.println("Head data: " + cll.last.next.data + ", Last data: " + cll.last.data);

        cll.deleteLast();  // Delete 25. List: (10) -> 20 -> 10
        cll.printList();
        System.out.println("Head data: " + cll.last.next.data + ", Last data: " + cll.last.data);

        System.out.println("List size: " + cll.getSize()); // Expected: 2

        System.out.println("\n--- Deleting down to empty ---");
        cll.deleteFirst(); // Delete 10. List: (20) -> 20 (single node remaining)
        cll.printList();
        System.out.println("Head data: " + cll.last.next.data + ", Last data: " + cll.last.data);
        System.out.println("List size: " + cll.getSize()); // Expected: 1

        cll.deleteLast();  // Delete 20. List: empty
        cll.printList();
        System.out.println("Is list empty? " + cll.isEmpty()); // Expected: true
        System.out.println("List size: " + cll.getSize());     // Expected: 0

        // Test deletions on an empty list
        cll.deleteFirst(); // Expected: Cannot delete from first.
        cll.deleteLast();  // Expected: Cannot delete from last.
    }
}
