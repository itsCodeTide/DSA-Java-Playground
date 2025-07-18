# Hash Tables and Hashing - Complete DSA Notes

## What is Hashing?

Hashing is a technique that transforms data of any size into a fixed-size value using a mathematical function called a hash function. Think of it like creating a unique fingerprint for each piece of data. Just as every person has a unique fingerprint that identifies them, hashing creates a unique identifier for data items.

**Real-world analogy:** Imagine a library with millions of books. Instead of searching through every shelf to find a specific book, the library assigns each book a unique catalog number. When you want to find a book, you look up its catalog number and go directly to that location. Hashing works similarly - it assigns each data item a unique "address" where it can be stored and retrieved quickly.

## Core Concept: Hash Function

A hash function is the mathematical engine that converts input data (called a "key") into an array index. The goal is to distribute keys as evenly as possible across the available storage space.

**Properties of a good hash function:**
- **Deterministic:** The same input always produces the same output
- **Uniform distribution:** Keys are spread evenly across the hash table
- **Fast computation:** The function should be quick to calculate
- **Avalanche effect:** Small changes in input create large changes in output

**Real-world example:** Consider a company's employee database. Instead of searching through thousands of employee records linearly, they use the employee ID as a key. A hash function might take the employee ID "EMP12345" and convert it to index 89 in the database array, allowing instant access to that employee's information.

## Hash Table Structure

A hash table (also called hash map) is an array-based data structure that uses hashing to store key-value pairs. It's like a sophisticated filing cabinet where each drawer is numbered, and you can instantly find any file by knowing its drawer number.

**Key components:**
- **Array:** The underlying storage structure
- **Hash function:** Converts keys to array indices
- **Buckets:** Individual slots in the array that store data
- **Load factor:** Ratio of filled slots to total slots

**Real-world example:** Think of a hotel's room assignment system. When guests check in, their reservation number (key) is processed through a hash function to determine their room number (index). The hotel staff can instantly locate any guest by running their reservation number through the same hash function.

## Hash Function Types

### Division Method
This is the most common and intuitive approach. The hash function is: `h(key) = key % table_size`

**Example:** If you have a hash table of size 10 and want to store a key with value 47:
- h(47) = 47 % 10 = 7
- The key 47 would be stored at index 7

**Real-world application:** Social security number assignment. The last digit of a SSN could determine which processing center handles that person's case.

### Multiplication Method
This method uses: `h(key) = floor(table_size * (key * A % 1))` where A is a constant between 0 and 1.

**Example:** With table_size = 10, A = 0.618 (golden ratio), and key = 47:
- 47 * 0.618 = 29.046
- 29.046 % 1 = 0.046 (fractional part)
- floor(10 * 0.046) = floor(0.46) = 0

**Real-world application:** Random number generation in computer graphics, where uniform distribution is crucial for realistic rendering.

### Universal Hashing
This method randomly selects a hash function from a family of functions to minimize worst-case scenarios.

**Real-world application:** Cryptographic applications where predictable hash functions could be exploited by attackers.

## Collision Resolution Strategies

Collisions occur when two different keys produce the same hash value. It's like two people being assigned the same locker number - you need a system to handle this conflict.

### Chaining (Separate Chaining)

**Concept:** Each array slot contains a linked list of all items that hash to that location.

**Real-world analogy:** Think of a parking garage where each level is numbered. If level 3 is full, you don't move cars to level 4. Instead, you create additional parking spaces on level 3 by building outward. Each level becomes a chain of parking spots.

**Detailed example:** Consider a hash table for a school's student records with size 7:
- Student "Alice" (ID: 101) hashes to index 3
- Student "Bob" (ID: 108) also hashes to index 3
- Instead of overwriting Alice's record, we create a chain at index 3: [Alice] -> [Bob]
- When searching for Bob, we go to index 3 and traverse the chain until we find him

**Advantages:**
- Simple to implement
- Hash table never becomes full
- Good performance with proper load factor management

**Disadvantages:**
- Extra memory overhead for storing pointers
- Poor cache performance due to pointer chasing
- Worst-case search time is O(n) if all keys hash to the same slot

### Open Addressing

**Concept:** When a collision occurs, we find an alternative empty slot within the hash table itself.

#### Linear Probing
**Method:** If slot h(key) is occupied, try h(key)+1, h(key)+2, etc.

**Real-world analogy:** Finding a parking spot in a crowded mall. If your preferred spot is taken, you check the next spot, then the next, until you find an empty one.

**Detailed example:** Hash table of size 7 for storing product codes:
- Product "A123" hashes to index 2, gets stored there
- Product "B456" also hashes to index 2, but it's occupied
- We check index 3 (empty), so B456 goes there
- Product "C789" hashes to index 2, but it's occupied
- We check index 3 (occupied), then index 4 (empty), so C789 goes there

**Primary clustering problem:** Keys tend to cluster together, creating long chains of occupied slots that slow down future operations.

#### Quadratic Probing
**Method:** If slot h(key) is occupied, try h(key)+1², h(key)+2², h(key)+3², etc.

**Advantage over linear probing:** Reduces primary clustering by spreading out the search pattern.

**Real-world application:** Network routing algorithms where you want to avoid congested paths by taking increasingly different routes.

#### Double Hashing
**Method:** Uses a second hash function to determine the step size for probing.

**Formula:** If h₁(key) is occupied, try h₁(key) + i*h₂(key) for i = 1, 2, 3...

**Real-world application:** Database indexing systems where you want maximum distribution to avoid clustering.

## Load Factor and Resizing

**Load Factor (α):** The ratio of the number of stored elements to the total number of slots in the hash table.

**Formula:** α = number of elements / table size

**Critical understanding:** Load factor directly impacts performance:
- **Low load factor (α < 0.5):** Fast operations but wastes memory
- **High load factor (α > 0.75):** Memory efficient but slower operations
- **Very high load factor (α > 1):** Only possible with chaining, significantly degrades performance

**Real-world analogy:** Think of a restaurant's seating capacity. If only 30% of tables are occupied (low load factor), service is fast but the restaurant isn't maximizing revenue. If 90% of tables are occupied (high load factor), the restaurant is profitable but service becomes slower due to congestion.

### Dynamic Resizing

When the load factor exceeds a threshold (typically 0.75), the hash table is resized to maintain performance.

**Resizing process:**
1. Create a new hash table (usually double the size)
2. Rehash all existing elements into the new table
3. Replace the old table with the new one

**Real-world example:** Consider an online shopping platform during Black Friday. As more users join, the system automatically scales up server capacity to maintain response times. Similarly, hash tables automatically resize to maintain optimal performance.

## Time Complexity Analysis

**Average Case:**
- **Insert:** O(1) - Direct access to calculated index
- **Search:** O(1) - Direct access to calculated index
- **Delete:** O(1) - Direct access to calculated index

**Worst Case:**
- **With chaining:** O(n) - All keys hash to the same slot
- **With open addressing:** O(n) - Must probe through entire table

**Real-world performance example:** Consider Amazon's product search. With proper hashing, when you search for "iPhone," the system doesn't scan through millions of products linearly. Instead, it uses the search term as a key, hashes it to find the relevant index, and retrieves results in constant time.

## Applications in Real World

### Database Indexing
**How it works:** Database systems use hash tables to create indexes on frequently queried columns.

**Example:** In a customer database, if you frequently search by email address, the database creates a hash table where:
- Key: email address
- Value: pointer to the customer record's location on disk

When you query "SELECT * FROM customers WHERE email = 'john@example.com'", the database hashes the email to instantly find the record location.

### Caching Systems
**How it works:** Web servers use hash tables to cache frequently requested content.

**Example:** When you visit a website, the server might cache the HTML content:
- Key: URL path ("/about-us")
- Value: cached HTML content

Subsequent requests for the same page are served instantly from the cache instead of regenerating the content.

### Password Storage
**How it works:** Systems store hashed passwords instead of plain text for security.

**Example:** When you create a password "MySecurePass123":
1. System applies a hash function: hash("MySecurePass123") = "a1b2c3d4..."
2. Only the hash is stored in the database
3. During login, your entered password is hashed and compared with the stored hash

### Symbol Tables in Compilers
**How it works:** Programming language compilers use hash tables to store variable and function names.

**Example:** When compiling code with variables like "userName" and "totalPrice":
- Key: variable name ("userName")
- Value: variable information (type, memory location, scope)

This allows the compiler to quickly lookup variable information during compilation.

### Network Routing
**How it works:** Internet routers use hash tables to determine where to forward network packets.

**Example:** When a packet needs to reach IP address "192.168.1.100":
- The router hashes the destination IP
- Uses the hash to quickly lookup the next hop in its routing table
- Forwards the packet without examining every possible route

## Common Hash Function Implementations

### String Hashing
**Challenge:** Converting text strings into numerical hash values.

**djb2 Algorithm approach:**
- Start with an initial value
- For each character, multiply current hash by 33 and add character's ASCII value
- Apply modulo to fit table size

**Example:** Hashing the string "hello"
- Start: hash = 5381
- 'h' (104): hash = 5381 * 33 + 104 = 177673
- 'e' (101): hash = 177673 * 33 + 101 = 5863310
- Continue for remaining characters...

### Integer Hashing
**Simple approach:** Use modulo operation with table size
**Advanced approach:** Multiply by large prime numbers to improve distribution

**Example:** For integer key 12345 with table size 1000:
- Simple: 12345 % 1000 = 345
- Advanced: (12345 * 31) % 1000 = 695

## Performance Considerations

### Memory Usage
**Chaining:** Requires extra memory for storing pointers in linked lists
**Open Addressing:** More memory efficient as it stores elements directly in the table

**Real-world trade-off:** Mobile applications often prefer open addressing to minimize memory usage, while server applications might use chaining for predictable performance.

### Cache Performance
**Chaining:** Poor cache locality due to pointer chasing through linked lists
**Open Addressing:** Better cache performance as elements are stored contiguously

**Real-world impact:** Modern CPUs are optimized for accessing nearby memory locations. Open addressing takes advantage of this by keeping related data close together.

### Predictability
**Chaining:** More predictable performance as it doesn't suffer from clustering
**Open Addressing:** Can have variable performance due to clustering effects

**Real-world consideration:** Real-time systems often prefer chaining because it provides more consistent response times.

## Advanced Concepts

### Consistent Hashing
**Purpose:** Used in distributed systems where hash table size changes frequently.

**Real-world application:** Content delivery networks (CDNs) like Cloudflare use consistent hashing to distribute cached content across servers. When servers are added or removed, only a small portion of data needs to be redistributed.

### Cryptographic Hashing
**Purpose:** Create secure, irreversible hash values for security applications.

**Real-world applications:**
- **Blockchain:** Bitcoin uses SHA-256 hashing for proof-of-work
- **Digital signatures:** Ensuring document integrity
- **Password verification:** Secure authentication systems

### Bloom Filters
**Concept:** Space-efficient probabilistic data structure that uses multiple hash functions.

**Real-world application:** Google Chrome uses Bloom filters to check if a website is potentially malicious before making a network request. It provides quick "definitely not malicious" or "possibly malicious" answers without storing the entire malicious URL database locally.

## Common Pitfalls and Solutions

### Hash Function Selection
**Problem:** Poor hash functions create uneven distribution
**Solution:** Use well-tested hash functions like djb2 for strings or multiply-shift for integers

### Load Factor Management
**Problem:** Letting load factor get too high degrades performance
**Solution:** Implement automatic resizing when load factor exceeds 0.75

### Collision Handling
**Problem:** Not handling collisions properly leads to data loss
**Solution:** Choose appropriate collision resolution strategy based on your use case

### Security Considerations
**Problem:** Predictable hash functions can be exploited
**Solution:** Use cryptographically secure hash functions for sensitive applications

## When to Use Hash Tables

**Ideal scenarios:**
- Need fast insertion, deletion, and lookup operations
- Data access patterns are random rather than sequential
- Memory usage is not extremely constrained
- You don't need to maintain sorted order

**Avoid when:**
- You need to maintain sorted order of elements
- Memory is extremely limited
- You frequently need to find minimum or maximum elements
- Range queries are common

**Real-world decision example:** An online gaming platform needs to track millions of active players. Hash tables are perfect because they need to quickly check if a player is online (lookup), add players when they login (insert), and remove players when they logout (delete). They don't need players sorted by name or level, making hash tables the optimal choice.

This comprehensive understanding of hash tables will serve as your foundation for implementing efficient data storage and retrieval systems in your programming projects.
