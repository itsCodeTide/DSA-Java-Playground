// File: RedBlackTree.java

import java.util.LinkedList; // For Level Order Traversal
import java.util.Queue;     // For Level Order Traversal
import java.lang.Math;      // For Math.max

/**
 * Represents a single node in a Red-Black Tree.
 * Each node stores integer data, references to its left, right children,
 * its parent, and its color (RED or BLACK).
 */
class RBNode {
    int data;           // The value stored in this node.
    RBNode left;        // Reference to the left child node.
    RBNode right;       // Reference to the right child node.
    RBNode parent;      // Reference to the parent node (crucial for RB-Tree operations).
    boolean isRed;      // True if Red, false if Black.

    /**
     * Constructor for RBNode.
     * New nodes are typically inserted as Red.
     * @param data The integer value to store in this node.
     */
    public RBNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.isRed = true; // New nodes are always inserted as Red.
    }

    // Helper to get color (null nodes are considered Black)
    public boolean isBlack() {
        return !isRed;
    }
}

/**
 * Implements a Red-Black Tree data structure.
 * A self-balancing Binary Search Tree that maintains balance using coloring rules
 * and rotations.
 */
public class RedBlackTree {
    private RBNode root; // The root node of the Red-Black Tree.

    // Constants for node colors (for clarity)
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * Constructor for RedBlackTree.
     * Initializes an empty Red-Black tree.
     */
    public RedBlackTree() {
        this.root = null;
    }

    // --- Helper Methods for Node Properties ---

    /**
     * Gets the color of a node. Null nodes are considered Black.
     * @param node The node to check.
     * @return true if Red, false if Black (or null).
     */
    private boolean getColor(RBNode node) {
        return (node == null) ? BLACK : node.isRed;
    }

    /**
     * Sets the color of a node.
     * @param node The node to color.
     * @param color The color to set (RED or BLACK).
     */
    private void setColor(RBNode node, boolean color) {
        if (node != null) {
            node.isRed = color;
        }
    }

    // --- Rotation Operations ---

    /**
     * Performs a Right Rotation around node 'y'.
     * This moves 'y' to its right child, and 'x' (y's left child) becomes the new parent.
     * @param y The node around which to rotate.
     * @return The new root of the rotated subtree (which is 'x').
     */
    private RBNode rightRotate(RBNode y) {
        RBNode x = y.left; // x is the left child of y.

        y.left = x.right; // y's left child becomes x's right child.
        if (x.right != null) {
            x.right.parent = y; // Update parent pointer of T2.
        }

        x.parent = y.parent; // x's parent becomes y's parent.
        if (y.parent == null) { // If y was the root.
            this.root = x;
        } else if (y == y.parent.right) { // If y was a right child.
            y.parent.right = x;
        } else { // If y was a left child.
            y.parent.left = x;
        }

        x.right = y; // x's right child becomes y.
        y.parent = x; // y's parent becomes x.

        return x; // Return the new root of this subtree.
    }

    /**
     * Performs a Left Rotation around node 'x'.
     * This moves 'x' to its left child, and 'y' (x's right child) becomes the new parent.
     * @param x The node around which to rotate.
     * @return The new root of the rotated subtree (which is 'y').
     */
    private RBNode leftRotate(RBNode x) {
        RBNode y = x.right; // y is the right child of x.

        x.right = y.left; // x's right child becomes y's left child.
        if (y.left != null) {
            y.left.parent = x; // Update parent pointer of T2.
        }

        y.parent = x.parent; // y's parent becomes x's parent.
        if (x.parent == null) { // If x was the root.
            this.root = y;
        } else if (x == x.parent.left) { // If x was a left child.
            x.parent.left = y;
        } else { // If x was a right child.
            x.parent.right = y;
        }

        y.left = x; // y's left child becomes x.
        x.parent = y; // x's parent becomes y.

        return y; // Return the new root of this subtree.
    }


    // --- 1. Insert Operation ---

    /**
     * Inserts a new data value into the Red-Black Tree.
     * @param data The integer value to insert.
     */
    public void insert(int data) {
        RBNode newNode = new RBNode(data); // New node is always created as RED.
        root = insertBST(root, newNode);   // Perform standard BST insertion.

        // After BST insertion, newNode is attached. Now fix violations.
        fixInsert(newNode);
        System.out.println("Inserted: " + data);
    }

    /**
     * Helper method for standard BST insertion.
     * Attaches the newNode to the tree and sets its parent pointer.
     * @param rootNode The current root of the subtree being considered.
     * @param newNode The node to insert.
     * @return The root of the (potentially modified) subtree.
     */
    private RBNode insertBST(RBNode rootNode, RBNode newNode) {
        if (rootNode == null) {
            return newNode; // Found the insertion point.
        }

        if (newNode.data < rootNode.data) {
            rootNode.left = insertBST(rootNode.left, newNode);
            rootNode.left.parent = rootNode; // Set parent pointer.
        } else if (newNode.data > rootNode.data) {
            rootNode.right = insertBST(rootNode.right, newNode);
            rootNode.right.parent = rootNode; // Set parent pointer.
        } else {
            // Duplicate values are typically not allowed or handled differently in BSTs.
            // For RB-Tree, we'll just return the existing node, effectively ignoring duplicates.
            System.out.println("Warning: Duplicate value " + newNode.data + " not inserted.");
            return rootNode;
        }
        return rootNode;
    }

    /**
     * Fixes Red-Black tree properties after insertion.
     * This is the core balancing logic for insertion.
     * @param k The newly inserted (or re-colored) node that might be causing a violation.
     */
    private void fixInsert(RBNode k) {
        RBNode parent = null;
        RBNode grandparent = null;

        // Loop as long as the current node 'k' is Red and its parent is also Red (violation of Property 3).
        // Also, ensure 'k' is not the root (root must be Black).
        while (k != root && getColor(k) == RED && getColor(k.parent) == RED) {
            parent = k.parent;
            grandparent = parent.parent; // Grandparent must exist because parent is Red (Property 2: root is black).

            /* Case A: Parent is left child of Grandparent */
            if (parent == grandparent.left) {
                RBNode uncle = grandparent.right;

                /* Case A1: Uncle is RED (Recoloring) */
                if (getColor(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grandparent, RED);
                    k = grandparent; // Move 'k' up to grandparent and continue loop.
                } else { // Uncle is BLACK
                    /* Case A2: Current node 'k' is a RIGHT child (LR Case - Left-Right Rotation) */
                    if (k == parent.right) {
                        leftRotate(parent); // Perform Left Rotation on parent.
                        k = parent;         // 'k' now points to the old parent (which is now the left child of k).
                        parent = k.parent;  // Update parent.
                    }
                    /* Case A3: Current node 'k' is a LEFT child (LL Case - Right Rotation) */
                    rightRotate(grandparent); // Perform Right Rotation on grandparent.
                    // Recolor after rotation
                    setColor(parent, BLACK);
                    setColor(grandparent, RED);
                    k = parent; // After rotation, parent is the new root of this subtree.
                }
            } else { // Case B: Parent is right child of Grandparent (Symmetric to Case A)
                RBNode uncle = grandparent.left;

                /* Case B1: Uncle is RED (Recoloring) */
                if (getColor(uncle) == RED) {
                    setColor(parent, BLACK);
                    setColor(uncle, BLACK);
                    setColor(grandparent, RED);
                    k = grandparent; // Move 'k' up to grandparent and continue loop.
                } else { // Uncle is BLACK
                    /* Case B2: Current node 'k' is a LEFT child (RL Case - Right-Left Rotation) */
                    if (k == parent.left) {
                        rightRotate(parent); // Perform Right Rotation on parent.
                        k = parent;          // 'k' now points to the old parent.
                        parent = k.parent;   // Update parent.
                    }
                    /* Case B3: Current node 'k' is a RIGHT child (RR Case - Left Rotation) */
                    leftRotate(grandparent); // Perform Left Rotation on grandparent.
                    // Recolor after rotation
                    setColor(parent, BLACK);
                    setColor(grandparent, RED);
                    k = parent; // After rotation, parent is the new root of this subtree.
                }
            }
        }
        // Always ensure the root is Black (Property 2).
        setColor(root, BLACK);
    }

    // --- 2. Search Operation ---

    /**
     * Searches for a data value in the Red-Black Tree.
     * Time Complexity: O(log N) in both average and worst cases (due to guaranteed balance).
     * @param data The integer value to search for.
     * @return true if the data is found, false otherwise.
     */
    public boolean search(int data) {
        return searchRecursive(root, data);
    }

    /**
     * Helper method for recursive searching.
     * @param node The current node being examined.
     * @param data The value to search for.
     * @return true if found, false otherwise.
     */
    private boolean searchRecursive(RBNode node, int data) {
        if (node == null) {
            return false; // Not found.
        }
        if (data == node.data) {
            return true; // Found!
        }
        if (data < node.data) {
            return searchRecursive(node.left, data);
        } else {
            return searchRecursive(node.right, data);
        }
    }

    // --- 3. Delete Operation (Conceptual - Complex to Implement Fully) ---

    /**
     * Deletes a data value from the Red-Black Tree.
     * NOTE: Full implementation of Red-Black Tree deletion is significantly
     * more complex than insertion due to the many cases required to maintain
     * properties, especially the Black-Height property.
     * This method provides a basic structure but does NOT include the full
     * rebalancing logic for all deletion cases.
     * Time Complexity: O(log N) in both average and worst cases (if fully implemented).
     * @param data The integer value to delete.
     */
    public void delete(int data) {
        System.out.println("\n--- Attempting to Delete " + data + " ---");
        if (!search(data)) {
            System.out.println("Cannot delete " + data + ": Not found in tree.");
            return;
        }
        // Deletion logic in Red-Black Trees is very complex.
        // It involves finding the node, identifying its successor/predecessor if it has two children,
        // replacing it, and then fixing potential double-black violations.
        // For a full implementation, this method would call a complex recursive helper.
        System.out.println("Deletion of " + data + " is not fully implemented for all Red-Black Tree cases in this example.");
        System.out.println("Please refer to advanced algorithms for full Red-Black Tree deletion logic.");
        // A placeholder to show it would modify the root:
        // root = deleteRecursive(root, data);
    }

    // --- 4. Tree Traversal Operations ---

    /**
     * Performs an Inorder Traversal of the Red-Black Tree.
     * Visits nodes in the order: Left -> Root -> Right.
     * Prints elements in sorted (ascending) order.
     * Time Complexity: O(N).
     */
    public void inorderTraversal() {
        System.out.print("Inorder Traversal: ");
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(RBNode node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.data + (node.isRed ? "(R)" : "(B)") + " ");
            inorderRecursive(node.right);
        }
    }

    /**
     * Performs a Preorder Traversal of the Red-Black Tree.
     * Visits nodes in the order: Root -> Left -> Right.
     * Time Complexity: O(N).
     */
    public void preorderTraversal() {
        System.out.print("Preorder Traversal: ");
        preorderRecursive(root);
        System.out.println();
    }

    private void preorderRecursive(RBNode node) {
        if (node != null) {
            System.out.print(node.data + (node.isRed ? "(R)" : "(B)") + " ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
        }
    }

    /**
     * Performs a Postorder Traversal of the Red-Black Tree.
     * Visits nodes in the order: Left -> Right -> Root.
     * Time Complexity: O(N).
     */
    public void postorderTraversal() {
        System.out.print("Postorder Traversal: ");
        postorderRecursive(root);
        System.out.println();
    }

    private void postorderRecursive(RBNode node) {
        if (node != null) {
            postorderRecursive(node.left);
            postorderRecursive(node.right);
            System.out.print(node.data + (node.isRed ? "(R)" : "(B)") + " ");
        }
    }

    /**
     * Performs a Level Order Traversal (BFS) of the Red-Black Tree.
     * Visits nodes level by level, from left to right. Uses a Queue.
     * Time Complexity: O(N).
     */
    public void levelOrderTraversal() {
        if (root == null) {
            System.out.println("Level Order Traversal: (empty tree)");
            return;
        }

        System.out.print("Level Order Traversal: ");
        Queue<RBNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            RBNode current = queue.poll();
            System.out.print(current.data + (current.isRed ? "(R)" : "(B)") + " ");
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
     * Checks if the Red-Black Tree is empty.
     * Time Complexity: O(1).
     * @return true if the tree has no nodes, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Calculates the total number of nodes in the tree.
     * Delegates to a recursive helper method.
     * Time Complexity: O(N).
     * @return The number of nodes in the tree.
     */
    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(RBNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
    }

    /**
     * Calculates the height of the tree.
     * The height is the length of the longest path from the root to a leaf node.
     * Delegates to a recursive helper method.
     * Time Complexity: O(N).
     * @return The height of the tree. Returns -1 if the tree is empty.
     */
    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(RBNode node) {
        if (node == null) {
            return -1; // Height of an empty tree (or null subtree) is -1.
        }
        int leftHeight = heightRecursive(node.left);
        int rightHeight = heightRecursive(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Main method to demonstrate basic Red-Black Tree operations.
     * Focuses on insertion and traversal to show balancing.
     */
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();

        System.out.println("--- Initial State ---");
        System.out.println("Is tree empty? " + rbt.isEmpty()); // true
        System.out.println("Tree size: " + rbt.size());       // 0
        System.out.println("Tree height: " + rbt.height());   // -1
        rbt.inorderTraversal(); // (empty tree)

        System.out.println("\n--- Inserting Elements (Observe Coloring & Balancing) ---");
        // Insertion sequence that will trigger various fix-up cases
        rbt.insert(10); // 10(B) - Root is always Black
        rbt.levelOrderTraversal();
        System.out.println("Root: " + rbt.root.data + (rbt.root.isRed ? "(R)" : "(B)"));

        rbt.insert(20); // 20(R)
        rbt.levelOrderTraversal(); // 10(B) -> 20(R)
        System.out.println("Root: " + rbt.root.data + (rbt.root.isRed ? "(R)" : "(B)"));

        rbt.insert(30); // 30(R) -> Parent 20(R) -> Grandparent 10(B) -> Uncle NIL(B) -> RR Case -> Left Rotate at 10
                        // Result: 20(B) -> 10(R), 30(R)
        rbt.levelOrderTraversal();
        System.out.println("Root: " + rbt.root.data + (rbt.root.isRed ? "(R)" : "(B)"));

        rbt.insert(15); // 15(R) -> Parent 10(R) -> Grandparent 20(B) -> Uncle 30(R) -> Case A1 (Recolor)
                        // Result: 20(B) -> 10(B), 30(B). 20 becomes Red, but it's root, so 20(B)
        rbt.levelOrderTraversal();
        System.out.println("Root: " + rbt.root.data + (rbt.root.isRed ? "(R)" : "(B)"));

        rbt.insert(5);  // 5(R) -> Parent 10(B) -> No violation.
        rbt.levelOrderTraversal();
        System.out.println("Root: " + rbt.root.data + (rbt.root.isRed ? "(R)" : "(B)"));

        rbt.insert(25); // 25(R) -> Parent 20(B) -> No violation.
        rbt.levelOrderTraversal();
        System.out.println("Root: " + rbt.root.data + (rbt.root.isRed ? "(R)" : "(B)"));

        rbt.insert(35); // 35(R) -> Parent 30(B) -> No violation.
        rbt.levelOrderTraversal();
        System.out.println("Root: " + rbt.root.data + (rbt.root.isRed ? "(R)" : "(B)"));

        rbt.insert(22); // 22(R) -> Parent 25(R) -> Grandparent 20(B) -> Uncle 10(B) -> RL Case -> Right-Left Rotate
                        // Result: 20(B) -> 10(B), 25(B). 22 becomes Red.
        rbt.levelOrderTraversal();
        System.out.println("Root: " + rbt.root.data + (rbt.root.isRed ? "(R)" : "(B)"));

        rbt.insert(12); // 12(R) -> Parent 15(R) -> Grandparent 10(B) -> Uncle 5(B) -> LR Case -> Left-Right Rotate
        rbt.levelOrderTraversal();
        System.out.println("Root: " + rbt.root.data + (rbt.root.isRed ? "(R)" : "(B)"));


        System.out.println("\n--- Tree State After Insertions ---");
        System.out.println("Is tree empty? " + rbt.isEmpty());
        System.out.println("Tree size: " + rbt.size());
        System.out.println("Tree height: " + rbt.height()); // Should be relatively balanced (log N)

        System.out.println("\n--- Tree Traversals ---");
        rbt.inorderTraversal();    // Should be sorted
        rbt.preorderTraversal();
        rbt.postorderTraversal();
        rbt.levelOrderTraversal();

        System.out.println("\n--- Searching Elements ---");
        System.out.println("Search for 25: " + rbt.search(25));   // true
        System.out.println("Search for 100: " + rbt.search(100)); // false
        System.out.println("Search for 10: " + rbt.search(10));   // true

        System.out.println("\n--- Deleting Elements (Conceptual - Not Fully Implemented) ---");
        rbt.delete(5);  // Attempt to delete a leaf
        rbt.delete(20); // Attempt to delete root (after some insertions)
        rbt.delete(100); // Not found
        System.out.println("\nNote: Full Red-Black Tree deletion is very complex and is not fully implemented in this example for brevity.");
        System.out.println("The output for deletion attempts above will only show messages, not actual tree changes.");
    }
}
