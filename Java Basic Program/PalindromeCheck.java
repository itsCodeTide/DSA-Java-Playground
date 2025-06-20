public class PalindromeCheck {
    public static void main(String[] args) {
        char[] str = {'r', 'a', 'c', 'e', 'c', 'a', 'r'};
        boolean isPalindrome = true;
        int left = 0, right = str.length - 1;

        while (left < right) {
            if (str[left] != str[right]) {
                isPalindrome = false;
                break;
            }
            left++;  //Move towards right
            right--; //Move towards left
        }

        System.out.println("Is palindrome? " + isPalindrome);
    }
}