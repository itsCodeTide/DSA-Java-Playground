import java.util.*;

public class ArrayManipulation {
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        long[] arr = new long[n + 2]; // use long to avoid overflow

        for (List<Integer> query : queries) {
            int a = query.get(0);
            int b = query.get(1);
            int k = query.get(2);

            arr[a] += k;
            arr[b + 1] -= k;
        }

        long max = 0;
        long current = 0;

        for (int i = 1; i <= n; i++) {
            current += arr[i];
            if (current > max) {
                max = current;
            }
        }

        return max;
    }
}