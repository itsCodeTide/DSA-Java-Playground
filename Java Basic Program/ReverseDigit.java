public class ReverseDigit {
    public static void main(String[] args) {
        int num = 1234, reversed = 0;

        while (num > 0) {
            int digit = num % 10; //extract last digit
            reversed = reversed * 10 + digit; //store reversed order of thr number
            num /= 10; //remove last digit
        }

        System.out.println("Reversed number: " + reversed);
    }
}