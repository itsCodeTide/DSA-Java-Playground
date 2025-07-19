# Pattern Searching Algorithms - Complete Java DSA Notes

## Understanding the Foundation: What is Pattern Searching?

Pattern searching is the fundamental process of finding occurrences of a specific sequence of characters (the pattern) within a larger text string. Think of it like being a detective searching through a massive book to find every occurrence of a particular phrase. This seemingly simple task becomes remarkably complex when you consider the scale at which modern applications operate.

Imagine you're building a search engine that needs to find the word "algorithm" in billions of web pages, or a text editor that highlights all instances of a user's search term in a document. The naive approach of checking every possible position would be painfully slow. This is why computer scientists have developed increasingly sophisticated algorithms that can find patterns with remarkable efficiency.

**The core insight:** The key to efficient pattern searching lies in avoiding unnecessary comparisons. When we find a mismatch, we want to skip ahead as much as possible rather than moving just one character forward. Different algorithms achieve this through different strategies, each with its own strengths and ideal use cases.

## The Naive Approach: Understanding the Baseline

Before we explore sophisticated algorithms, let's understand the straightforward approach that naturally comes to mind. This will help us appreciate why the advanced algorithms are necessary and how they improve upon this basic method.

```java
public class NaivePatternSearch {
    /**
     * Searches for all occurrences of pattern in text using naive approach
     * Time Complexity: O(n*m) where n = text length, m = pattern length
     * Space Complexity: O(1)
     */
    public static void naiveSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        
        // Try every possible starting position in the text
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            
            // Check if pattern matches starting at position i
            while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            
            // If we matched the entire pattern
            if (j == m) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }
    
    public static void main(String[] args) {
        String text = "AABAACAADAABAABA";
        String pattern = "AABA";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Searching using naive approach:");
        naiveSearch(text, pattern);
    }
}
```

**Walking through the logic:** The naive approach tries every possible position where the pattern could start. At each position, it compares characters one by one until either the entire pattern matches or a mismatch is found. When a mismatch occurs, it simply moves to the next starting position and begins the comparison process again.

**Understanding the inefficiency:** Consider searching for "AABA" in "AABAACAADAABAABA". When we start at position 0, we successfully match "AAB" but fail at the fourth character. The naive approach then starts over at position 1, even though we've already learned valuable information about the text that we're completely ignoring.

**Why we need better approaches:** In the worst case, this algorithm performs O(n*m) character comparisons. For large texts or patterns, this becomes prohibitively slow. More importantly, it doesn't learn from previous comparisons, repeating work that could be avoided.

## KMP Algorithm: Learning from Mismatches

The Knuth-Morris-Pratt (KMP) algorithm represents a breakthrough in pattern searching because it never moves backward in the text. Instead, it preprocesses the pattern to create a "failure function" that tells us how to skip ahead when mismatches occur.

**The fundamental insight:** When we encounter a mismatch, we don't need to start completely over. If we've already matched part of the pattern, we can often skip ahead based on the structure of the pattern itself. The key is recognizing when a prefix of the pattern matches a suffix of what we've already processed.

```java
public class KMPPatternSearch {
    /**
     * Builds the failure function (also called LPS array - Longest Proper Prefix which is also Suffix)
     * This array tells us how many characters we can skip when a mismatch occurs
     */
    private static int[] buildFailureFunction(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m]; // lps[i] stores length of longest proper prefix of pattern[0..i] which is also suffix
        int len = 0; // length of previous longest prefix suffix
        int i = 1;
        
        lps[0] = 0; // first character has no proper prefix
        
        // Build the failure function for pattern[1..m-1]
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                // We found a match, so the LPS value is len + 1
                len++;
                lps[i] = len;
                i++;
            } else {
                // Mismatch occurred
                if (len != 0) {
                    // Try the next best option using previously computed LPS values
                    len = lps[len - 1];
                    // Note: we don't increment i here
                } else {
                    // No match possible at this position
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
    
    /**
     * KMP pattern searching algorithm
     * Time Complexity: O(n + m) where n = text length, m = pattern length
     * Space Complexity: O(m) for the failure function array
     */
    public static void kmpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        
        // Build the failure function
        int[] lps = buildFailureFunction(pattern);
        
        System.out.println("Failure function for pattern '" + pattern + "':");
        for (int i = 0; i < lps.length; i++) {
            System.out.println("lps[" + i + "] = " + lps[i]);
        }
        System.out.println();
        
        int i = 0; // index for text
        int j = 0; // index for pattern
        
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                // Characters match, move both pointers
                i++;
                j++;
            }
            
            if (j == m) {
                // We found a complete match
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1]; // Get the next position to check using failure function
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                // Mismatch occurred
                if (j != 0) {
                    // Use failure function to skip characters
                    j = lps[j - 1];
                } else {
                    // No partial match, move to next character in text
                    i++;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABCABCABCABC";
        String pattern = "ABABCABCAB";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("\nKMP Search Results:");
        kmpSearch(text, pattern);
    }
}
```

**Understanding the failure function:** The failure function (LPS array) is the heart of KMP. For each position in the pattern, it stores the length of the longest proper prefix that is also a suffix. This information tells us how far we can "slide" the pattern when a mismatch occurs, avoiding redundant comparisons.

**Mental model for the failure function:** Think of it as asking "If I fail to match at this position, what's the furthest back I need to go in the pattern to potentially find a match?" The answer depends on the internal structure of the pattern itself.

**Why KMP is efficient:** By never moving backward in the text and using the failure function to skip ahead optimally, KMP achieves O(n + m) time complexity. This is a dramatic improvement over the naive approach's O(n*m) in the worst case.

## Rabin-Karp Algorithm: The Power of Hashing

The Rabin-Karp algorithm introduces a completely different approach to pattern searching by using rolling hash functions. Instead of comparing characters directly, it compares hash values of substrings, making the initial comparison very fast.

**The core concept:** If two strings are equal, their hash values must be equal. While the converse isn't necessarily true (hash collisions can occur), we can use hashing to quickly eliminate most non-matching positions and only perform character-by-character comparison when hash values match.

```java
public class RabinKarpSearch {
    private static final int PRIME = 101; // A prime number for hash calculation
    
    /**
     * Calculates hash value for a string using polynomial rolling hash
     * hash = (s[0]*base^(m-1) + s[1]*base^(m-2) + ... + s[m-1]*base^0) mod prime
     */
    private static long calculateHash(String str, int length) {
        long hash = 0;
        long pow = 1;
        
        for (int i = length - 1; i >= 0; i--) {
            hash = (hash + (str.charAt(i) - 'a' + 1) * pow) % PRIME;
            if (i > 0) {
                pow = (pow * 256) % PRIME; // 256 is our base (assuming extended ASCII)
            }
        }
        return hash;
    }
    
    /**
     * Recalculates hash when sliding the window by one position
     * This is the "rolling" part of the rolling hash
     */
    private static long recalculateHash(String str, int oldIndex, int newIndex, 
                                      long oldHash, int patternLength) {
        long newHash = oldHash;
        
        // Remove the contribution of the first character
        long oldCharContribution = ((str.charAt(oldIndex) - 'a' + 1) * 
                                   power(256, patternLength - 1)) % PRIME;
        newHash = (newHash - oldCharContribution + PRIME) % PRIME;
        
        // Multiply by base to shift existing characters
        newHash = (newHash * 256) % PRIME;
        
        // Add the contribution of the new character
        newHash = (newHash + (str.charAt(newIndex) - 'a' + 1)) % PRIME;
        
        return newHash;
    }
    
    private static long power(int base, int exponent) {
        long result = 1;
        for (int i = 0; i < exponent; i++) {
            result = (result * base) % PRIME;
        }
        return result;
    }
    
    /**
     * Checks if two strings match character by character
     * This is needed because hash collisions are possible
     */
    private static boolean checkEqual(String text, int start, String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            if (text.charAt(start + i) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Rabin-Karp pattern searching algorithm
     * Average Time Complexity: O(n + m)
     * Worst Case Time Complexity: O(n*m) when many hash collisions occur
     * Space Complexity: O(1)
     */
    public static void rabinKarpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        
        if (m > n) return;
        
        // Calculate hash for pattern and first window of text
        long patternHash = calculateHash(pattern, m);
        long textHash = calculateHash(text, m);
        
        System.out.println("Pattern hash: " + patternHash);
        System.out.println("Initial text window hash: " + textHash);
        System.out.println();
        
        // Check first window
        if (patternHash == textHash && checkEqual(text, 0, pattern)) {
            System.out.println("Pattern found at index 0");
        }
        
        // Roll the hash over the text
        for (int i = 1; i <= n - m; i++) {
            // Calculate hash for current window
            textHash = recalculateHash(text, i - 1, i + m - 1, textHash, m);
            
            System.out.println("Window [" + i + ", " + (i + m - 1) + "] hash: " + textHash);
            
            // If hashes match, check character by character
            if (patternHash == textHash) {
                if (checkEqual(text, i, pattern)) {
                    System.out.println("Pattern found at index " + i);
                } else {
                    System.out.println("Hash collision at index " + i + " (false positive)");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        String text = "abcdefghijklmnopqrstuvwxyz";
        String pattern = "mno";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("\nRabin-Karp Search Results:");
        rabinKarpSearch(text, pattern);
    }
}
```

**Understanding rolling hash:** The brilliant insight of Rabin-Karp is the rolling hash technique. When we slide our window by one position, instead of recalculating the entire hash from scratch, we update it incrementally by removing the contribution of the character that's leaving the window and adding the contribution of the character that's entering.

**Dealing with hash collisions:** Since hash functions can produce the same value for different strings, we must verify matches with character-by-character comparison. In practice, with a good hash function and large prime modulus, collisions are rare, making the algorithm very efficient on average.

**When Rabin-Karp excels:** This algorithm is particularly useful when searching for multiple patterns simultaneously (you can compare against multiple pattern hashes) or when the alphabet size is large (making character comparisons expensive).

## Boyer-Moore Algorithm: Searching Backwards

The Boyer-Moore algorithm introduces the counterintuitive approach of comparing the pattern with the text from right to left. This backward scanning, combined with two powerful heuristics, often allows the algorithm to skip large portions of the text.

```java
public class BoyerMooreSearch {
    private static final int ALPHABET_SIZE = 256; // Extended ASCII
    
    /**
     * Builds the bad character heuristic table
     * For each character, stores the rightmost occurrence in the pattern
     */
    private static int[] buildBadCharTable(String pattern) {
        int m = pattern.length();
        int[] badChar = new int[ALPHABET_SIZE];
        
        // Initialize all characters as not present in pattern
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            badChar[i] = -1;
        }
        
        // Store the rightmost occurrence of each character in pattern
        for (int i = 0; i < m; i++) {
            badChar[pattern.charAt(i)] = i;
        }
        
        return badChar;
    }
    
    /**
     * Builds the good suffix heuristic table
     * This is more complex but provides better skip distances in many cases
     */
    private static int[] buildGoodSuffixTable(String pattern) {
        int m = pattern.length();
        int[] shift = new int[m];
        int[] borderPos = new int[m + 1];
        
        // Initialize
        for (int i = 0; i < m; i++) {
            shift[i] = m;
        }
        
        // Preprocessing for case 2 of good suffix rule
        int i = m, j = m + 1;
        borderPos[i] = j;
        
        while (i > 0) {
            while (j <= m && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (shift[j - 1] == m) {
                    shift[j - 1] = j - i;
                }
                j = borderPos[j];
            }
            i--;
            j--;
            borderPos[i] = j;
        }
        
        // Preprocessing for case 1 of good suffix rule
        j = borderPos[0];
        for (i = 0; i <= m; i++) {
            if (shift[i] == m) {
                shift[i] = j;
            }
            if (i == j) {
                j = borderPos[j];
            }
        }
        
        return shift;
    }
    
    /**
     * Boyer-Moore pattern searching algorithm
     * Best Case Time Complexity: O(n/m) - can skip most characters
     * Average Case Time Complexity: O(n)
     * Worst Case Time Complexity: O(n*m) - rare in practice
     * Space Complexity: O(m + σ) where σ is alphabet size
     */
    public static void boyerMooreSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        
        if (m > n) return;
        
        // Build heuristic tables
        int[] badChar = buildBadCharTable(pattern);
        int[] goodSuffix = buildGoodSuffixTable(pattern);
        
        System.out.println("Bad character table built");
        System.out.println("Good suffix table built");
        System.out.println();
        
        int shift = 0; // shift of the pattern with respect to text
        
        while (shift <= n - m) {
            int j = m - 1; // start matching from rightmost character of pattern
            
            System.out.println("Checking alignment at position " + shift);
            System.out.println("Text window: " + text.substring(shift, shift + m));
            System.out.println("Pattern:     " + pattern);
            
            // Keep matching characters from right to left
            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
                j--;
            }
            
            if (j < 0) {
                // Pattern found
                System.out.println("*** PATTERN FOUND at index " + shift + " ***");
                
                // Shift based on good suffix heuristic
                shift += goodSuffix[0];
            } else {
                // Mismatch occurred, calculate shift using both heuristics
                char mismatchChar = text.charAt(shift + j);
                
                // Bad character heuristic
                int badCharShift = Math.max(1, j - badChar[mismatchChar]);
                
                // Good suffix heuristic
                int goodSuffixShift = goodSuffix[j + 1];
                
                // Use the maximum of both heuristics
                int actualShift = Math.max(badCharShift, goodSuffixShift);
                
                System.out.println("Mismatch at pattern[" + j + "] = '" + pattern.charAt(j) + 
                                 "' vs text[" + (shift + j) + "] = '" + mismatchChar + "'");
                System.out.println("Bad char shift: " + badCharShift + 
                                 ", Good suffix shift: " + goodSuffixShift);
                System.out.println("Using shift: " + actualShift);
                System.out.println();
                
                shift += actualShift;
            }
        }
    }
    
    public static void main(String[] args) {
        String text = "ABAAABCDABCDABDE";
        String pattern = "ABCD";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("\nBoyer-Moore Search Results:");
        boyerMooreSearch(text, pattern);
    }
}
```

**Understanding the bad character heuristic:** When we encounter a mismatch, we look at the mismatched character in the text. If this character doesn't appear in the pattern at all, we can skip past it entirely. If it does appear in the pattern, we can align the pattern so that the rightmost occurrence of this character in the pattern aligns with the mismatched character in the text.

**Understanding the good suffix heuristic:** This is more sophisticated. When we have a partial match followed by a mismatch, we look at the matched suffix. We then try to find this same suffix elsewhere in the pattern (not overlapping with its current position) or find the longest prefix of the pattern that matches a suffix of the matched part.

**Why Boyer-Moore can be super-fast:** In the best case, Boyer-Moore can achieve O(n/m) time complexity. This happens when the pattern is not found in the text and we can skip m characters at each step. For example, searching for "ZZZZZ" in a text that doesn't contain 'Z' would skip 5 characters at each comparison.

## Z-Algorithm: Preprocessing the Text

The Z-algorithm provides a different perspective on string processing by computing, for each position in a string, the length of the longest substring starting from that position that matches a prefix of the string.

```java
public class ZAlgorithmSearch {
    /**
     * Computes Z array for given string
     * Z[i] = length of longest substring starting from i which is also prefix of string
     */
    private static int[] computeZArray(String str) {
        int n = str.length();
        int[] z = new int[n];
        
        // Z[0] is not defined (or can be considered as n)
        z[0] = 0;
        
        int left = 0, right = 0; // boundaries of current Z-box
        
        for (int i = 1; i < n; i++) {
            if (i <= right) {
                // We're inside a Z-box, so we can use previously computed information
                int k = i - left; // corresponding position in the prefix
                z[i] = Math.min(right - i + 1, z[k]);
            }
            
            // Extend the match as far as possible
            while (i + z[i] < n && str.charAt(z[i]) == str.charAt(i + z[i])) {
                z[i]++;
            }
            
            // If we extended past the current Z-box, update it
            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }
        
        return z;
    }
    
    /**
     * Pattern searching using Z-algorithm
     * Time Complexity: O(n + m)
     * Space Complexity: O(n + m)
     */
    public static void zAlgorithmSearch(String text, String pattern) {
        // Create concatenated string: pattern + "$" + text
        // We use '$' as a separator that doesn't appear in text or pattern
        String combined = pattern + "$" + text;
        int combinedLength = combined.length();
        int patternLength = pattern.length();
        
        // Compute Z array for the combined string
        int[] zArray = computeZArray(combined);
        
        System.out.println("Combined string: " + combined);
        System.out.println("Z array:");
        for (int i = 0; i < Math.min(zArray.length, 30); i++) { // Show first 30 for readability
            System.out.print(zArray[i] + " ");
            if ((i + 1) % 10 == 0) System.out.println();
        }
        System.out.println("\n");
        
        // Look for occurrences of pattern in text
        for (int i = patternLength + 1; i < combinedLength; i++) {
            if (zArray[i] == patternLength) {
                // Found a match at position (i - patternLength - 1) in original text
                int textIndex = i - patternLength - 1;
                System.out.println("Pattern found at index " + textIndex + " in text");
            }
        }
    }
    
    /**
     * Demonstrates the Z-array computation step by step
     */
    public static void demonstrateZArray(String str) {
        System.out.println("Demonstrating Z-array computation for: " + str);
        int n = str.length();
        int[] z = new int[n];
        z[0] = 0;
        
        int left = 0, right = 0;
        
        for (int i = 1; i < n; i++) {
            System.out.println("\nProcessing position " + i + ":");
            System.out.println("Current Z-box: [" + left + ", " + right + "]");
            
            if (i <= right) {
                int k = i - left;
                z[i] = Math.min(right - i + 1, z[k]);
                System.out.println("Inside Z-box, using Z[" + k + "] = " + z[k]);
                System.out.println("Initial Z[" + i + "] = " + z[i]);
            } else {
                System.out.println("Outside Z-box, starting fresh");
                z[i] = 0;
            }
            
            // Extend the match
            int extensions = 0;
            while (i + z[i] < n && str.charAt(z[i]) == str.charAt(i + z[i])) {
                z[i]++;
                extensions++;
            }
            
            if (extensions > 0) {
                System.out.println("Extended by " + extensions + " characters");
            }
            System.out.println("Final Z[" + i + "] = " + z[i]);
            
            // Update Z-box if necessary
            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
                System.out.println("Updated Z-box to [" + left + ", " + right + "]");
            }
        }
        
        System.out.println("\nFinal Z-array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(z[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // First demonstrate Z-array computation
        System.out.println("=== Z-Array Demonstration ===");
        demonstrateZArray("aabcaabxaaz");
        
        System.out.println("\n=== Pattern Searching with Z-Algorithm ===");
        String text = "ABABDABACDABABCABCABCABCABC";
        String pattern = "ABC";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println();
        
        zAlgorithmSearch(text, pattern);
    }
}
```

**Understanding the Z-array concept:** The Z-array is a powerful preprocessing tool. For a string, Z[i] tells us the length of the longest substring starting at position i that matches a prefix of the entire string. This information captures important structural properties of the string.

**The Z-box optimization:** The algorithm maintains a "Z-box" representing the rightmost segment that matches a prefix. When processing a new position, if it falls within the current Z-box, we can use previously computed information to avoid redundant comparisons.

**Pattern searching application:** To search for a pattern in text, we create a combined string "pattern$text" and compute its Z-array. Whenever Z[i] equals the pattern length, we've found an occurrence of the pattern in the text.

## Comparing Algorithms: When to Use Which

Understanding when to use each algorithm is crucial for making informed decisions in real-world applications. Each algorithm has its strengths and ideal use cases.

```java
public class PatternSearchComparison {
    /**
     * Utility class to measure and compare algorithm performance
     */
    static class PerformanceTester {
        private long startTime;
        
        public void start() {
            startTime = System.nanoTime();
        }
        
        public long stop() {
            return System.nanoTime() - startTime;
        }
        
        public static String formatTime(long nanoseconds) {
            if (nanoseconds < 1000) return nanoseconds + " ns";
            if (nanoseconds < 1000000) return (nanoseconds / 1000.0) + " μs";
            return (nanoseconds / 1000000.0) + " ms";
        }
    }
    
    /**
     * Comprehensive comparison of different pattern searching algorithms
     */
    public static void compareAlgorithms(String text, String pattern) {
        System.out.println("=== Algorithm Comparison ===");
        System.out.println("Text length: " + text.length());
        System.out.println("Pattern length: " + pattern.length());
        System.out.println("Pattern: " + pattern);
        System.out.println();
        
        PerformanceTester timer = new PerformanceTester();
        
        // Test Naive Algorithm
        System.out.println("1. Naive Algorithm:");
        System.out.println("   Best for: Small texts, simple implementation");
        System.out.println("   Time Complexity: O(n*m)");
        timer.start();
        int naiveCount = countNaiveMatches(text, pattern);
        long naiveTime = timer.stop();
        System.out.println("   Matches found: " + naiveCount);
        System.out.println("   Time taken: " + PerformanceTester.formatTime(naiveTime));
        System.out.println();
        
        // Test KMP Algorithm
        System.out.println("2. KMP Algorithm:");
        System.out.println("   Best for: Long patterns, guaranteed O(n+m) performance");
        System.out.println("   Time Complexity: O(n+m)");
        timer.start();
        int kmpCount = countKMPMatches(text, pattern);
        long kmpTime = timer.stop();
        System.out.println("   Matches found: " + kmpCount);
        System.out.println("   Time taken: " + PerformanceTester.formatTime(kmpTime));
        System.out.println();
        
        // Test Boyer-Moore Algorithm
        System.out.println("3. Boyer-Moore Algorithm:");
        System.out.println("   Best for: Large alphabets, long patterns, text searching");
        System.out.println("   Time Complexity: O(n/m) best case, O(n*m) worst case");
        timer.start();
        int bmCount = countBoyerMooreMatches(text, pattern);
        long bmTime = timer.stop();
        System.out.println("   Matches found: " + bmCount);
        System.out.println("   Time taken: " + PerformanceTester.formatTime(bmTime));
        System.out.println();
        
        // Performance summary
        System.out.println("=== Performance Summary ===");
        System.out.println("Fastest algorithm: " + getFastest(naiveTime, kmpTime, bmTime));
        System.out.println("All algorithms found the same number of matches: " + 
                          (naiveCount == kmpCount && kmpCount == bmCount));
    }
    
    private static String getFastest(long naive, long kmp, long bm) {
        if (naive <= kmp && naive <= bm) return "Naive";
        if (kmp <= bm) return "KMP";
        return "Boyer-Moore";
    }
    
    // Simplified counting versions of the algorithms for comparison
    private static int countNaiveMatches(String text, String pattern) {
        int count = 0;
        int n = text.length();
        int m = pattern.length();
