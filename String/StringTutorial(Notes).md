## Strings in DSA: A Full Tutorial (Basic to Advanced)

Just like arrays, strings are fundamental data structures in computer science. While an array stores a collection of elements of the *same data type*, a string specifically stores a **sequence of characters**.

Think of a string as a special type of array where each element is a character.

### What is a String?

A **string** is an immutable (in many languages like Java, Python) or mutable (in languages like C/C++) sequence of characters. It is used to represent text.

**Key characteristics:**

* **Sequence of Characters:** Composed of individual characters (letters, numbers, symbols, spaces).
* **Indexed:** Like arrays, characters in a string are accessed using an index, typically starting from **0**.
* **Immutability vs. Mutability:**
    * **Immutable (Java, Python, JavaScript, C#):** Once a string is created, its content cannot be changed. Any operation that *seems* to modify a string actually creates a *new* string. This has significant implications for performance and memory.
    * **Mutable (C/C++ char arrays):** In C/C++, strings are often represented as null-terminated character arrays, which *can* be modified in place.
* **Null Termination (C/C++):** In C/C++, strings are typically terminated by a special null character (`\0`) to mark the end of the string. This is not explicitly managed in higher-level languages.

#### Why are Strings Important?

* **Representing Text:** Essential for handling names, sentences, paragraphs, code, file paths, URLs – virtually any textual data.
* **Data Serialization:** Used to store and transmit data (e.g., JSON, XML).
* **User Interface:** Displaying messages, input fields, labels.
* **Pattern Matching & Text Processing:** Core to tasks like searching for specific words, replacing text, validating input.

### Basic String Operations

Let's look at the fundamental operations you can perform on strings. We'll use conceptual syntax and provide examples for common languages.

#### 1. Declaration and Initialization

**Syntax (conceptual):**

```
String stringName = "characters";
```

**Examples:**

* **Java:**
    ```java
    String message = "Hello, World!";
    String emptyString = ""; // An empty string
    ```
* **Python:**
    ```python
    name = "Alice"
    greeting = 'Welcome!' # Single quotes also work
    ```
* **C++ (using `std::string`):**
    ```cpp
    #include <string>
    std::string city = "New York";
    std::string another_city("London");
    ```
* **C (character array):**
    ```c
    char country[] = "India"; // Null-terminated character array
    char another_country[10] = "Canada"; // Fixed size, ensure space for \0
    ```

#### 2. Accessing Characters

You can access individual characters using their index.

**Syntax:**

```
stringName[index] // Conceptual
```

**Examples:**

* **Java:**
    ```java
    String s = "Hello";
    char firstChar = s.charAt(0); // 'H'
    char thirdChar = s.charAt(2); // 'l'
    // s.charAt(5) would throw an IndexOutOfBoundsException
    ```
* **Python:**
    ```python
    s = "Python"
    first_char = s[0] # 'P'
    last_char = s[5]  # 'n' (or s[-1] for last char)
    # s[6] would throw an IndexError
    ```
* **C++:**
    ```cpp
    std::string str = "World";
    char first = str[0]; // 'W'
    char last = str[4];  // 'd'
    // str.at(5) would throw an out_of_range exception
    ```
* **C:**
    ```c
    char str[] = "C Lang";
    char c1 = str[0]; // 'C'
    char c2 = str[5]; // 'g'
    // Remember null terminator at str[6]
    ```

#### 3. String Length

Get the number of characters in a string.

**Syntax:**

```
stringName.length() // Java, C++
len(stringName)     // Python
strlen(charArray)   // C
```

**Examples:**

* **Java:**
    ```java
    String text = "programming";
    int len = text.length(); // 11
    ```
* **Python:**
    ```python
    text = "data"
    length = len(text) # 4
    ```

#### 4. Concatenation (Joining Strings)

Combining two or more strings.

**Syntax:**

```
string1 + string2 // Most languages
string1.concat(string2) // Java specific
```

**Examples:**

* **Java:**
    ```java
    String firstName = "John";
    String lastName = "Doe";
    String fullName = firstName + " " + lastName; // "John Doe"
    String anotherFullName = firstName.concat(" ").concat(lastName); // "John Doe"
    ```
* **Python:**
    ```python
    greeting = "Hello"
    name = "World"
    full_greeting = greeting + ", " + name + "!" # "Hello, World!"
    ```
* **C++:**
    ```cpp
    std::string s1 = "Good";
    std::string s2 = "Morning";
    std::string combined = s1 + " " + s2; // "Good Morning"
    ```

#### 5. Substrings

Extracting a portion of a string.

**Syntax (conceptual - often `startIndex` and `endIndex` or `length`):**

```
stringName.substring(startIndex, endIndex)
```

**Examples:**

* **Java:**
    ```java
    String sentence = "The quick brown fox";
    String word = sentence.substring(4, 9); // "quick" (index 4 to 8, 9 is exclusive)
    String rest = sentence.substring(10);   // "brown fox" (from index 10 to end)
    ```
* **Python:**
    ```python
    sentence = "Python is fun"
    word = sentence[0:6]  # "Python" (index 0 to 5, 6 is exclusive)
    rest = sentence[7:]   # "is fun" (from index 7 to end)
    part = sentence[:6]   # "Python" (from start to index 5)
    ```
* **C++:**
    ```cpp
    std::string s = "programming";
    std::string sub = s.substr(3, 4); // "gram" (start at index 3, length 4)
    std::string end_part = s.substr(7); // "mming" (from index 7 to end)
    ```

#### 6. Comparison

Comparing two strings for equality or lexicographical order.

**Examples:**

* **Java:**
    ```java
    String s1 = "apple";
    String s2 = "Apple";
    String s3 = "apple";

    System.out.println(s1.equals(s2));        // false (case-sensitive)
    System.out.println(s1.equalsIgnoreCase(s2)); // true
    System.out.println(s1.equals(s3));        // true
    // For lexicographical comparison: s1.compareTo(s2) returns 0 if equal, <0 if s1 comes before s2, >0 if s1 comes after s2
    ```
* **Python:**
    ```python
    s1 = "banana"
    s2 = "Banana"
    s3 = "banana"

    print(s1 == s2) # False (case-sensitive)
    print(s1 == s3) # True
    print(s1 < s2)  # False ('b' comes after 'B' in ASCII)
    print(s1 > s2)  # True
    ```
* **C++:**
    ```cpp
    std::string s1 = "cat";
    std::string s2 = "Cat";
    std::string s3 = "cat";

    std::cout << (s1 == s2) << std::endl; // 0 (false)
    std::cout << (s1 == s3) << std::endl; // 1 (true)
    std::cout << (s1 < s2) << std::endl;  // 0 (false) - 'c' > 'C'
    ```

#### 7. Searching for Characters/Substrings

Finding the index of a character or a substring.

**Examples:**

* **Java:**
    ```java
    String text = "hello world";
    int indexO = text.indexOf('o');     // 4 (first occurrence)
    int lastIndexO = text.lastIndexOf('o'); // 7 (last occurrence)
    int indexWorld = text.indexOf("world"); // 6
    int notFound = text.indexOf("xyz");  // -1 (if not found)
    ```
* **Python:**
    ```python
    text = "apple banana"
    index_p = text.find('p')      # 1
    index_z = text.find('z')      # -1
    index_ana = text.find("ana")  # 7
    ```
* **C++:**
    ```cpp
    std::string text = "example text";
    size_t pos_e = text.find('e');       // 0
    size_t pos_t = text.find('t');       // 7 (first 't')
    size_t pos_x = text.find("xyz");     // std::string::npos (not found)
    ```

#### 8. Replacing Characters/Substrings

**Examples:**

* **Java:**
    ```java
    String original = "Java is fun";
    String replaced = original.replace('a', 'x');      // "Jxvx is fun"
    String replacedWord = original.replace("fun", "great"); // "Java is great"
    ```
* **Python:**
    ```python
    original = "hello world"
    replaced_l = original.replace('l', 'x')       # "hexxo worxd"
    replaced_word = original.replace("world", "Python") # "hello Python"
    ```
* **C++ (note: `replace` is more complex, often used with `find` first):**
    ```cpp
    std::string s = "This is a test.";
    s.replace(5, 2, "was"); // Start at index 5, replace 2 chars with "was"
    // s becomes "This was a test."
    ```

#### 9. Case Conversion

Converting to uppercase or lowercase.

**Examples:**

* **Java:**
    ```java
    String mixed = "HeLlO";
    String upper = mixed.toUpperCase(); // "HELLO"
    String lower = mixed.toLowerCase(); // "hello"
    ```
* **Python:**
    ```python
    mixed = "PyThOn"
    upper = mixed.upper() # "PYTHON"
    lower = mixed.lower() # "python"
    ```
* **C++ (requires algorithm and character manipulation):**
    ```cpp
    #include <algorithm> // For std::transform
    #include <cctype>    // For ::tolower, ::toupper

    std::string s = "CoMpUtEr";
    std::transform(s.begin(), s.end(), s.begin(), ::tolower); // "computer"
    // For uppercase, use ::toupper
    ```

### Basic Problems Solved with Strings

#### Problem 1: Check for Palindrome

**Goal:** Determine if a string reads the same forwards and backwards (e.g., "madam", "level"). Case and non-alphanumeric characters usually ignored for this problem in interviews.

```java
public boolean isPalindrome(String s) {
    // Basic version, case-sensitive, ignores spaces for simplicity
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}

// Example usage:
// isPalindrome("madam") -> true
// isPalindrome("hello") -> false
```

#### Problem 2: Reverse a String

**Goal:** Create a new string that is the reverse of the original.

```java
// Using StringBuilder in Java for efficiency (avoids creating many intermediate strings)
public String reverseString(String s) {
    StringBuilder reversed = new StringBuilder();
    for (int i = s.length() - 1; i >= 0; i--) {
        reversed.append(s.charAt(i));
    }
    return reversed.toString();
}

// In Python, this is very easy:
// "hello"[::-1] gives "olleh"

// In-place reverse for mutable character arrays (like in C/C++ or converting to char[] in Java)
public void reverseCharArray(char[] s) {
    int left = 0;
    int right = s.length - 1;
    while (left < right) {
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        left++;
        right--;
    }
}
```

#### Problem 3: Count Vowels and Consonants

**Goal:** Count the number of vowels and consonants in a string.

```java
public void countVowelsAndConsonants(String s) {
    s = s.toLowerCase(); // Convert to lowercase for easier checking
    int vowels = 0;
    int consonants = 0;

    for (int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);
        if (ch >= 'a' && ch <= 'z') { // Check if it's an alphabet
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }
    }
    System.out.println("Vowels: " + vowels + ", Consonants: " + consonants);
}
```

### Advanced String Concepts & Algorithms

Many advanced string problems involve more complex algorithms.

#### 1. Anagram Check

**Goal:** Determine if two strings are anagrams of each other (contain the same characters with the same frequencies).

**Approach 1: Sorting**
Sort both strings. If they are equal, they are anagrams.
Time Complexity: $O(N \log N)$ due to sorting.

```java
public boolean areAnagramsSort(String s1, String s2) {
    if (s1.length() != s2.length()) {
        return false;
    }
    char[] arr1 = s1.toCharArray();
    char[] arr2 = s2.toCharArray();
    java.util.Arrays.sort(arr1);
    java.util.Arrays.sort(arr2);
    return java.util.Arrays.equals(arr1, arr2);
}
```

**Approach 2: Character Counting (Frequency Array/Map)**
Use an array (e.g., `int[26]` for lowercase English letters) or a hash map to store character frequencies.
Time Complexity: $O(N)$ (linear scan). Space Complexity: $O(1)$ (fixed size array) or $O(k)$ for map where k is unique characters.

```java
public boolean areAnagramsCount(String s1, String s2) {
    if (s1.length() != s2.length()) {
        return false;
    }
    int[] charCounts = new int[26]; // For lowercase 'a' through 'z'

    for (char c : s1.toCharArray()) {
        charCounts[c - 'a']++;
    }
    for (char c : s2.toCharArray()) {
        charCounts[c - 'a']--;
    }

    for (int count : charCounts) {
        if (count != 0) {
            return false;
        }
    }
    return true;
}
```

#### 2. Pattern Searching Algorithms

Finding occurrences of a smaller string (pattern) within a larger string (text).

* **Naive/Brute-Force:** $O(M \cdot N)$ where M is pattern length, N is text length. (Simple but inefficient).
* **Rabin-Karp Algorithm:** Uses hashing to speed up comparisons. Average $O(M+N)$, worst-case $O(M \cdot N)$.
* **Knuth-Morris-Pratt (KMP) Algorithm:** Uses a precomputed "LPS array" (Longest Proper Prefix which is also Suffix) to avoid redundant comparisons. $O(M+N)$ in worst case.
* **Boyer-Moore Algorithm:** Often faster in practice, especially for long patterns, by skipping characters. $O(N/M)$ in best case, $O(M \cdot N)$ in worst case.

**Example (Conceptual - Naive Search):**

```java
public int naiveStringSearch(String text, String pattern) {
    int n = text.length();
    int m = pattern.length();

    for (int i = 0; i <= n - m; i++) {
        int j;
        for (j = 0; j < m; j++) {
            if (text.charAt(i + j) != pattern.charAt(j)) {
                break; // Mismatch
            }
        }
        if (j == m) { // Pattern found
            return i; // Return starting index
        }
    }
    return -1; // Not found
}
```

#### 3. String Manipulation with Pointers (for in-place operations)

Many string problems in C/C++ or problems that require optimizing space in Java/Python (by converting to char arrays) can leverage the two-pointers technique.

**Problem Example: Remove Duplicates from Sorted String (in-place)**

Given a sorted string (as a char array), remove duplicate characters such that each unique character appears only once. The relative order of the elements should be kept the same.

```java
public void removeDuplicates(char[] s) {
    if (s.length <= 1) return;

    int writePointer = 1; // Where the next unique char will be written
    for (int readPointer = 1; readPointer < s.length; readPointer++) {
        if (s[readPointer] != s[readPointer - 1]) { // If current char is different from previous
            s[writePointer] = s[readPointer]; // Write it to the current unique position
            writePointer++;
        }
    }
    // All characters from writePointer to end are duplicates or invalid, can nullify them if needed
    // In Java, you'd usually create a new String from s[0...writePointer-1]
    System.out.println(java.util.Arrays.copyOfRange(s, 0, writePointer)); // To see the effective string
}
// Example: removeDuplicates({'a', 'a', 'b', 'b', 'c', 'c'}) -> {'a', 'b', 'c'}
```

#### 4. Longest Common Prefix

**Goal:** Find the longest common prefix among an array of strings.

```java
public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
        return "";
    }

    String prefix = strs[0]; // Start with the first string as the initial prefix

    for (int i = 1; i < strs.length; i++) {
        // While the current string doesn't start with the prefix,
        // shorten the prefix
        while (strs[i].indexOf(prefix) != 0) {
            prefix = prefix.substring(0, prefix.length() - 1);
            if (prefix.isEmpty()) {
                return ""; // No common prefix
            }
        }
    }
    return prefix;
}
// Example: {"flower", "flow", "flight"} -> "fl"
```

#### 5. Regular Expressions (Regex)

Powerful for pattern matching, searching, and replacing complex textual patterns. While not strictly a DSA *algorithm* in itself, understanding regex is crucial for advanced string processing.

**Example (Java):**

```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

String text = "My email is example@domain.com, and another is test@email.org.";
String regex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b"; // Email pattern

Pattern pattern = Pattern.compile(regex);
Matcher matcher = pattern.matcher(text);

while (matcher.find()) {
    System.out.println("Found email: " + matcher.group());
}
// Output:
// Found email: example@domain.com
// Found email: test@email.org
```

#### 6. Dynamic Programming with Strings

Many complex string problems (e.g., Longest Common Subsequence, Edit Distance, Palindromic Substrings) can be solved efficiently using dynamic programming.

**Problem Example: Longest Common Subsequence (LCS)**

Given two strings, find the length of their longest common subsequence. A subsequence does not require contiguous characters.

**Example:** "ABCDE" and "ACE" -> LCS is "ACE", length 3.

```java
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();
    int[][] dp = new int[m + 1][n + 1]; // DP table

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1]; // Match! Extend LCS from diagonal
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // No match, take max from top or left
            }
        }
    }
    return dp[m][n];
}
// Time Complexity: O(M*N)
// Space Complexity: O(M*N)
```

### Time and Space Complexity of String Operations

| Operation            | Time Complexity (Average) | Time Complexity (Worst) | Space Complexity | Notes                                       |
| :------------------- | :------------------------ | :---------------------- | :--------------- | :------------------------------------------ |
| Access (by index)    | $O(1)$                    | $O(1)$                  | $O(1)$           | Very fast.                                  |
| Length               | $O(1)$                    | $O(1)$                  | $O(1)$           | Usually stored directly.                    |
| Concatenation        | $O(M+N)$                  | $O(M+N)$                | $O(M+N)$         | Creates new string.                            |
| Substring            | $O(L)$                    | $O(L)$                  | $O(L)$           | Where L is length of substring. Creates new. |
| Search (find/indexOf)| $O(M \cdot N)$ (Naive)    | $O(M \cdot N)$ (Naive)  | $O(1)$           | Can be optimized with KMP etc.               |
| Replace              | $O(M \cdot N)$ to $O(N)$  | $O(M \cdot N)$ to $O(N)$| $O(N)$           | Depends on implementation, creates new.      |
| Comparison           | $O(min(N, M))$            | $O(min(N, M))$          | $O(1)$           | Compares character by character.            |
| Palindrome Check     | $O(N)$                    | $O(N)$                  | $O(1)$           | Using two pointers.                         |
| Reverse String       | $O(N)$                    | $O(N)$                  | $O(N)$ (new string) / $O(1)$ (in-place) |                                             |
| Anagram Check (Sort) | $O(N \log N)$             | $O(N \log N)$           | $O(N)$           | Due to sorting.                             |
| Anagram Check (Count)| $O(N)$                    | $O(N)$                  | $O(1)$           | Fixed alphabet size.                        |

### When to Use Strings?

* When you need to represent and manipulate textual data.
* For reading from and writing to files.
* For user input and output.
* As keys in hash maps (especially in languages where strings are immutable and hashable).

### Limitations of Strings (especially Immutable Strings)

* **Immutability Overhead:** In languages like Java and Python, repeated modifications (e.g., concatenating in a loop) create many temporary string objects, leading to performance degradation and increased memory usage. Use `StringBuilder`/`StringBuffer` (Java) or `list.join()` (Python) for efficient string building.
* **Character Set/Encoding Issues:** Handling different character sets (ASCII, UTF-8, UTF-16) and encodings can be complex and lead to bugs if not managed carefully.
* **Fixed-Size Representation (C/C++):** When using `char` arrays, you must manage memory yourself, including null termination and buffer overflows.

### Conclusion

Strings are an indispensable part of programming, enabling us to work with human-readable text. Understanding their properties (especially immutability), basic operations, and common algorithmic techniques (two-pointers, dynamic programming) is vital for any aspiring DSA practitioner. Always consider the efficiency implications of string manipulations, particularly with immutable strings, and choose the right tool (e.g., `StringBuilder`) for the job. Practice, as always, is the key to mastery!
