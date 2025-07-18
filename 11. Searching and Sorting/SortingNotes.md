# Sorting Algorithms in DSA (with Java)

Sorting is the process of arranging elements in a particular order (typically ascending or descending). Sorting algorithms are fundamental in Data Structures and Algorithms (DSA) and are vital for optimizing data processing and searching algorithms. Below are detailed notes on the most common sorting algorithms, complete with Java-style explanations and important points for understanding and implementation.

Here’s a comprehensive overview of **all major sorting techniques**, their fundamentals, algorithm flow, and where each excels or falls short. Where relevant, example logic is explained in Java-style pseudocode.

## 1. Bubble Sort

**Concept:**  
Repeatedly compares adjacent elements, swapping them if out of order, until the array is sorted.

**Steps:**
- Start from the first element.
- Compare the current element to the next.
- If out of order, swap them.
- Repeat for every element; after each pass, the largest element “bubbles up” to the end.
- Continue until a full pass occurs without swaps.

**Use case:** Small or nearly sorted arrays.

**Complexity:**  
- Best: O(n)  
- Worst/Average: O(n²)  
- Space: O(1)  
- Stable: Yes

## 2. Selection Sort

**Concept:**  
Finds the minimum (or maximum) in the unsorted portion and places it at the beginning, then repeats for the rest.

**Steps:**
- Start with the first index.
- Find the smallest element from the unsorted part.
- Swap it with the element at current index.
- Move to the next index and repeat.

**Use case:** Suitable for small datasets.

**Complexity:**  
- Best/Average/Worst: O(n²)  
- Space: O(1)  
- Stable: No

## 3. Insertion Sort

**Concept:**  
Builds the sorted array one element at a time by inserting each item into its rightful place.

**Steps:**
- Consider the first element as sorted.
- Pick the next element.
- Shift elements larger than it to the right.
- Insert the picked element at the correct position.
- Repeat until the end.

**Use case:** Small or almost sorted collections.

**Complexity:**  
- Best: O(n)  
- Worst/Average: O(n²)  
- Space: O(1)  
- Stable: Yes

## 4. Merge Sort

**Concept:**  
Divide-and-conquer. Divides the array, sorts subarrays, then merges them in order.

**Steps:**
- Divide the array into two halves.
- Recursively sort both halves.
- Merge the two sorted halves.

**Use case:** Large datasets, linked lists, external sorting.

**Complexity:**  
- Best/Average/Worst: O(n log n)  
- Space: O(n)  
- Stable: Yes

## 5. Quick Sort

**Concept:**  
Divide-and-conquer. Select a 'pivot', partition the array into elements less and greater than the pivot, then recursively sort them.

**Steps:**
- Pick a pivot element.
- Partition: place elements less than pivot to its left; greater to its right.
- Recursively sort left and right subarrays.

**Use case:** General purpose, large datasets.

**Complexity:**  
- Best/Average: O(n log n)  
- Worst: O(n²) (rare, bad pivots)  
- Space: O(log n)  
- Stable: No

## 6. Heap Sort

**Concept:**  
Converts the array to a heap structure. Removes the largest (root) and places it at the end, shrinking the heap and repeating.

**Steps:**
- Build a max heap.
- Swap first element (max) with last unsorted.
- Reduce heap size.
- Heapify the root.
- Repeat until sorted.

**Use case:** When O(n log n) time and O(1) space is needed, but stable sort isn’t required.

**Complexity:**  
- Best/Average/Worst: O(n log n)  
- Space: O(1)  
- Stable: No

## 7. Counting Sort

**Concept:**  
Non-comparison. Counts the occurrences of each unique value. Calculates positions and fills a new array.

**Steps:**
- Find the range of input.
- Count occurrences of each value.
- Modify counts to reflect positions.
- Place values into output array.

**Use case:** Small integer keys, large datasets, not for negative numbers or floating points.

**Complexity:**  
- Best/Average/Worst: O(n + k) (where k = range of values)  
- Space: O(k)  
- Stable: Yes

## 8. Radix Sort

**Concept:**  
Non-comparison. Sorts by digits/characters, from least or most significant, using a stable sort (often Counting Sort) at each digit/character position.

**Steps:**
- Find longest length (digits or characters).
- Sort array by each digit/character, starting with least significant.
- Combines passes to fully sort.

**Use case:** Large lists of integers, strings, IDs.

**Complexity:**  
- Best/Average/Worst: O(d(n + k)), d = number of digits  
- Space: O(n + k)  
- Stable: Yes

## 9. Bucket Sort

**Concept:**  
Distributes elements into several buckets, sorts each bucket (using another algorithm or recursively), then concatenates all buckets.

**Steps:**
- Divide the interval into buckets.
- Put elements into corresponding buckets.
- Sort individual buckets.
- Concatenate sorted buckets.

**Use case:** Uniformly distributed numbers, floating points.

**Complexity:**  
- Average: O(n + k)  
- Worst: O(n²) (if elements cluster in a bucket)  
- Space: O(n + k)  
- Stable: Yes (if stable algorithm used inside bucket)

## 10. Shell Sort

**Concept:**  
Generalization of insertion sort: compares elements far apart and reduces the gap gradually.

**Steps:**
- Pick a gap sequence (e.g., n/2, n/4, ..., 1).
- For each gap, insertion sort the sublists formed by elements 'gap' distance apart.
- Repeat until gap is 1.

**Use case:** Medium-size arrays, when space is at premium.

**Complexity:**  
- Best: O(n log n)  
- Average/Worst: Up to O(n²), depends on gap sequence  
- Space: O(1)  
- Stable: No

## 11. Cycle Sort

**Concept:**  
Minimizes the number of writes while rearranging elements into sorted order. Each element is moved to its correct position in a cycle.

**Use case:** Scenarios where write operations are costly.

**Complexity:**  
- O(n²)

## 12. Comb Sort

**Concept:**  
Improves on bubble sort by comparing elements with larger gaps and shrinking the gap over time.

**Use case:** Improvements over bubble for simple cases.

**Complexity:**  
- Average: O(n²)

## 13. Exchange Sort

**Concept:**  
Compares each element with all following elements and swaps if out of order.

**Complexity:**  
- Worst: O(n²)

## 14. Recombinant Sort

**Concept:**  
Hybrid, uses ideas from bucket, counting, radix sort, hashing, and dynamic programming. Maps elements in n-dimensional space, hashes, and extracts in order.

**Use case:** Experimental, efficient for complex/mixed data.

**Complexity:**  
- Best/Average/Worst: O(n)

## Key Properties to Consider

- **Stability:** Whether equal elements retain their relative order.
- **In-place:** If sorting uses only a constant amount of extra space beyond the input array.
- **Adaptivity:** If it runs faster for partially sorted data.

## Summary Table

| Name           | Best        | Average   | Worst     | Space   | Stable | In-place | Principle             |
|----------------|-------------|-----------|-----------|---------|--------|----------|-----------------------|
| Bubble Sort    | O(n)        | O(n²)     | O(n²)     | O(1)    | Yes    | Yes      | Comparison            |
| Selection Sort | O(n²)       | O(n²)     | O(n²)     | O(1)    | No     | Yes      | Comparison            |
| Insertion Sort | O(n)        | O(n²)     | O(n²)     | O(1)    | Yes    | Yes      | Comparison            |
| Merge Sort     | O(n log n)  | O(n log n)| O(n log n)| O(n)    | Yes    | No       | Divide & Conquer      |
| Quick Sort     | O(n log n)  | O(n log n)| O(n²)     | O(log n)| No     | Yes      | Divide & Conquer      |
| Heap Sort      | O(n log n)  | O(n log n)| O(n log n)| O(1)    | No     | Yes      | Heap                  |
| Counting Sort  | O(n + k)    | O(n + k)  | O(n + k)  | O(k)    | Yes    | No       | Counting, Non-comp.   |
| Radix Sort     | O(d(n + k)) | O(d(n+k)) | O(d(n+k)) | O(n+k)  | Yes    | No       | Radix, Non-comp.      |
| Bucket Sort    | O(n + k)    | O(n+k)    | O(n²)     | O(n+k)  | Yes    | No       | Bucketing, Non-comp.  |
| Shell Sort     | O(n log n)  | O(n^1.5)  | O(n²)     | O(1)    | No     | Yes      | Gap Sequences         |
| Cycle Sort     | O(n²)       | O(n²)     | O(n²)     | O(1)    | No     | Yes      | Cycle placement       |
| Comb Sort      | O(n log n)  | O(n²)     | O(n²)     | O(1)    | No     | Yes      | Gap Swapping          |
| Exchange Sort  | O(n²)       | O(n²)     | O(n²)     | O(1)    | No     | Yes      | Direct Comparison     |
| Recombinant    | O(n)        | O(n)      | O(n)      | Varies  | Yes    | No       | Hybrid, Hashing       |

[1] https://www.shiksha.com/online-courses/articles/types-of-sorting-algorithm-blogId-148505
[2] https://en.wikipedia.org/wiki/Sorting_algorithm
[3] https://byjus.com/gate/sorting-algorithms-notes/
[4] https://www.freecodecamp.org/news/sorting-algorithms-explained-with-examples-in-python-java-and-c/
[5] https://www.programiz.com/dsa/sorting-algorithm
[6] https://www.geeksforgeeks.org/dsa/sorting-algorithms/
[7] https://visualgo.net/en/sorting
[8] https://testbook.com/gate/sorting-algorithms-notes
[9] https://www.youtube.com/watch?v=RfXt_qHDEPw
[10] https://www.geeksforgeeks.org/sorting-algorithms/
