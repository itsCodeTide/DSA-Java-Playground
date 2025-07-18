# Sorting Algorithms in DSA (with Java)

Sorting is the process of arranging elements in a particular order (typically ascending or descending). Sorting algorithms are fundamental in Data Structures and Algorithms (DSA) and are vital for optimizing data processing and searching algorithms. Below are detailed notes on the most common sorting algorithms, complete with Java-style explanations and important points for understanding and implementation.

## 1. Bubble Sort

**Bubble Sort** compares adjacent elements and swaps them if they are in the wrong order. It repeats this process for each element in the array, causing heavier (larger) elements to "bubble" to the end of the array with each complete pass.

**Time Complexity:**  
- Best: O(n)—when the array is already sorted  
- Worst/Average: O(n²)  
**Space Complexity:** O(1)  
**Stable:** Yes

**How it works:**
- For each pass, repeatedly compare adjacent items and swap if needed.
- After each pass, the largest element moves to its correct position at the end.

**Java Example:**
```java
void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i  arr[j + 1]) {
                // swap arr[j] and arr[j + 1]
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
```
Used mainly for learning or very small datasets as performance is poor on large collections.

## 2. Selection Sort

**Selection Sort** selects the minimum element from the unsorted array and swaps it with the first unsorted element.

**Time Complexity:**  
- Best, Worst, Average: O(n²)  
**Space Complexity:** O(1)  
**Stable:** No

**Java Example:**
```java
void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i = 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}
```
Efficient for small or nearly sorted arrays; commonly used in hybrid algorithms.

## 4. Merge Sort

**Merge Sort** is a divide-and-conquer algorithm:
- Divides the array into halves, recursively sorts each half, then merges the sorted halves.

**Time Complexity:**  
- Best/Worst/Average: O(n log n)  
**Space Complexity:** O(n) (requires auxiliary array)  
**Stable:** Yes

**Java Example:**
```java
void mergeSort(int[] arr, int left, int right) {
    if (left  0) arr[idx++] = i;
    }
}
```
Efficient when the range k is not significantly greater than n[3][2].

## 7. Radix Sort

**Radix Sort** sorts numbers by processing individual digits. It uses a stable sub-sorting algorithm (often counting sort).

**Time Complexity:** O(d(n + k)), d = number of digits, k = digit range  
**Space Complexity:** O(n + k)  
**Stable:** Yes

**Java Example:**
```java
void radixSort(int[] arr) {
    int max = Arrays.stream(arr).max().getAsInt();
    for (int exp = 1; max / exp > 0; exp *= 10)
        countingSortByDigit(arr, exp);
}
```
Useful for sorting large lists of numbers where the range of digit values is limited.

## 8. Heap Sort

**Heap Sort** leverages a binary heap data structure:
- Builds a max-heap, repeatedly extracts the maximum and rebuilds the heap.

**Time Complexity:** O(n log n)  
**Space Complexity:** O(1)  
**Stable:** No

**Java Example:**
```java
void heapSort(int[] arr) {
    int n = arr.length;
    // Build max heap
    for (int i = n / 2 - 1; i >= 0; i--)
        heapify(arr, n, i);
    // Extract from heap
    for (int i = n - 1; i > 0; i--) {
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;
        heapify(arr, i, 0);
    }
}
```
Popular in scenarios where memory usage must be minimized.

## Comparison Table

| Algorithm      | Best      | Average   | Worst    | Space    | Stable |
|----------------|-----------|-----------|----------|----------|--------|
| Bubble Sort    | O(n)      | O(n²)     | O(n²)    | O(1)     | Yes    |
| Selection Sort | O(n²)     | O(n²)     | O(n²)    | O(1)     | No     |
| Insertion Sort | O(n)      | O(n²)     | O(n²)    | O(1)     | Yes    |
| Merge Sort     | O(n log n)| O(n log n)| O(n log n)| O(n)    | Yes    |
| Quick Sort     | O(n log n)| O(n log n)| O(n²)    | O(log n) | No     |
| Counting Sort  | O(n+k)    | O(n+k)    | O(n+k)   | O(k)     | Yes    |
| Radix Sort     | O(n+k)    | O(n+k)    | O(n+k)   | O(n+k)   | Yes    |
| Heap Sort      | O(n log n)| O(n log n)| O(n log n)| O(1)    | No     |


**Pro Tips**:
- Use simpler algorithms (insertion, selection, bubble) for small or nearly sorted data.
- Use merge, quick, or heap sort for large or general datasets.
- Counting, radix, and bucket sort are ideal for large datasets with known, constrained ranges.

Sorting underpins many complex algorithms. Understanding each type’s performance and implementation—and when to use which—is essential in DSA and technical interviews.

[1] https://en.wikipedia.org/wiki/Sorting_algorithm
[2] https://www.youtube.com/watch?v=-vdAndp_NtE
[3] https://www.programiz.com/dsa/sorting-algorithm
[4] https://www.youtube.com/watch?v=rBDlM1QPOpE
[5] https://www.w3schools.com/dsa/dsa_algo_bubblesort.php
[6] https://github.com/topics/sorting-algorithm
[7] https://www.geeksforgeeks.org/dsa/sorting-algorithms/
[8] https://github.com/Deeksha2501/Data-Structures-and-Algorithms-Notes
[9] https://www.tutorialspoint.com/data_structures_algorithms/sorting_algorithms.htm
[10] https://github.com/mandartule/Apna-College-AlphaBatch-DSA-JAVA
