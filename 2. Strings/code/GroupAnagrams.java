import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);

            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams obj = new GroupAnagrams();
        String[] input = {"cat", "dog", "tac", "god", "act"};
        List<List<String>> result = obj.groupAnagrams(input);

        for (List<String> group : result) {
            System.out.println(group);
        }
    }
}