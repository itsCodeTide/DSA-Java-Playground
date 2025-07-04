// File: RecursionInDSA.java
// Recursion in detail: https://www.geeksforgeeks.org/introduction-to-recursion-2/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursionInDSA {

    public static void main(String[] args) {
        System.out.println("--- Demonstrating Recursion in DSA ---");

        // --- Function 1: Factorial Calculation ---
        System.out.println("\n1. Factorial Calculation:");
        int nFactorial = 5;
        System.out.println("Factorial of " + nFactorial + " is: " + factorial(nFactorial)); // Expected: 120

        // --- Function 2: Fibonacci Sequence ---
        System.out.println("\n2. Fibonacci Sequence (nth term):");
        int nFibonacci = 8;
        System.out.println("The " + nFibonacci + "th Fibonacci number is: " + fibonacci(nFibonacci)); // Expected: 21

        // --- Function 3: Sum of Digits ---
        System.out.println("\n3. Sum of Digits:");
        int numberForSum = 12345;
        System.out.println("Sum of digits for " + numberForSum + " is: " + sumOfDigits(numberForSum)); // Expected: 15

        // --- Function 4: Reverse a String ---
        System.out.println("\n4. Reverse a String:");
        String originalString = "hello";
        System.out.println("Original String: " + originalString);
        System.out.println("Reversed String: " + reverseString(originalString)); // Expected: olleh

        // --- Function 5: Check if an Array is Sorted ---
        System.out.println("\n5. Check if an Array is Sorted:");
        int[] sortedArr = {1, 2, 3, 4, 5};
        int[] unsortedArr = {1, 3, 2, 4, 5};
        System.out.println("Is {1, 2, 3, 4, 5} sorted? " + isArraySorted(sortedArr, 0)); // Expected: true
        System.out.println("Is {1, 3, 2, 4, 5} sorted? " + isArraySorted(unsortedArr, 0)); // Expected: false

        // --- Function 6: Tower of Hanoi ---
        System.out.println("\n6. Tower of Hanoi (Steps to solve):");
        int numDisks = 3;
        System.out.println("Steps to solve Tower of Hanoi for " + numDisks + " disks:");
        towerOfHanoi(numDisks, 'A', 'C', 'B'); // A: Source, C: Destination, B: Auxiliary

        // --- Function 7: Generate Permutations of a String ---
        System.out.println("\n7. Generate Permutations of a String:");
        String strPermute = "ABC";
        System.out.println("Permutations of \"" + strPermute + "\":");
        generatePermutations(strPermute, 0, strPermute.length() - 1);

        // --- Function 8: Binary Search (Recursive) ---
        System.out.println("\n8. Binary Search (Recursive):");
        int[] sortedNumbers = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int target1 = 50;
        int target2 = 95;
        System.out.println("Array: " + Arrays.toString(sortedNumbers));
        int index1 = binarySearch(sortedNumbers, target1, 0, sortedNumbers.length - 1);
        int index2 = binarySearch(sortedNumbers, target2, 0, sortedNumbers.length - 1);
        System.out.println("Index of " + target1 + ": " + (index1 != -1 ? index1 : "Not Found")); // Expected: 4
        System.out.println("Index of " + target2 + ": " + (index2 != -1 ? index2 : "Not Found")); // Expected: Not Found
    }

    /**
     * 1. Factorial Calculation (Recursive)
     * Base Case: factorial(0) = 1
     * Recursive Step: factorial(n) = n * factorial(n-1)
     * This function calculates the factorial of a non-negative integer n.
     * It's a classic example of simple recursion.
     *
     * @param n The non-negative integer for which to calculate the factorial.
     * @return The factorial of n.
     */
    public static int factorial(int n) {
        // Base case: If n is 0, the factorial is 1.
        if (n == 0) {
            return 1;
        }
        // Recursive step: n * factorial of (n-1)
        return n * factorial(n - 1);
    }

    /**
     * 2. Fibonacci Sequence (Recursive)
     * Base Cases: fibonacci(0) = 0, fibonacci(1) = 1
     * Recursive Step: fibonacci(n) = fibonacci(n-1) + fibonacci(n-2)
     * This function calculates the nth term of the Fibonacci sequence.
     * It's a common example but can be inefficient due to redundant calculations
     * (overlapping subproblems) if not optimized (e.g., with memoization).
     *
     * @param n The index of the Fibonacci number to calculate (0-indexed).
     * @return The nth Fibonacci number.
     */
    public static int fibonacci(int n) {
        // Base cases:
        if (n <= 1) {
            return n;
        }
        // Recursive step: Sum of the previous two Fibonacci numbers.
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 3. Sum of Digits (Recursive)
     * Base Case: If the number is 0, the sum is 0.
     * Recursive Step: sumOfDigits(n) = (n % 10) + sumOfDigits(n / 10)
     * This function calculates the sum of the digits of a given integer.
     *
     * @param n The integer whose digits are to be summed.
     * @return The sum of the digits of n.
     */
    public static int sumOfDigits(int n) {
        // Base case: If n is 0, there are no more digits to sum.
        if (n == 0) {
            return 0;
        }
        // Recursive step: Add the last digit (n % 10) to the sum of the remaining digits (n / 10).
        return (n % 10) + sumOfDigits(n / 10);
    }

    /**
     * 4. Reverse a String (Recursive)
     * Base Case: If the string is empty or has one character, it's already reversed.
     * Recursive Step: reverseString(str) = lastChar + reverseString(remainingString)
     * This function reverses a given string using recursion.
     *
     * @param str The string to be reversed.
     * @return The reversed string.
     */
    public static String reverseString(String str) {
        // Base case: If the string is empty or has only one character, return it.
        if (str.isEmpty() || str.length() == 1) {
            return str;
        }
        // Recursive step: Take the last character, and prepend it to the reversed rest of the string.
        return str.charAt(str.length() - 1) + reverseString(str.substring(0, str.length() - 1));
    }

    /**
     * 5. Check if an Array is Sorted (Recursive)
     * Base Case: If the array has 0 or 1 element, it is considered sorted.
     * Recursive Step: Compare current element with the next. If current <= next,
     * then recursively check the rest of the array starting from the next element.
     * This function checks if an integer array is sorted in non-decreasing order.
     *
     * @param arr   The array to check.
     * @param index The current index being examined. Starts from 0.
     * @return True if the array is sorted, false otherwise.
     */
    public static boolean isArraySorted(int[] arr, int index) {
        // Base case: If we've reached the end of the array (or it's empty/single-element), it's sorted.
        if (index == arr.length - 1 || arr.length <= 1) {
            return true;
        }
        // Recursive step: Check if the current element is less than or equal to the next,
        // AND recursively check the rest of the array.
        if (arr[index] <= arr[index + 1]) {
            return isArraySorted(arr, index + 1);
        } else {
            // If the current element is greater than the next, it's not sorted.
            return false;
        }
    }

    /**
     * 6. Tower of Hanoi (Recursive)
     * This function prints the steps to solve the Tower of Hanoi puzzle.
     * Base Case: If there's only one disk, move it directly from source to destination.
     * Recursive Steps:
     * 1. Move n-1 disks from source to auxiliary using destination as auxiliary.
     * 2. Move the nth disk from source to destination.
     * 3. Move n-1 disks from auxiliary to destination using source as auxiliary.
     *
     * @param n      The number of disks.
     * @param source The source peg.
     * @param dest   The destination peg.
     * @param aux    The auxiliary (helper) peg.
     */
    public static void towerOfHanoi(int n, char source, char dest, char aux) {
        // Base case: If only one disk, move it directly.
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + dest);
            return;
        }
        // Step 1: Move n-1 disks from source to auxiliary using destination as auxiliary.
        towerOfHanoi(n - 1, source, aux, dest);
        // Step 2: Move the nth disk from source to destination.
        System.out.println("Move disk " + n + " from " + source + " to " + dest);
        // Step 3: Move n-1 disks from auxiliary to destination using source as auxiliary.
        towerOfHanoi(n - 1, aux, dest, source);
    }

    /**
     * 7. Generate Permutations of a String (Recursive)
     * This function generates all unique permutations of a given string.
     * It uses a backtracking approach.
     *
     * @param str The string for which to generate permutations.
     * @param l   The starting index of the substring to consider for permutation.
     * @param r   The ending index of the substring to consider for permutation.
     */
    private static void generatePermutations(String str, int l, int r) {
        // Base case: If l == r, a permutation is complete, print it.
        if (l == r) {
            System.out.println(str);
        } else {
            // Recursive step: Iterate through the remaining characters to swap.
            for (int i = l; i <= r; i++) {
                // Swap the current character with the character at index 'l'.
                str = swap(str, l, i);
                // Recursively call for the next position.
                generatePermutations(str, l + 1, r);
                // Backtrack: Swap back to restore the original string for the next iteration.
                str = swap(str, l, i);
            }
        }
    }

    // Helper function to swap characters in a string
    private static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    /**
     * 8. Binary Search (Recursive)
     * This function implements binary search recursively to find the index of a target element
     * in a sorted array.
     * Base Case: If low > high, the element is not found.
     * Recursive Steps:
     * 1. Calculate mid.
     * 2. If arr[mid] == target, return mid.
     * 3. If arr[mid] < target, search in the right half (mid + 1 to high).
     * 4. If arr[mid] > target, search in the left half (low to mid - 1).
     *
     * @param arr    The sorted array to search in.
     * @param target The element to search for.
     * @param low    The lower bound of the search range.
     * @param high   The upper bound of the search range.
     * @return The index of the target if found, otherwise -1.
     */
    public static int binarySearch(int[] arr, int target, int low, int high) {
        // Base case: If the search range is invalid (low crosses high), element not found.
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2; // Calculate mid to prevent potential overflow

        // If the target is found at mid.
        if (arr[mid] == target) {
            return mid;
        }
        // If the target is in the right half.
        else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, high);
        }
        // If the target is in the left half.
        else {
            return binarySearch(arr, target, low, mid - 1);
        }
    }
}
