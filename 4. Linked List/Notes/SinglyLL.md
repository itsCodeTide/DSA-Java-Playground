Okay, gather 'round, future coders\! We've talked about arrays – those neat, organized shelves where you put your items, each in its own numbered slot. They're great, right? Super fast if you know the slot number.

But what if you need to add something in the middle of a full shelf? Or remove something, leaving a gap? With arrays, it's a bit like shuffling *all* the other items down (or up) to make space or close a gap. That can be a lot of work if your shelf is really long\!

This is where our new friend, the **Linked List**, steps in. It offers a completely different way to organize your items, one that's super flexible when it comes to adding or removing things.

-----

## Linked Lists: Your Flexible Chain of Data

Imagine you're on a treasure hunt. Instead of a map that shows you all the treasure locations directly, you get your first clue. This clue tells you where the first treasure is, and once you find it, you get *another clue* that tells you where the *next* treasure is. And so on, until the last treasure, which just says "The End\!"

That's precisely how a **Linked List** works\! It's a sequence of data elements, but unlike an array, they don't have to be physically next to each other in memory. Instead, each element "knows" where the *next* element is.

### The Basic Building Blocks: What's Inside a Linked List?

Every piece of data in a Linked List lives inside a special container called a **Node**.

#### 1\. The `Node`: Your Data's Little Home

Each `Node` has two main parts:

  * **Data:** This is the actual piece of information you want to store (e.g., a number, a name, a whole object).
  * **Next Pointer (or Reference):** This is the "clue" to the next `Node` in the sequence. It's simply a reference (or an arrow, if you think visually) pointing to the memory location of the next `Node`.

Think of it like a train carriage:
`[ Your Item (Data) | Points to Next Carriage (Next Pointer) ]`

The very last `Node` in a Linked List has its `next` pointer pointing to `null` (meaning "nothing" or "the end").

#### 2\. The `Head`: Your Starting Point

To find your way through the Linked List, you need to know where it starts. The **Head** is a special reference (or pointer) that always points to the *first `Node`* in the list. If the `Head` is `null`, it means your list is empty\!

Visually, a simple Linked List looks like this:

```
HEAD
  |
  V
[ Data1 | Next ] --> [ Data2 | Next ] --> [ Data3 | Next ] --> null
```

-----

### Why Use Linked Lists? (Advantages over Arrays)

  * **Dynamic Size:** Unlike arrays, Linked Lists can grow or shrink as needed. You don't have to pre-allocate a fixed amount of space.
  * **Easy Insertions/Deletions:** Adding a new item or removing an existing one, especially in the middle, is very efficient. You just need to change a few pointers, not shift entire blocks of data. This is typically an $O(1)$ operation if you have a pointer to the specific location, or $O(N)$ if you have to search for it first.
  * **No Wasted Memory:** Linked Lists only allocate memory for the nodes they currently store. Arrays can sometimes waste space if you allocate a large array but only use a small part of it.

### The Trade-offs (Disadvantages)

  * **No Random Access:** Want to get the 5th item? In an array, you just go `myArray[4]` ($O(1)$). In a Linked List, you have to start from the `Head` and follow the `next` pointers one by one until you reach the 5th item ($O(N)$). This makes accessing elements by index much slower.
  * **More Memory per Node:** Each `Node` stores not only its data but also at least one pointer to the next `Node`. This consumes a bit more memory per item compared to just storing the data itself in an array.
  * **Not Cache-Friendly:** Because nodes are scattered in memory (not contiguous like array elements), modern computer caches don't work as efficiently with Linked Lists, potentially leading to slower overall performance in some scenarios.

-----

### Types of Linked Lists: Different Chains for Different Needs

Just like there are different types of chains, Linked Lists come in a few variations:

#### 1\. Singly Linked List (SLL)

This is the most basic type, and the one we'll focus on heavily. Each `Node` points *only* to the next `Node`. You can only traverse (move) forward.

**Visual:**

```
HEAD
  |
  V
[ Data1 | next ] --> [ Data2 | next ] --> [ Data3 | next ] --> null
```

#### 2\. Doubly Linked List (DLL)

In a Doubly Linked List, each `Node` has two pointers:

  * `next`: Points to the next `Node`.
  * `prev` (or `previous`): Points to the previous `Node`.

This allows you to traverse both forwards and backwards\! You usually keep track of both the `Head` and the `Tail` (last node).

**Visual:**

```
      HEAD                          TAIL
        |                             |
        V                             V
null <-- [ Data1 | prev | next ] <--> [ Data2 | prev | next ] <--> [ Data3 | prev | next ] --> null
```

**Advantages of DLLs:**

  * Easier to traverse backwards.
  * Deletion of a specific node is simpler because you can easily get to the `previous` node without starting from the `Head`.

**Disadvantages of DLLs:**

  * More memory used per `Node` (because of the extra `prev` pointer).
  * Operations are slightly more complex to implement as you need to manage two pointers (`next` and `prev`) during insertions and deletions.

#### 3\. Circular Linked List (CLL)

In a Circular Linked List, the `next` pointer of the last `Node` points back to the *first `Node`* (the `Head`), forming a circle. There is no `null` to mark the end.

**Visual (Singly Circular):**

```
HEAD
  |  <--------------------------------
  V                                   |
[ Data1 | next ] --> [ Data2 | next ] --
```

**Variations:** You can have both Singly Circular Linked Lists and Doubly Circular Linked Lists.

**Advantages of CLLs:**

  * You can traverse the entire list starting from any `Node`.
  * Useful for implementing data structures like queues where elements continuously cycle (e.g., a playlist that repeats).

**Disadvantages of CLLs:**

  * Traversal needs careful handling to avoid infinite loops (you need a condition to stop, like "stop when I get back to the starting `Node`").

-----

### Let's Build a Singly Linked List in Java\!

For beginners, the Singly Linked List is the best place to start. We'll build a basic one step-by-step.

#### Step 1: The `Node` Class

This is the blueprint for our individual data containers.

```java
// Node.java (or typically, this would be an inner class within your LinkedList class)
class Node {
    int data;     // The data this node holds (we'll use int for simplicity)
    Node next;    // A reference (pointer) to the next Node in the list

    // Constructor to create a new Node
    public Node(int data) {
        this.data = data;
        this.next = null; // When a new node is created, it initially points to nothing
    }
}
```

#### Step 2: The `SinglyLinkedList` Class

This class will manage the entire list. It needs a `head` pointer and methods to perform operations.

```java
public class SinglyLinkedList {
    // The head of the list. It points to the first node.
    // If head is null, the list is empty.
    Node head;

    // A variable to keep track of the list's size (optional, but makes getSize() O(1))
    int size;

    // Constructor for the LinkedList
    public SinglyLinkedList() {
        this.head = null; // A new list starts empty
        this.size = 0;
    }

    // --- Common Operations ---

    // 1. Adding a new node to the front (head) of the list
    // Time Complexity: O(1)
    public void addAtHead(int data) {
        Node newNode = new Node(data); // Create a new Node with the given data
        newNode.next = head;           // The new node's next pointer points to the current head
        head = newNode;                // The new node becomes the new head
        size++;                        // Increment the size
        System.out.println("Added " + data + " at head. List size: " + size);
    }

    // 2. Adding a new node to the end (tail) of the list
    // Time Complexity: O(N) because we might need to traverse the entire list
    public void addAtTail(int data) {
        Node newNode = new Node(data); // Create a new Node

        if (head == null) { // If the list is empty, the new node becomes the head
            head = newNode;
        } else {
            Node current = head; // Start from the head
            while (current.next != null) { // Traverse until you reach the last node
                current = current.next;
            }
            current.next = newNode; // The last node now points to the new node
        }
        size++;
        System.out.println("Added " + data + " at tail. List size: " + size);
    }

    // 3. Adding a new node at a specific index
    // Time Complexity: O(N) because we might need to traverse up to the index
    public void addAtIndex(int data, int index) {
        if (index < 0 || index > size) { // Basic validation for the index
            System.out.println("Error: Index " + index + " is out of bounds for size " + size);
            return;
        }
        if (index == 0) { // If index is 0, it's equivalent to addAtHead
            addAtHead(data);
            return;
        }
        if (index == size) { // If index is size, it's equivalent to addAtTail
            addAtTail(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;
        // Traverse to the node *before* the desired insertion point
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next; // New node points to the node that was originally at 'index'
        current.next = newNode;      // The previous node now points to the new node
        size++;
        System.out.println("Added " + data + " at index " + index + ". List size: " + size);
    }
    
    // 4. Deleting a node from the head
    // Time Complexity: O(1)
    public void deleteFromHead() {
        if (head == null) { // If list is empty, nothing to delete
            System.out.println("List is empty. Cannot delete from head.");
            return;
        }
        int deletedData = head.data; // Get data of node to be deleted
        head = head.next;           // The head now points to the second node (old head is garbage collected)
        size--;
        System.out.println("Deleted " + deletedData + " from head. List size: " + size);
    }

    // 5. Deleting a node from the tail
    // Time Complexity: O(N) because we need to find the second-to-last node
    public void deleteFromTail() {
        if (head == null) {
            System.out.println("List is empty. Cannot delete from tail.");
            return;
        }
        if (head.next == null) { // If there's only one node
            int deletedData = head.data;
            head = null; // List becomes empty
            size--;
            System.out.println("Deleted " + deletedData + " from tail. List size: " + size);
            return;
        }

        Node current = head;
        Node previous = null; // Keep track of the node before 'current'
        while (current.next != null) { // Traverse until 'current' is the last node
            previous = current;
            current = current.next;
        }
        // 'current' is now the last node, 'previous' is the second-to-last
        previous.next = null; // Sever the link to the last node
        size--;
        System.out.println("Deleted " + current.data + " from tail. List size: " + size);
    }

    // 6. Deleting a node by its value
    // Time Complexity: O(N) because we might need to search the entire list
    public void deleteByValue(int data) {
        if (head == null) {
            System.out.println("List is empty. Cannot delete value " + data + ".");
            return;
        }
        // Case 1: Head node contains the data to be deleted
        if (head.data == data) {
            head = head.next;
            size--;
            System.out.println("Deleted " + data + " by value from head. List size: " + size);
            return;
        }

        Node current = head;
        Node previous = null;
        while (current != null && current.data != data) { // Traverse until found or end
            previous = current;
            current = current.next;
        }

        if (current == null) { // Data not found
            System.out.println("Value " + data + " not found in the list. No deletion.");
        } else { // Data found (current points to the node to be deleted)
            previous.next = current.next; // Bypass the current node
            size--;
            System.out.println("Deleted " + data + " by value. List size: " + size);
        }
    }

    // 7. Traversing and printing the list
    // Time Complexity: O(N)
    public void printList() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node current = head; // Start from the head
        System.out.print("List: ");
        while (current != null) { // Iterate until you reach the end (null)
            System.out.print(current.data + " -> ");
            current = current.next; // Move to the next node
        }
        System.out.println("null"); // Indicate the end of the list
    }

    // 8. Searching for a value in the list
    // Time Complexity: O(N)
    public boolean search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true; // Found the data!
            }
            current = current.next;
        }
        return false; // Data not found
    }

    // 9. Checking if the list is empty
    // Time Complexity: O(1)
    public boolean isEmpty() {
        return head == null;
    }

    // 10. Get the current size of the list
    // Time Complexity: O(1) (if maintaining a size variable)
    public int getSize() {
        return size;
    }

    // --- Main method to test our SinglyLinkedList ---
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("Is list empty? " + list.isEmpty()); // true

        list.addAtHead(10); // List: 10 -> null
        list.printList();
        list.addAtHead(5);  // List: 5 -> 10 -> null
        list.printList();
        list.addAtTail(15); // List: 5 -> 10 -> 15 -> null
        list.printList();
        list.addAtIndex(8, 1); // List: 5 -> 8 -> 10 -> 15 -> null
        list.printList();
        list.addAtIndex(20, 4); // List: 5 -> 8 -> 10 -> 15 -> 20 -> null (add at tail)
        list.printList();
        list.addAtIndex(0, 0); // List: 0 -> 5 -> 8 -> 10 -> 15 -> 20 -> null (add at head)
        list.printList();

        System.out.println("List size: " + list.getSize()); // 6

        System.out.println("Is 10 in list? " + list.search(10)); // true
        System.out.println("Is 100 in list? " + list.search(100)); // false

        list.deleteFromHead(); // List: 5 -> 8 -> 10 -> 15 -> 20 -> null
        list.printList();
        list.deleteFromTail(); // List: 5 -> 8 -> 10 -> 15 -> null
        list.printList();
        
        list.deleteByValue(10); // List: 5 -> 8 -> 15 -> null
        list.printList();
        list.deleteByValue(5);  // List: 8 -> 15 -> null (deleted head by value)
        list.printList();
        list.deleteByValue(15); // List: 8 -> null (deleted tail by value)
        list.printList();
        list.deleteByValue(8);  // List: null (deleted last node)
        list.printList();
        list.deleteByValue(99); // Value not found.
    }
}
```
