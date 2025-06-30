// File: BinarySearchTree.java

import java.util.LinkedList; // Needed for Level Order Traversal
import java.util.Queue;     // Needed for Level Order Traversal

/**
 * Represents a single node in a Binary Search Tree.
 * Each node stores an integer data and references to its left and right children.
 */
class TreeNode {
    int data;         // The value stored in this node.
    TreeNode left;    // Reference to the left child node.
    TreeNode right;   // Reference to the right child node.

    /**
     * Constructor for TreeNode.
     * @param data The integer value to store in this node.
     */
    public TreeNode(int data) {
        this.data = data;
        this.left = null;  // New nodes are created without children.
        this.right = null;
    }
}

/**
 * Implements a Binary Search Tree (BST) data structure.
 * A BST maintains a property where for any node, all values in its left subtree
 * are smaller than its data, and all values in its right subtree are larger.
 * This class provides methods for common BST operations like insert, search, delete,
 * and various traversals.
 */
public class BinarySearchTree {
    TreeNode root; // The root node of the BST. If null, the tree is empty.

    /**
     * Constructor for BinarySearchTree.
     * Initializes an empty BST.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    // --- 1. Insert Operation ---

    /**
     * Inserts a new data value into the Binary Search Tree.
     * Delegates to a recursive helper method.
     * Time Complexity: O(log N) on average (for balanced trees), O(N) in worst case (for skewed trees).
     * @param data The integer value to insert.
     */
    public void insert(int data) {
        root = insertRecursive(root, data);
        System.out.println("Inserted: " + data);
    }

    /**
     * Helper method for recursive insertion.
     * @param current The current node being examined in the recursion.
     * @param data The value to insert.
     * @return The updated current node (or a new node if insertion point is found).
     */
    private TreeNode insertRecursive(TreeNode current, int data) {
        // Base Case: If the current node is null, we've found the correct spot to insert.
        if (current == null) {
            return new TreeNode(data); // Create and return the new node.
        }

        // Recursive Step: Decide whether to go left or right based on data comparison.
        if (data < current.data) {
            // If new data is smaller, go to the left subtree.
            current.left = insertRecursive(current.left, data);
        } else if (data > current.data) {
            // If new data is larger, go to the right subtree.
            // (Assuming no duplicates for simplicity in a strict BST.
            // For duplicates, you might allow them in the right subtree, or ignore them.)
            current.right = insertRecursive(current.right, data);
        } else {
            // Data already exists in the tree. For a strict BST, we typically do nothing or handle as per requirement.
            // For this example, we'll just return the current node, effectively ignoring duplicates.
            System.out.println("Warning: Duplicate value " + data + " not inserted.");
            return current;
        }
        return current; // Return the (possibly modified) current node.
    }

    // --- 2. Search Operation ---

    /**
     * Searches for a data value in the Binary Search Tree.
     * Delegates to a recursive helper method.
     * Time Complexity: O(log N) on average, O(N) in worst case.
     * @param data The integer value to search for.
     * @return true if the data is found, false otherwise.
     */
    public boolean search(int data) {
        return searchRecursive(root, data);
    }

    /**
     * Helper method for recursive searching.
     * @param current The current node being examined.
     * @param data The value to search for.
     * @return true if found, false otherwise.
     */
    private boolean searchRecursive(TreeNode current, int data) {
        // Base Case 1: If current node is null, the value is not found in this path.
        if (current == null) {
            return false;
        }

        // Base Case 2: If data matches current node's data, we found it!
        if (data == current.data) {
            return true;
        }

        // Recursive Step: Decide where to go next based on data comparison.
        if (data < current.data) {
            // If target data is smaller, search in the left subtree.
            return searchRecursive(current.left, data);
        } else {
            // If target data is larger, search in the right subtree.
            return searchRecursive(current.right, data);
        }
    }

    // --- 3. Delete Operation ---

    /**
     * Deletes a data value from the Binary Search Tree.
     * Delegates to a recursive helper method. Handles three cases: leaf node, one child, two children.
     * Time Complexity: O(log N) on average, O(N) in worst case.
     * @param data The integer value to delete.
     */
    public void delete(int data) {
        if (!search(data)) { // Optional: Check if element exists before attempting delete.
            System.out.println("Cannot delete " + data + ": Not found in tree.");
            return;
        }
        root = deleteRecursive(root, data);
        System.out.println("Deleted: " + data);
    }

    /**
     * Helper method for recursive deletion.
     * @param current The current node being examined.
     * @param data The value to delete.
     * @return The updated node (or null if the node itself was deleted).
     */
    private TreeNode deleteRecursive(TreeNode current, int data) {
        // Base Case: If current node is null, the value was not found (shouldn't happen if pre-checked).
        if (current == null) {
            return null;
        }

        // If the current node contains the data to be deleted:
        if (data == current.data) {
            // Case 1: Node has no children (it's a leaf node).
            // Just return null, effectively removing it.
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: Node has only one child.
            // Replace the node with its single child.
            if (current.left == null) { // Has only right child
                return current.right;
            }
            if (current.right == null) { // Has only left child
                return current.left;
            }

            // Case 3: Node has two children. (Most complex case)
            // Find the inorder successor (smallest value in the right subtree).
            int smallestValue = findSmallestValue(current.right);
            // Replace the current node's data with the inorder successor's data.
            current.data = smallestValue;
            // Recursively delete the inorder successor from its original position in the right subtree.
            // This call will fall into Case 1 or Case 2.
            current.right = deleteRecursive(current.right, smallestValue);
            return current; // Return the current node (now with updated data).

        } else if (data < current.data) {
            // If data to delete is smaller, go to the left subtree.
            current.left = deleteRecursive(current.left, data);
            return current;
        } else {
            // If data to delete is larger, go to the right subtree.
            current.right = deleteRecursive(current.right, data);
            return current;
        }
    }

    /**
     * Helper method to find the smallest value in a given subtree.
     * Used in the delete operation for Case 3.
     * @param root The root of the subtree to search in.
     * @return The smallest integer value found in the subtree.
     */
    private int findSmallestValue(TreeNode root) {
        // The smallest value in a BST is always the leftmost node.
        return root.left == null ? root.data : findSmallestValue(root.left);
    }

    // --- 4. Tree Traversal Operations ---

    /**
     * Performs an Inorder Traversal of the BST.
     * Visits nodes in the order: Left -> Root -> Right.
     * For a BST, this prints elements in sorted (ascending) order.
     * Time Complexity: O(N) - visits every node once.
     */
    public void inorderTraversal() {
        System.out.print("Inorder Traversal: ");
        inorderRecursive(root);
        System.out.println();
    }

    /**
     * Helper method for recursive Inorder Traversal.
     * @param node The current node being visited.
     */
    private void inorderRecursive(TreeNode node) {
        if (node != null) {
            inorderRecursive(node.left);  // 1. Traverse left subtree.
            System.out.print(node.data + " "); // 2. Visit current node.
            inorderRecursive(node.right); // 3. Traverse right subtree.
        }
    }

    /**
     * Performs a Preorder Traversal of the BST.
     * Visits nodes in the order: Root -> Left -> Right.
     * Useful for creating a copy of the tree.
     * Time Complexity: O(N).
     */
    public void preorderTraversal() {
        System.out.print("Preorder Traversal: ");
        preorderRecursive(root);
        System.out.println();
    }

    /**
     * Helper method for recursive Preorder Traversal.
     * @param node The current node being visited.
     */
    private void preorderRecursive(TreeNode node) {
        if (node != null) {
            System.out.print(node.data + " "); // 1. Visit current node.
            preorderRecursive(node.left);  // 2. Traverse left subtree.
            preorderRecursive(node.right); // 3. Traverse right subtree.
        }
    }

    /**
     * Performs a Postorder Traversal of the BST.
     * Visits nodes in the order: Left -> Right -> Root.
     * Useful for deleting a tree (children deleted before parent).
     * Time Complexity: O(N).
     */
    public void postorderTraversal() {
        System.out.print("Postorder Traversal: ");
        postorderRecursive(root);
        System.out.println();
    }

    /**
     * Helper method for recursive Postorder Traversal.
     * @param node The current node being visited.
     */
    private void postorderRecursive(TreeNode node) {
        if (node != null) {
            postorderRecursive(node.left);  // 1. Traverse left subtree.
            postorderRecursive(node.right); // 2. Traverse right subtree.
            System.out.print(node.data + " "); // 3. Visit current node.
        }
    }

    /**
     * Performs a Level Order Traversal (Breadth-First Traversal - BFS) of the BST.
     * Visits nodes level by level, from left to right. Uses a Queue.
     * Time Complexity: O(N).
     */
    public void levelOrderTraversal() {
        if (root == null) {
            System.out.println("Level Order Traversal: (empty tree)");
            return;
        }

        System.out.print("Level Order Traversal: ");
        Queue<TreeNode> queue = new LinkedList<>(); // Use LinkedList as a Queue implementation.
        queue.offer(root); // Start by adding the root to the queue.

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll(); // Get the node from the front of the queue.
            System.out.print(current.data + " "); // Visit the current node.

            // Add its children to the back of the queue (if they exist) for processing in the next level.
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        System.out.println();
    }

    // --- 5. Tree Properties Operations ---

    /**
     * Checks if the Binary Search Tree is empty.
     * Time Complexity: O(1).
     * @return true if the tree has no nodes, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Calculates the total number of nodes in the tree.
     * Delegates to a recursive helper method.
     * Time Complexity: O(N) - visits every node once.
     * @return The number of nodes in the tree.
     */
    public int size() {
        return sizeRecursive(root);
    }

    /**
     * Helper method for recursive size calculation.
     * @param node The current node being considered.
     * @return The size of the subtree rooted at 'node'.
     */
    private int sizeRecursive(TreeNode node) {
        if (node == null) {
            return 0; // An empty subtree has 0 nodes.
        }
        // Size is 1 (for current node) + size of left subtree + size of right subtree.
        return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
    }

    /**
     * Calculates the height of the tree.
     * The height is the length of the longest path from the root to a leaf node.
     * Delegates to a recursive helper method.
     * Time Complexity: O(N) - visits every node once.
     * @return The height of the tree. Returns -1 if the tree is empty.
     */
    public int height() {
        return heightRecursive(root);
    }

    /**
     * Helper method for recursive height calculation.
     * @param node The current node being considered.
     * @return The height of the subtree rooted at 'node'.
     */
    private int heightRecursive(TreeNode node) {
        if (node == null) {
            return -1; // The height of an empty tree (or a null subtree) is conventionally -1.
        }
        int leftHeight = heightRecursive(node.left);   // Calculate height of left subtree.
        int rightHeight = heightRecursive(node.right); // Calculate height of right subtree.
        // Height is 1 (for the current node) + the maximum height of its children's subtrees.
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Main method to demonstrate basic Binary Search Tree operations.
     */
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        System.out.println("--- Initial State ---");
        System.out.println("Is tree empty? " + bst.isEmpty()); // true
        System.out.println("Tree size: " + bst.size());       // 0
        System.out.println("Tree height: " + bst.height());   // -1
        bst.inorderTraversal(); // (empty tree)

        System.out.println("\n--- Inserting Elements ---");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        bst.insert(35); // Insert 35 to make it a bit more complex.
        bst.insert(50); // Attempt to insert duplicate

        System.out.println("\n--- Tree State After Insertions ---");
        System.out.println("Is tree empty? " + bst.isEmpty()); // false
        System.out.println("Tree size: " + bst.size());       // 8 (duplicates not added)
        // Expected height depends on insertion order and balance. For this order:
        // 50 (0)
        // |-30 (1)
        // | |-20 (2)
        // | |-40 (2)
        // |   |-35 (3)
        // |-70 (1)
        //   |-60 (2)
        //   |-80 (2)
        System.out.println("Tree height: " + bst.height());   // 3 (longest path 50->30->40->35)

        System.out.println("\n--- Tree Traversals ---");
        bst.inorderTraversal();    // Expected: 20 30 35 40 50 60 70 80 (Sorted order)
        bst.preorderTraversal();   // Expected: 50 30 20 40 35 70 60 80
        bst.postorderTraversal();  // Expected: 20 35 40 30 60 80 70 50
        bst.levelOrderTraversal(); // Expected: 50 30 70 20 40 60 80 35

        System.out.println("\n--- Searching Elements ---");
        System.out.println("Search for 40: " + bst.search(40));   // true
        System.out.println("Search for 90: " + bst.search(90));   // false
        System.out.println("Search for 20: " + bst.search(20));   // true

        System.out.println("\n--- Deleting Elements ---");
        bst.delete(20); // Case 1: Leaf node (20 is a leaf)
        bst.inorderTraversal(); // Expected: 30 35 40 50 60 70 80

        bst.delete(70); // Case 2: Node with one child (70 has only 60 and 80, 70 is replaced by 60 and 60 is deleted from its original place)
        bst.inorderTraversal(); // Expected: 30 35 40 50 60 80

        bst.delete(50); // Case 3: Node with two children (root 50). Replaced by 60 (inorder successor)
        bst.inorderTraversal(); // Expected: 30 35 40 60 80

        bst.delete(99); // Cannot delete 99: Not found in tree.
        bst.inorderTraversal(); // No change

        System.out.println("\n--- Final Tree State ---");
        System.out.println("Tree size: " + bst.size());     // 5
        // Expected height after deletions:
        // 60 (0)
        // |-30 (1)
        // | |- (null)
        // | |-40 (2)
        // |   |-35 (3)
        // |-80 (1)
        System.out.println("Tree height: " + bst.height()); // 3 (e.g., 60->30->40->35)
    }
}
