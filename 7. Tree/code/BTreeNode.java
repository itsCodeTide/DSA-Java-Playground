// File: BTreeNode.java (Conceptual - Simplified)

import java.util.ArrayList;
import java.util.List;

/**
 * Conceptual representation of a node in a B-Tree or B+ Tree.
 * This is a highly simplified structure to illustrate the concept,
 * not a full, robust implementation.
 *
 * For a B-Tree of order M:
 * - Each node has (M-1) keys maximum.
 * - Each node has M children maximum.
 */
class BTreeNode {
    List<Integer> keys; // Sorted list of keys stored in this node.
    List<BTreeNode> children; // List of child pointers (subtrees).
    boolean isLeaf;     // True if this is a leaf node, false otherwise.
    int t;              // Minimum degree (or order/2), commonly used in CLRS definition.
                        // A node has at least t-1 keys and t children. Max 2t-1 keys, 2t children.
                        // So, M = 2t. Max keys = M-1 = 2t-1. Min keys = t-1.

    // For B+ Tree, leaf nodes would also have a 'nextLeaf' pointer:
    // BTreeNode nextLeaf; // Only for leaf nodes in B+ Tree

    public BTreeNode(int t, boolean isLeaf) {
        this.t = t;
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>(2 * t - 1); // Max keys a node can hold.
        this.children = new ArrayList<>(2 * t); // Max children a node can hold.
    }

    // --- Conceptual Search Method (Simplified) ---
    // In a real B-Tree, this would return the (key, value) pair or a pointer to it.
    public BTreeNode search(int key) {
        int i = 0;
        // Find the first key greater than or equal to 'key'
        while (i < keys.size() && key > keys.get(i)) {
            i++;
        }

        // If key is found in this node
        if (i < keys.size() && key == keys.get(i)) {
            System.out.println("Key " + key + " found in node: " + keys);
            return this; // Return the node where key is found
        }

        // If this is a leaf node and key not found here
        if (isLeaf) {
            System.out.println("Key " + key + " not found. Reached leaf node: " + keys);
            return null;
        }

        // If not a leaf, go to the appropriate child
        System.out.println("Key " + key + " not in current node " + keys + ". Going to child " + i);
        return children.get(i).search(key); // Recursive call to child
    }

    // --- Conceptual Insert Method (Highly Simplified - No Splitting Logic) ---
    // A full insert would involve splitting nodes and promoting keys up the tree.
    public void insertKey(int key) {
        // This simplified method just adds to a leaf if space, or indicates overflow.
        if (isLeaf) {
            if (keys.size() < (2 * t - 1)) { // Check if there's space (max keys)
                int i = 0;
                while (i < keys.size() && key > keys.get(i)) {
                    i++;
                }
                keys.add(i, key); // Insert in sorted position
                System.out.println("Inserted " + key + " into leaf: " + keys);
            } else {
                System.out.println("Leaf node " + keys + " is full. Would trigger split for " + key);
                // In a real B-Tree, this would trigger a split and promotion.
            }
        } else {
            // For internal nodes, insert would involve finding correct child and then handling split propagation.
            System.out.println("Cannot directly insert into internal node. Traverse to leaf for " + key);
        }
    }

    // --- Conceptual Delete Method (Highly Simplified - No Merging/Redistribution Logic) ---
    // A full delete would involve complex borrowing/merging and key demotion.
    public void deleteKey(int key) {
        // This simplified method just removes from a leaf if found.
        if (isLeaf) {
            if (keys.remove(Integer.valueOf(key))) { // Remove by object value
                System.out.println("Deleted " + key + " from leaf: " + keys);
                // In a real B-Tree, this would trigger underflow check and rebalancing.
            } else {
                System.out.println("Key " + key + " not found in leaf for deletion: " + keys);
            }
        } else {
            System.out.println("Cannot directly delete from internal node. Traverse to leaf for " + key);
        }
    }

    // --- Utility to print node content ---
    @Override
    public String toString() {
        return keys.toString();
    }
}

/**
 * Conceptual B-Tree Class.
 * A full B-Tree implementation is very extensive. This provides a high-level
 * structure and demonstrates how operations would conceptually interact.
 */
public class BTree {
    private BTreeNode root;
    private int t; // Minimum degree (CLRS notation). Order M = 2t.

    public BTree(int t) {
        this.t = t;
        this.root = new BTreeNode(t, true); // Initially, root is a leaf.
        System.out.println("B-Tree created with minimum degree t=" + t + " (Order M=" + (2*t) + ")");
    }

    // --- Main Operations (High-level calls) ---

    public BTreeNode search(int key) {
        System.out.println("\n--- Searching for " + key + " ---");
        return root.search(key);
    }

    // Full insert method would handle root splitting and propagation
    public void insert(int key) {
        System.out.println("\n--- Inserting " + key + " ---");
        // Simplified: In a real B-Tree, if root is full, it splits and a new root is created.
        // Otherwise, it finds the leaf and calls insertKey on it, handling splits recursively.
        if (root.keys.size() == (2 * t - 1)) { // Root is full
            BTreeNode s = new BTreeNode(t, false); // New root will be internal
            s.children.add(0, root); // Old root becomes first child
            // This is where a complex splitChild method would be called
            // s.splitChild(0, root); // This method would split root, promote middle key to s
            System.out.println("Root is full. Would split and create new root for " + key);
            // For this conceptual code, we'll just insert into a leaf if possible.
            // A proper implementation would handle the split and then insert.
            root.insertKey(key); // Simplified: might still fail if leaf is full
        } else {
            // Find appropriate leaf and insert. This is simplified.
            // A real insert would traverse, then call a non-full insert on the leaf.
            root.insertKey(key);
        }
    }

    // Full delete method would handle complex cases
    public void delete(int key) {
        System.out.println("\n--- Deleting " + key + " ---");
        // A full delete would involve finding the key, handling 3 cases (leaf, 1 child, 2 children),
        // and then fixing underflow with borrowing/merging, propagating up.
        root.deleteKey(key); // Simplified: might just remove from leaf
    }

    // --- Traversal (Conceptual - Inorder for illustrative purposes) ---
    public void inorderTraversal() {
        System.out.print("\nInorder Traversal (Conceptual): ");
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(BTreeNode node) {
        if (node == null) return;

        // Traverse left child, then current key, then right child
        // For a B-Tree node, it's more like:
        // Traverse children[0]
        // Visit keys[0]
        // Traverse children[1]
        // Visit keys[1]
        // ...
        // Traverse children[k]
        int i;
        for (i = 0; i < node.keys.size(); i++) {
            if (!node.isLeaf) {
                inorderRecursive(node.children.get(i));
            }
            System.out.print(node.keys.get(i) + " ");
        }
        if (!node.isLeaf) {
            inorderRecursive(node.children.get(i)); // Traverse last child
        }
    }

    // --- Main Method for Demonstration ---
    public static void main(String[] args) {
        // Create a B-Tree with minimum degree t=2.
        // This means each node has between 1 and 3 keys, and between 2 and 4 children.
        // (Min keys = t-1 = 1, Max keys = 2t-1 = 3)
        // (Min children = t = 2, Max children = 2t = 4)
        BTree bTree = new BTree(2); // Order M = 4

        System.out.println("\n--- Initial Tree ---");
        bTree.inorderTraversal();
        System.out.println("Is root leaf? " + bTree.root.isLeaf);

        // Insertions (will trigger splits in a full implementation)
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);
        bTree.insert(40); // This would cause root to split in a real B-Tree (10,20,30,40) -> (20) / (10) (30,40)
        bTree.insert(50);
        bTree.insert(60);
        bTree.insert(70);
        bTree.insert(80);
        bTree.insert(90);

        bTree.inorderTraversal(); // Will show flat list in this simplified version

        // Searches
        bTree.search(30);
        bTree.search(95);

        // Deletions
        bTree.delete(20);
        bTree.delete(100);

        bTree.inorderTraversal(); // Will show flat list in this simplified version
    }
