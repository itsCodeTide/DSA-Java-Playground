public class CountDigitOccurrences {
    public static void main(String[] args) {
        int num = 122344412, digit = 4, count = 0;

        while (num > 0) {
            if (num % 10 == digit) {
                count++;
            }
            num /= 10;
        }

        System.out.println("Digit " + digit + " appears " + count + " times.");
    }
}