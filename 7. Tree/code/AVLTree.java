// File: AVLTree.java

import java.util.LinkedList; // Needed for Level Order Traversal
import java.util.Queue;     // Needed for Level Order Traversal
import java.lang.Math;      // Needed for Math.max

/**
 * Represents a single node in an AVL Tree.
 * Each node stores integer data, references to its left and right children,
 * and its height within the subtree rooted at this node.
 */
class AVLNode {
    int data;         // The value stored in this node.
    AVLNode left;     // Reference to the left child node.
    AVLNode right;    // Reference to the right child node.
    int height;       // Height of the node (longest path from this node to a leaf).

    /**
     * Constructor for AVLNode.
     * @param data The integer value to store in this node.
     */
    public AVLNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1; // A new node is a leaf, so its height is 1.
    }
}

/**
 * Implements an AVL Tree data structure.
 * An AVL Tree is a self-balancing Binary Search Tree where the difference
 * between the heights of left and right subtrees (balance factor)
 * for any node is at most 1.
 */
public class AVLTree {
    private AVLNode root; // The root node of the AVL Tree.

    /**
     * Constructor for AVLTree.
     * Initializes an empty AVL tree.
     */
    public AVLTree() {
        this.root = null;
    }

    // --- Helper Methods for Height and Balance Factor ---

    /**
     * Returns the height of a given node.
     * Returns 0 if the node is null (height of an empty subtree).
     * @param node The AVLNode to get the height of.
     * @return The height of the node, or 0 if null.
     */
    private int getHeight(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    /**
     * Calculates the balance factor of a given node.
     * Balance Factor = Height of Left Subtree - Height of Right Subtree.
     * @param node The AVLNode to calculate the balance factor for.
     * @return The balance factor.
     */
    private int getBalanceFactor(AVLNode node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    /**
     * Updates the height of a node based on the heights of its children.
     * This should be called after any structural change to a node's children.
     * @param node The AVLNode whose height needs to be updated.
     */
    private void updateHeight(AVLNode node) {
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    // --- Rotation Operations ---

    /**
     * Performs a Right Rotation (LL Case).
     * @param y The node around which to rotate (the unbalanced node's left child).
     * @return The new root of the rotated subtree.
     */
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;   // x is the left child of y.
        AVLNode T2 = x.right; // T2 is the right child of x.

        // Perform rotation
        x.right = y;   // y becomes the right child of x.
        y.left = T2;   // T2 becomes the left child of y.

        // Update heights after rotation (bottom-up)
        updateHeight(y); // y's height changes first.
        updateHeight(x); // x's height changes second (as it's the new root).

        return x; // x is the new root of this subtree.
    }

    /**
     * Performs a Left Rotation (RR Case).
     * @param x The node around which to rotate (the unbalanced node's right child).
     * @return The new root of the rotated subtree.
     */
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;   // y is the right child of x.
        AVLNode T2 = y.left;   // T2 is the left child of y.

        // Perform rotation
        y.left = x;    // x becomes the left child of y.
        x.right = T2;  // T2 becomes the right child of x.

        // Update heights after rotation (bottom-up)
        updateHeight(x); // x's height changes first.
        updateHeight(y); // y's height changes second (as it's the new root).

        return y; // y is the new root of this subtree.
    }

    // --- 1. Insert Operation ---

    /**
     * Inserts a new data value into the AVL Tree.
     * Delegates to a recursive helper method that also handles balancing.
     * Time Complexity: O(log N) in both average and worst cases (due to balancing).
     * @param data The integer value to insert.
     */
    public void insert(int data) {
        root = insertRecursive(root, data);
        System.out.println("Inserted: " + data);
    }

    /**
     * Helper method for recursive insertion and balancing.
     * @param node The current node being examined in the recursion.
     * @param data The value to insert.
     * @return The updated (and balanced) root of the current subtree.
     */
    private AVLNode insertRecursive(AVLNode node, int data) {
        // 1. Perform standard BST insertion
        if (node == null) {
            return new AVLNode(data); // Found insertion point, create new node.
        }

        if (data < node.data) {
            node.left = insertRecursive(node.left, data);
        } else if (data > node.data) {
            node.right = insertRecursive(node.right, data);
        } else {
            // Duplicate values are not allowed in a strict BST/AVL tree.
            System.out.println("Warning: Duplicate value " + data + " not inserted.");
            return node;
        }

        // 2. Update height of the current node
        updateHeight(node);

        // 3. Get the balance factor of the current node
        int balance = getBalanceFactor(node);

        // 4. Perform Rotations if unbalanced (4 cases)

        // Left Left Case (LL)
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }

        // Right Right Case (RR)
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }

        // Left Right Case (LR)
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left); // First, left rotate the child.
            return rightRotate(node);          // Then, right rotate the current node.
        }

        // Right Left Case (RL)
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right); // First, right rotate the child.
            return leftRotate(node);           // Then, left rotate the current node.
        }

        return node; // Return the (potentially rebalanced) node.
    }

    // --- 2. Search Operation ---

    /**
     * Searches for a data value in the AVL Tree.
     * Delegates to a recursive helper method.
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
    private boolean searchRecursive(AVLNode node, int data) {
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

    // --- 3. Delete Operation ---

    /**
     * Deletes a data value from the AVL Tree.
     * Delegates to a recursive helper method that also handles balancing.
     * Time Complexity: O(log N) in both average and worst cases.
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
     * Helper method to find the node with the minimum value in a given subtree.
     * Used in deletion when a node has two children (to find inorder successor).
     * @param node The root of the subtree to search in.
     * @return The node with the minimum value.
     */
    private AVLNode findMinValueNode(AVLNode node) {
        AVLNode current = node;
        // Loop down to find the leftmost leaf
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Helper method for recursive deletion and balancing.
     * @param node The current node being examined.
     * @param data The value to delete.
     * @return The updated (and balanced) root of the current subtree.
     */
    private AVLNode deleteRecursive(AVLNode node, int data) {
        // 1. Perform standard BST deletion
        if (node == null) {
            return null; // Node not found (shouldn't happen if pre-checked)
        }

        if (data < node.data) {
            node.left = deleteRecursive(node.left, data);
        } else if (data > node.data) {
            node.right = deleteRecursive(node.right, data);
        } else { // Node with data to be deleted is found
            // Case 1: Node with no child or one child
            if ((node.left == null) || (node.right == null)) {
                AVLNode temp = null;
                if (node.left == null) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                // No child case
                if (temp == null) {
                    node = null; // Delete the node
                } else { // One child case
                    node = temp; // Replace node with its child
                }
            } else {
                // Case 2: Node with two children
                // Find the inorder successor (smallest in the right subtree)
                AVLNode temp = findMinValueNode(node.right);

                // Copy the inorder successor's data to this node
                node.data = temp.data;

                // Delete the inorder successor from the right subtree
                node.right = deleteRecursive(node.right, temp.data);
            }
        }

        // If the tree had only one node then return
        if (node == null) {
            return null;
        }

        // 2. Update height of the current node
        updateHeight(node);

        // 3. Get the balance factor of the current node
        int balance = getBalanceFactor(node);

        // 4. Perform Rotations if unbalanced (4 cases)
        // (Note: Deletion can sometimes cause a single rotation, or require a double rotation)

        // Left Left Case (LL)
        if (balance > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // Left Right Case (LR)
        if (balance > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case (RR)
        if (balance < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // Right Left Case (RL)
        if (balance < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // Return the (potentially rebalanced) node.
    }

    // --- 4. Tree Traversal Operations ---

    /**
     * Performs an Inorder Traversal of the AVL Tree.
     * Visits nodes in the order: Left -> Root -> Right.
     * Prints elements in sorted (ascending) order.
     * Time Complexity: O(N).
     */
    public void inorderTraversal() {
        System.out.print("Inorder Traversal: ");
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(AVLNode node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.data + " ");
            inorderRecursive(node.right);
        }
    }

    /**
     * Performs a Preorder Traversal of the AVL Tree.
     * Visits nodes in the order: Root -> Left -> Right.
     * Time Complexity: O(N).
     */
    public void preorderTraversal() {
        System.out.print("Preorder Traversal: ");
        preorderRecursive(root);
        System.out.println();
    }

    private void preorderRecursive(AVLNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
        }
    }

    /**
     * Performs a Postorder Traversal of the AVL Tree.
     * Visits nodes in the order: Left -> Right -> Root.
     * Time Complexity: O(N).
     */
    public void postorderTraversal() {
        System.out.print("Postorder Traversal: ");
        postorderRecursive(root);
        System.out.println();
    }

    private void postorderRecursive(AVLNode node) {
        if (node != null) {
            postorderRecursive(node.left);
            postorderRecursive(node.right);
            System.out.print(node.data + " ");
        }
    }

    /**
     * Performs a Level Order Traversal (BFS) of the AVL Tree.
     * Visits nodes level by level, from left to right. Uses a Queue.
     * Time Complexity: O(N).
     */
    public void levelOrderTraversal() {
        if (root == null) {
            System.out.println("Level Order Traversal: (empty tree)");
            return;
        }

        System.out.print("Level Order Traversal: ");
        Queue<AVLNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            AVLNode current = queue.poll();
            System.out.print(current.data + " ");
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
     * Checks if the AVL Tree is empty.
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

    private int sizeRecursive(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
    }

    /**
     * Calculates the height of the tree.
     * The height is the length of the longest path from the root to a leaf node.
     * Delegates to a helper method.
     * Time Complexity: O(1) - as height is stored in nodes.
     * @return The height of the tree. Returns 0 if the tree is empty (or -1 if using common convention for empty tree height).
     */
    public int height() {
        return getHeight(root); // Directly return height from root node.
    }

    /**
     * Main method to demonstrate basic AVL Tree operations.
     */
    public static void main(String[] args) {
        AVLTree avl = new AVLTree();

        System.out.println("--- Initial State ---");
        System.out.println("Is tree empty? " + avl.isEmpty()); // true
        System.out.println("Tree size: " + avl.size());       // 0
        System.out.println("Tree height: " + avl.height());   // 0 (as per getHeight(null) convention)
        avl.inorderTraversal(); // (empty tree)

        System.out.println("\n--- Inserting Elements (Observe Balancing) ---");
        // Insertion sequence that will trigger rotations
        avl.insert(10);
        avl.insert(20);
        avl.insert(30); // RR Case -> Left Rotation at 10
        avl.levelOrderTraversal(); // Expected: 20 10 30
        System.out.println("Height: " + avl.height() + ", Root: " + avl.root.data);

        avl.insert(25); // RL Case -> Right-Left Rotation
        avl.levelOrderTraversal(); // Expected: 20 10 30 25 (After 25 insert, 30's left child is 25, 20 is root)
        System.out.println("Height: " + avl.height() + ", Root: " + avl.root.data);

        avl.insert(5);  // LL Case -> Right Rotation
        avl.levelOrderTraversal();
        System.out.println("Height: " + avl.height() + ", Root: " + avl.root.data);

        avl.insert(7); // LR Case -> Left-Right Rotation
        avl.levelOrderTraversal();
        System.out.println("Height: " + avl.height() + ", Root: " + avl.root.data);

        avl.insert(40);
        avl.insert(60);
        avl.insert(70); // Another RR
        avl.levelOrderTraversal();
        System.out.println("Height: " + avl.height() + ", Root: " + avl.root.data);

        System.out.println("\n--- Tree State After Insertions ---");
        System.out.println("Is tree empty? " + avl.isEmpty());
        System.out.println("Tree size: " + avl.size());
        System.out.println("Tree height: " + avl.height());

        System.out.println("\n--- Tree Traversals ---");
        avl.inorderTraversal();    // Should be sorted
        avl.preorderTraversal();
        avl.postorderTraversal();
        avl.levelOrderTraversal();

        System.out.println("\n--- Searching Elements ---");
        System.out.println("Search for 25: " + avl.search(25));   // true
        System.out.println("Search for 100: " + avl.search(100)); // false

        System.out.println("\n--- Deleting Elements ---");
        avl.delete(7); // Delete a leaf node
        avl.levelOrderTraversal();
        System.out.println("Height: " + avl.height() + ", Root: " + avl.root.data);

        avl.delete(10); // Delete a node with one child
        avl.levelOrderTraversal();
        System.out.println("Height: " + avl.height() + ", Root: " + avl.root.data);

        avl.delete(30); // Delete a node with two children, might trigger rebalancing
        avl.levelOrderTraversal();
        System.out.println("Height: " + avl.height() + ", Root: " + avl.root.data);

        avl.delete(99); // Not found
        avl.levelOrderTraversal();

        System.out.println("\n--- Final Tree State ---");
        System.out.println("Tree size: " + avl.size());
        System.out.println("Tree height: " + avl.height());
    }
}
