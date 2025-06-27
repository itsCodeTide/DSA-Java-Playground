import java.util.HashMap; //Alternative: import java.util.*;
import java.util.Map;

public class FrequentElements {
    public static void findFrequentElements(int[] arr, int k) {
        int n = arr.length;
        int threshold = n / k;

        // Step 1: Count frequencies using HashMap
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Identify elements exceeding n/k frequency
        System.out.println("Elements appearing more than n/k times:");
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > threshold) {
                System.out.print(entry.getKey() + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 2, 3, 3, 4, 4, 4, 4, 5, 6};
        int k = 4;
        findFrequentElements(arr, k);
    }
}