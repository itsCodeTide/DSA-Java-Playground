# 🔍 Searching in Java — A Beginner-Friendly Guide

Welcome! 🙌
In this section, we’ll learn about **Searching** in Java — what it is, why it's important, and how to do it using different techniques. We’ll also look at examples to make everything easier to understand.

---

## 📌 What is Searching?

Searching means **finding a specific element in a collection of data** — like an array or list.

For example, if you have a list of numbers:

```java
int[] numbers = {3, 6, 9, 12, 15};
```

And you want to check if `9` exists in that list — that’s searching!

---

## 🛠️ Types of Searching in Java

There are mainly **two basic types of searching algorithms** that we often use in Java:

1. **Linear Search** (Brute force way)
2. **Binary Search** (Efficient way, but only works on sorted data)

Let’s learn each one with simple code examples.

---

## 1️⃣ Linear Search

**Idea:**
Check every element one by one until you find the target.

### 🔁 Algorithm:

* Start from the first element.
* Compare each element with the value you're searching for.
* If you find it, return the index.
* If you reach the end without finding it, return -1 (not found).

### ✅ Example:

```java
public class LinearSearch {
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Found at index i
            }
        }
        return -1; // Not found
    }

    public static void main(String[] args) {
        int[] data = {5, 10, 15, 20, 25};
        int target = 15;
        int result = linearSearch(data, target);

        if (result != -1)
            System.out.println("Element found at index: " + result);
        else
            System.out.println("Element not found.");
    }
}
```

### 📝 Output:

```
Element found at index: 2
```

### ⚡ Time Complexity:

* Worst case: **O(n)**
  (You might have to look at all elements.)

---

## 2️⃣ Binary Search

**Idea:**
Divide and conquer. If the array is sorted, check the middle element and decide whether to search left or right.

### 📋 Requirements:

* The array **must be sorted** (in ascending order).

### 🔁 Algorithm:

1. Find the middle of the array.
2. If the middle element is the target → done!
3. If target < middle → search in the left half.
4. If target > middle → search in the right half.
5. Repeat until found or bounds cross.

### ✅ Example:

```java
import java.util.Arrays;

public class BinarySearchExample {
    public static int binarySearch(int[] arr, int target) {
        int start = 0, end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target)
                return mid;
            else if (arr[mid] < target)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return -1; // Not found
    }

    public static void main(String[] args) {
        int[] data = {10, 20, 30, 40, 50};
        int target = 30;

        int result = binarySearch(data, target);

        if (result != -1)
            System.out.println("Element found at index: " + result);
        else
            System.out.println("Element not found.");
    }
}
```

### 📝 Output:

```
Element found at index: 2
```

### ⚡ Time Complexity:

* Worst case: **O(log n)**
  (Very fast for large data sets!)

---

## 🔄 Bonus: Built-in Binary Search in Java

Java provides a built-in method in the `Arrays` class:

```java
import java.util.Arrays;

public class BuiltInBinarySearch {
    public static void main(String[] args) {
        int[] arr = {5, 10, 15, 20, 25};
        int index = Arrays.binarySearch(arr, 15);

        if (index >= 0)
            System.out.println("Element found at index: " + index);
        else
            System.out.println("Element not found.");
    }
}
```

---

## 🧠 When to Use What?

| Algorithm     | Use When                      | Time Complexity |
| ------------- | ----------------------------- | --------------- |
| Linear Search | Data is **unsorted** or small | O(n)            |
| Binary Search | Data is **sorted**            | O(log n)        |

---

## 📚 Summary

* Searching means finding a value in a list or array.
* **Linear Search**: Works on any array but slower for large data.
* **Binary Search**: Much faster, but array must be **sorted**.
* Java has a built-in binary search in `Arrays.binarySearch()`.
