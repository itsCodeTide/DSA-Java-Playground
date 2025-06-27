import java.util.*;

public class LongestUniqueSubstring {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int maxLen = 0, start = 0;

        for (int end = 0; end < s.length(); end++) {
            while (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start));
                start++;
            }
            set.add(s.charAt(end));
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LongestUniqueSubstring obj = new LongestUniqueSubstring();
        String s = "abcabcbb";
        System.out.println("Longest unique substring length: " + obj.lengthOfLongestSubstring(s));
    }
}