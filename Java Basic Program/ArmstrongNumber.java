public class ArmstrongNumber {
    public static void main(String[] args) {
        int num = 153;
        int sum = 0, temp = num;

        while (temp > 0) {
            int digit = temp % 10;
            sum += (digit * digit * digit);
            temp /= 10;
        }

        System.out.println(num + " is " + (sum == num ? "an Armstrong number" : "not an Armstrong number"));
    }
}