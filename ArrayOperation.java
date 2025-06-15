import java.util.Scanner;
import java.util.Arrays;

public class ArrayOperation {
    static int currentSize;

    static void search(int[] a, int target) {
        boolean found = false;
        for (int i = 0; i < currentSize; i++) {
            if (a[i] == target) {
                System.out.println("Found at index: " + i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Element not found.");
        }
    }

    static void deletion(int[] a, int index) {
        if (index < 0 || index >= currentSize) {
            System.out.println("Invalid index.");
            return;
        }
        for (int i = index; i < currentSize - 1; i++) {
            a[i] = a[i + 1];
        }
        currentSize--;
        System.out.println("Element deleted.");
        display(a);
    }

    static void insertion(int[] a, int index, int value) {
        if (currentSize == a.length) {
            System.out.println("Array is full. Insertion not possible.");
            return;
        }
        if (index < 0 || index > currentSize) {
            System.out.println("Invalid index.");
            return;
        }
        for (int i = currentSize; i > index; i--) {
            a[i] = a[i - 1];
        }
        a[index] = value;
        currentSize++;
        System.out.println("Element inserted.");
        display(a);
    }

    static void display(int[] a) {
        System.out.print("Array Elements: ");
        for (int i = 0; i < currentSize; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    static int largestElement(int[] a) {
        int max = a[0];
        for (int i = 1; i < currentSize; i++) {
            if (a[i] > max) max = a[i];
        }
        return max;
    }

    static int secondLargest(int[] a) {
        int max = largestElement(a);
        int second = Integer.MIN_VALUE;
        for (int i = 0; i < currentSize; i++) {
            if (a[i] != max && a[i] > second) {
                second = a[i];
            }
        }
        return (second == Integer.MIN_VALUE) ? -1 : second;
    }

    static void smallest(int[] a) {
        int min = a[0];
        for (int i = 1; i < currentSize; i++) {
            if (a[i] < min) min = a[i];
        }
        System.out.println("Smallest Element: " + min);
    }

    static void reverse(int[] a) {
        for (int i = 0; i < currentSize / 2; i++) {
            int temp = a[i];
            a[i] = a[currentSize - 1 - i];
            a[currentSize - 1 - i] = temp;
        }
        System.out.print("Reversed: ");
        display(a);
    }

    static void rotate(int[] a, int k) {
        if (currentSize == 0) return;
        k %= currentSize;
        int[] temp = new int[currentSize];
        for (int i = 0; i < currentSize; i++) {
            temp[(i + k) % currentSize] = a[i];
        }
        for (int i = 0; i < currentSize; i++) {
            a[i] = temp[i];
        }
        System.out.println("Array after rotation:");
        display(a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int num = sc.nextInt();

        int[] arr = new int[num + 10]; // some buffer space for insertions
        currentSize = num;

        for (int i = 0; i < num; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }

        int choice;
        do {
            System.out.println("\n===== Array Operations Menu =====");
            System.out.println("1. Display");
            System.out.println("2. Insertion");
            System.out.println("3. Deletion");
            System.out.println("4. Search");
            System.out.println("5. Reverse");
            System.out.println("6. Rotate");
            System.out.println("7. Largest Element");
            System.out.println("8. Second Largest Element");
            System.out.println("9. Smallest Element");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    display(arr);
                    break;
                case 2:
                    System.out.print("Enter index and element: ");
                    int idx = sc.nextInt();
                    int val = sc.nextInt();
                    insertion(arr, idx, val);
                    break;
                case 3:
                    System.out.print("Enter index to delete: ");
                    int delIdx = sc.nextInt();
                    deletion(arr, delIdx);
                    break;
                case 4:
                    System.out.print("Enter element to search: ");
                    int key = sc.nextInt();
                    search(arr, key);
                    break;
                case 5:
                    reverse(arr);
                    break;
                case 6:
                    System.out.print("Enter rotation step (k): ");
                    int k = sc.nextInt();
                    rotate(arr, k);
                    break;
                case 7:
                    System.out.println("Largest Element: " + largestElement(arr));
                    break;
                case 8:
                    int second = secondLargest(arr);
                    if (second == -1)
                        System.out.println("No second largest element.");
                    else
                        System.out.println("Second Largest Element: " + second);
                    break;
                case 9:
                    smallest(arr);
                    break;
                case 10:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 10);

        sc.close();
    }
}