public class MissingAndRepeating {
    public static int[] findMissingAndRepeating(int[] nums) {
        int n = nums.length;
        long sumN = (long)n * (n + 1) / 2;
        long sumN2 = (long)n * (n + 1) * (2 * n + 1) / 6;
        
        long sum = 0, sumSquares = 0;
        for (int num : nums) {
            sum += num;
            sumSquares += (long) num * num;
        }
        
        // Let missing - repeating = diff1
        long diff1 = sumN - sum;  
        // Let missing^2 - repeating^2 = diff2 which is (missing - repeating)(missing + repeating)
        long diff
    }
}