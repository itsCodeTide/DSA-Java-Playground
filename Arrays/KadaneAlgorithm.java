    public class KadaneAlgorithm {
    public static int maxSubarraySum(int[] arr) {
        int maxSum = Integer.MIN_VALUE; //Minimum value that variable maxSum can store
        int currentSum = 0;

        for (int num : arr) {   
            currentSum += num;
            if (currentSum > maxSum) {
                maxSum = currentSum; // Update max sum
            }
            if (currentSum < 0) {
                currentSum = 0; // Reset if sum drops below 0
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum: " + maxSubarraySum(arr));
    }
}
