//Hash in detail: https://www.tutorialspoint.com/data_structures_algorithms/hashing_data_structure.htm
// File: MyHashTable.java

import java.util.ArrayList;
import java.util.LinkedList; // For separate chaining
import java.util.List;
import java.util.Objects; // For Objects.hashCode()

/**
 * Represents a key-value pair stored in the hash table.
 * Used as nodes in the linked lists for separate chaining.
 */
class HashNode<K, V> {
    K key;
    V value;
    // No 'next' pointer needed here if using LinkedList directly in buckets.
    // If implementing custom linked list, it would be here.

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + key + "=" + value + "}";
    }

    // It's good practice to override equals and hashCode for custom objects used as keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashNode<?, ?> hashNode = (HashNode<?, ?>) o;
        return Objects.equals(key, hashNode.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}

/**
 * Implements a simple Hash Table using Separate Chaining for collision resolution.
 * Keys can be of any type (K), values can be of any type (V).
 */
public class MyHashTable<K, V> {
    // The array of buckets. Each bucket is a LinkedList of HashNodes.
    private List<LinkedList<HashNode<K, V>>> buckets;
    private int numBuckets; // Total number of buckets in the hash table.
    private int size;       // Current number of key-value pairs stored.

    // Load factor threshold for resizing.
    private static final double DEFAULT_LOAD_FACTOR_THRESHOLD = 0.75;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    /**
     * Constructor for MyHashTable with default initial capacity.
     */
    public MyHashTable() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Constructor for MyHashTable with a specified initial capacity.
     * @param initialCapacity The initial number of buckets.
     */
    public MyHashTable(int initialCapacity) {
        this.numBuckets = initialCapacity;
        this.buckets = new ArrayList<>(numBuckets);
        // Initialize each bucket with an empty LinkedList.
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new LinkedList<>());
        }
        this.size = 0;
        System.out.println("Hash Table created with " + numBuckets + " buckets.");
    }

    // --- Helper: Hash Function ---

    /**
     * Computes the hash value (bucket index) for a given key.
     * Uses Java's built-in hashCode() and then applies modulo for bucket mapping.
     * Time Complexity: O(1) on average (depends on key's hashCode() implementation).
     * @param key The key to hash.
     * @return The index of the bucket where the key should be stored.
     */
    private int getBucketIndex(K key) {
        // Use Java's Object.hashCode() for the key, then take absolute value
        // to ensure a non-negative hash, and finally modulo by numBuckets.
        return Math.abs(key.hashCode()) % numBuckets;
    }

    // --- 1. put(key, value): Insert/Update Operation ---

    /**
     * Inserts a new key-value pair into the hash table.
     * If the key already exists, its value is updated.
     * Time Complexity: O(1) on average (hash function + linked list add/update).
     * Worst Case: O(N) if all keys hash to the same bucket.
     * @param key The key to insert or update.
     * @param value The value associated with the key.
     */
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<HashNode<K, V>> currentBucket = buckets.get(bucketIndex);

        // Check if the key already exists in this bucket (collision or update)
        for (HashNode<K, V> node : currentBucket) {
            if (node.key.equals(key)) { // Found existing key
                V oldValue = node.value;
                node.value = value; // Update value
                System.out.println("Updated key '" + key + "' from '" + oldValue + "' to '" + value + "' at bucket " + bucketIndex);
                return;
            }
        }

        // Key does not exist, add new node to the bucket's linked list
        currentBucket.add(new HashNode<>(key, value));
        size++;
        System.out.println("Added key '" + key + "' with value '" + value + "' to bucket " + bucketIndex);

        // Check load factor and resize if necessary
        if ((double) size / numBuckets > DEFAULT_LOAD_FACTOR_THRESHOLD) {
            resize();
        }
    }

    // --- 2. get(key): Search/Lookup Operation ---

    /**
     * Retrieves the value associated with the given key.
     * Time Complexity: O(1) on average. Worst Case: O(N).
     * @param key The key to search for.
     * @return The value associated with the key, or null if the key is not found.
     */
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<HashNode<K, V>> currentBucket = buckets.get(bucketIndex);

        for (HashNode<K, V> node : currentBucket) {
            if (node.key.equals(key)) { // Found the key
                System.out.println("Retrieved value '" + node.value + "' for key '" + key + "' from bucket " + bucketIndex);
                return node.value;
            }
        }
        System.out.println("Key '" + key + "' not found.");
        return null; // Key not found
    }

    // --- 3. remove(key): Delete Operation ---

    /**
     * Removes the key-value pair associated with the given key.
     * Time Complexity: O(1) on average. Worst Case: O(N).
     * @param key The key to remove.
     * @return The value that was removed, or null if the key was not found.
     */
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<HashNode<K, V>> currentBucket = buckets.get(bucketIndex);

        HashNode<K, V> nodeToRemove = null;
        for (HashNode<K, V> node : currentBucket) {
            if (node.key.equals(key)) { // Found the key
                nodeToRemove = node;
                break;
            }
        }

        if (nodeToRemove != null) {
            currentBucket.remove(nodeToRemove);
            size--;
            System.out.println("Removed key '" + key + "' with value '" + nodeToRemove.value + "' from bucket " + bucketIndex);
            return nodeToRemove.value;
        }
        System.out.println("Key '" + key + "' not found for removal.");
        return null; // Key not found
    }

    // --- 4. containsKey(key): Check Key Existence ---

    /**
     * Checks if the hash table contains the specified key.
     * Time Complexity: O(1) on average. Worst Case: O(N).
     * @param key The key to check for.
     * @return true if the key exists in the hash table, false otherwise.
     */
    public boolean containsKey(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<HashNode<K, V>> currentBucket = buckets.get(bucketIndex);

        for (HashNode<K, V> node : currentBucket) {
            if (node.key.equals(key)) {
                System.out.println("Key '" + key + "' exists.");
                return true;
            }
        }
        System.out.println("Key '" + key + "' does not exist.");
        return false;
    }

    // --- 5. size(): Get Number of Elements ---

    /**
     * Returns the number of key-value pairs currently stored in the hash table.
     * Time Complexity: O(1).
     * @return The number of elements.
     */
    public int size() {
        return size;
    }

    // --- 6. isEmpty(): Check if Hash Table is Empty ---

    /**
     * Checks if the hash table contains any key-value pairs.
     * Time Complexity: O(1).
     * @return true if the hash table is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    // --- Helper: Resize (Rehashing) ---

    /**
     * Resizes the hash table when the load factor threshold is exceeded.
     * Creates a new, larger array of buckets and rehashes all existing elements.
     * Time Complexity: O(N) where N is the current number of elements.
     */
    private void resize() {
        int oldNumBuckets = numBuckets;
        numBuckets *= 2; // Double the number of buckets.
        List<LinkedList<HashNode<K, V>>> oldBuckets = buckets;
        
        // Create new buckets list and initialize.
        this.buckets = new ArrayList<>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new LinkedList<>());
        }
        this.size = 0; // Reset size, as elements will be re-added.

        System.out.println("\n--- Resizing Hash Table from " + oldNumBuckets + " to " + numBuckets + " buckets ---");

        // Rehash all elements from old buckets to new buckets.
        for (LinkedList<HashNode<K, V>> bucket : oldBuckets) {
            for (HashNode<K, V> node : bucket) {
                put(node.key, node.value); // Use the put method to re-insert.
            }
        }
        System.out.println("--- Resizing complete. ---");
    }

    // --- Utility: Print Hash Table ---

    /**
     * Prints the current state of the hash table, showing each bucket and its contents.
     */
    public void printHashTable() {
        System.out.println("\n--- Current Hash Table State (Size: " + size + ", Buckets: " + numBuckets + ") ---");
        for (int i = 0; i < numBuckets; i++) {
            System.out.print("Bucket " + i + ": ");
            if (buckets.get(i).isEmpty()) {
                System.out.println("[]");
            } else {
                System.out.println(buckets.get(i));
            }
        }
        System.out.println("-------------------------------------------------");
    }

    /**
     * Main method to demonstrate all basic Hash Table operations.
     */
    public static void main(String[] args) {
        MyHashTable<String, Integer> studentAges = new MyHashTable<>(5); // Small initial capacity to demonstrate resizing

        System.out.println("--- Initial State ---");
        System.out.println("Is hash table empty? " + studentAges.isEmpty()); // true
        System.out.println("Size: " + studentAges.size());                 // 0
        studentAges.printHashTable();

        System.out.println("\n--- Put (Insert) Operations ---");
        studentAges.put("Alice", 20);  // Hash for "Alice" might be 0, 1, 2, 3, 4
        studentAges.put("Bob", 22);
        studentAges.put("Charlie", 21);
        studentAges.put("David", 23);
        studentAges.put("Eve", 19);
        studentAges.put("Frank", 24); // This might trigger a resize due to load factor

        studentAges.printHashTable();

        System.out.println("\n--- Put (Update) Operation ---");
        studentAges.put("Alice", 21); // Update Alice's age
        studentAges.printHashTable();

        System.out.println("\n--- Get (Lookup) Operations ---");
        System.out.println("Age of Charlie: " + studentAges.get("Charlie")); // 21
        System.out.println("Age of Grace: " + studentAges.get("Grace"));   // null

        System.out.println("\n--- ContainsKey Operations ---");
        studentAges.containsKey("Bob");    // true
        studentAges.containsKey("Zoe");    // false

        System.out.println("\n--- Remove (Delete) Operations ---");
        studentAges.remove("David"); // Remove David
        studentAges.printHashTable();
        System.out.println("Size after removal: " + studentAges.size()); // Should be 5 now

        studentAges.remove("Zoe"); // Try to remove non-existent key

        System.out.println("\n--- Final State ---");
        System.out.println("Is hash table empty? " + studentAges.isEmpty()); // false
        System.out.println("Final size: " + studentAges.size()); // 5
        studentAges.printHashTable();
    }
}
