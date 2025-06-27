import java.util.*;

public class MissingInteger {
    public static int findMissing(int[] nums) {
        int n = nums.length;
        int expectedSum = (n+1) * (n+2)/2;
        int actualSum = 0;
        for(int num: nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of an array: ");
        int size = sc.nextInt();
        int[] nums = new int[size];
        for(int i=0; i<size; i++){
        System.out.print("Element " + (i + 1) + ": ");
        nums[i] = sc.nextInt();
        }
        findMissing(nums);
        

    }

}