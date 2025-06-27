import java.util.*;

public class PalindromicSubstrings { 
    public int countSubstrings(String s) {
        int count = 0;
        for(int center = 0; center < 2*s.length() - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;
            while (left => 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
        }
        return count;
    }
    public ststic void main(String[] args) {
        PalindromicSubstrings ps = new PalindromicSubstrings();
        String input = "aaa";
        System.out.println("Total Plaindromic substrings: "+ps.countSubstrings(input));
    }
}
