// File: Trie.java
//Tries: https://www.geeksforgeeks.org/dsa/trie-insert-and-search/

import java.util.HashMap;
import java.util.Map;
import java.util.Set; // For getting keyset in delete
import java.util.Stack; // For iterative deletion (backtracking)

/**
 * Represents a single node in a Trie (Prefix Tree).
 * Each node stores a map of its children (character -> TrieNode)
 * and a flag indicating if it marks the end of a valid word.
 */
class TrieNode {
    // Using a HashMap for children provides flexibility for any character set
    // and is memory-efficient for sparse child sets.
    Map<Character, TrieNode> children;
    boolean isEndOfWord; // True if this node completes a valid word.

    /**
     * Constructor for TrieNode.
     * Initializes an empty map for children and sets isEndOfWord to false.
     */
    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

/**
 * Implements a Trie (Prefix Tree) data structure.
 * Provides efficient operations for inserting, searching, checking prefixes,
 * and deleting strings.
 */
public class Trie {
    private TrieNode root; // The root of the Trie. It typically represents an empty string.

    /**
     * Constructor for Trie.
     * Initializes the Trie with a root node.
     */
    public Trie() {
        root = new TrieNode();
        System.out.println("Trie created.");
    }

    // --- 1. insert(word): Adds a word to the Trie ---

    /**
     * Inserts a word into the Trie.
     * Time Complexity: O(L), where L is the length of the word.
     * @param word The word to insert.
     */
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            // If the current node does not have a child for 'ch', create one.
            current.children.putIfAbsent(ch, new TrieNode());
            // Move to the child node.
            current = current.children.get(ch);
        }
        // After iterating through all characters, mark the last node as the end of a word.
        current.isEndOfWord = true;
        System.out.println("Inserted: \"" + word + "\"");
    }

    // --- 2. search(word): Checks if a word exists in the Trie ---

    /**
     * Searches for a word in the Trie.
     * Time Complexity: O(L), where L is the length of the word.
     * @param word The word to search for.
     * @return true if the word exists in the Trie, false otherwise.
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            // If the current node does not have a child for 'ch', the word cannot exist.
            if (!current.children.containsKey(ch)) {
                System.out.println("Search for \"" + word + "\": false (path missing)");
                return false;
            }
            // Move to the child node.
            current = current.children.get(ch);
        }
        // After traversing all characters, check if the last node marks the end of a word.
        boolean found = current.isEndOfWord;
        System.out.println("Search for \"" + word + "\": " + found);
        return found;
    }

    // --- 3. startsWith(prefix): Checks if any word starts with a given prefix ---

    /**
     * Checks if there is any word in the Trie that starts with the given prefix.
     * Time Complexity: O(L), where L is the length of the prefix.
     * @param prefix The prefix to search for.
     * @return true if any word starts with the prefix, false otherwise.
     */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            // If the current node does not have a child for 'ch', no word can start with this prefix.
            if (!current.children.containsKey(ch)) {
                System.out.println("Starts with \"" + prefix + "\": false (prefix path missing)");
                return false;
            }
            // Move to the child node.
            current = current.children.get(ch);
        }
        // If we successfully traversed the entire prefix, it means the prefix exists.
        boolean found = true; // The prefix path exists.
        System.out.println("Starts with \"" + prefix + "\": " + found);
        return found;
    }

    // --- 4. delete(word): Removes a word from the Trie ---

    /**
     * Deletes a word from the Trie.
     * This operation involves unmarking the end of the word and potentially
     * removing nodes that are no longer part of any other word's prefix.
     * Time Complexity: O(L), where L is the length of the word.
     * @param word The word to delete.
     * @return true if the word was found and deleted, false otherwise.
     */
    public boolean delete(String word) {
        // Use a stack to keep track of the path from root to the last character of the word.
        Stack<TrieNode> path = new Stack<>();
        Stack<Character> chars = new Stack<>(); // To store characters corresponding to nodes in path.

        TrieNode current = root;
        // Traverse to find the word, pushing nodes onto the stack.
        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                System.out.println("Cannot delete \"" + word + "\": Word not found.");
                return false; // Word not found.
            }
            path.push(current); // Push parent node
            chars.push(ch);     // Push character leading to next node
            current = current.children.get(ch);
        }

        // If the word doesn't actually end at this node, it's not the full word.
        if (!current.isEndOfWord) {
            System.out.println("Cannot delete \"" + word + "\": Word found as prefix, but not a complete word.");
            return false;
        }

        // Step 1: Unmark the end of the word.
        current.isEndOfWord = false;
        System.out.println("Unmarked \"" + word + "\" as end of word.");

        // Step 2: Backtrack and delete nodes if they are no longer needed.
        // The last node (current) is now the one we potentially delete from.
        // We backtrack using the 'path' stack.
        while (!path.isEmpty()) {
            TrieNode parent = path.pop();
            char ch = chars.pop();
            TrieNode childToDelete = parent.children.get(ch);

            // A node can be deleted if:
            // 1. It is NOT the end of another word (isEndOfWord is false).
            // 2. It has NO other children (its children map is empty).
            if (!childToDelete.isEndOfWord && childToDelete.children.isEmpty()) {
                parent.children.remove(ch); // Remove the child from its parent's map.
                System.out.println("Deleted node for char '" + ch + "' (data: " + childToDelete.hashCode() + ")");
            } else {
                // If the node is an end of another word OR has other children, stop deleting.
                break;
            }
        }
        System.out.println("Successfully deleted \"" + word + "\".");
        return true;
    }

    /**
     * Utility method to print all words in the Trie.
     * Uses a recursive helper.
     */
    public void printAllWords() {
        System.out.println("\n--- All words in Trie ---");
        List<String> words = new ArrayList<>();
        findAllWords(root, "", words);
        if (words.isEmpty()) {
            System.out.println("(No words in Trie)");
        } else {
            for (String word : words) {
                System.out.println("- " + word);
            }
        }
        System.out.println("-------------------------");
    }

    /**
     * Helper recursive method to find all words from a given node.
     * @param node The current TrieNode.
     * @param currentPrefix The prefix built so far to reach this node.
     * @param wordList The list to add found words to.
     */
    private void findAllWords(TrieNode node, String currentPrefix, List<String> wordList) {
        if (node == null) {
            return;
        }

        if (node.isEndOfWord) {
            wordList.add(currentPrefix); // If this node marks end of a word, add it.
        }

        // Recursively call for all children.
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            findAllWords(entry.getValue(), currentPrefix + entry.getKey(), wordList);
        }
    }


    /**
     * Main method to demonstrate basic Trie operations.
     */
    public static void main(String[] args) {
        Trie trie = new Trie();

        System.out.println("--- Initial State ---");
        System.out.println("Is Trie empty? (Implicitly yes if no words inserted)");
        trie.printAllWords();

        System.out.println("\n--- Insert Operations ---");
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apricot");
        trie.insert("apply");
        trie.insert("banana");
        trie.insert("band");

        trie.printAllWords();

        System.out.println("\n--- Search Operations ---");
        trie.search("apple");   // true
        trie.search("app");     // true
        trie.search("ap");      // false (it's a prefix, not a word)
        trie.search("apply");   // true
        trie.search("apricot"); // true
        trie.search("apricots"); // false
        trie.search("banana");  // true
        trie.search("band");    // true
        trie.search("ban");     // false

        System.out.println("\n--- StartsWith Operations ---");
        trie.startsWith("ap");    // true
        trie.startsWith("appl");  // true
        trie.startsWith("ban");   // true
        trie.startsWith("xyz");   // false

        System.out.println("\n--- Delete Operations ---");
        trie.delete("app");     // Delete "app" (should unmark 'p' node, but 'p' is still prefix for "apple", "apply", "apricot")
        trie.printAllWords();   // "app" should be gone.

        trie.delete("apple");   // Delete "apple" (should delete 'e' and 'l' nodes, as 'l' is no longer a prefix for anything else)
        trie.printAllWords();   // "apple" should be gone.

        trie.delete("apricot"); // Delete "apricot"
        trie.printAllWords();   // "apricot" should be gone.

        trie.delete("apply");   // Delete "apply" (This should now clean up 'y', and 'l' if no other words use 'l' from 'appl' prefix)
        trie.printAllWords();   // "apply" should be gone.

        System.out.println("\n--- After extensive deletions ---");
        System.out.println("Starts with \"ap\" after deletions: " + trie.startsWith("ap")); // false, as all "ap" words are gone
        System.out.println("Search for \"app\": " + trie.search("app")); // false
        System.out.println("Search for \"apple\": " + trie.search("apple")); // false

        System.out.println("\n--- Final Remaining Words ---");
        trie.printAllWords(); // Should only show "banana", "band"
    }
}
