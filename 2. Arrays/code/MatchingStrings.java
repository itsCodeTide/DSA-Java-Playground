import java.util.*;

public class MatchingStrings {
    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        Map<String, Integer> freqMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // Count frequency of each string in input list
        for (String str : strings) {
            freqMap.put(str, freqMap.getOrDefault(str, 0) + 1);
        }

        // For each query, fetch its count (or 0 if not found)
        for (String query : queries) {
            result.add(freqMap.getOrDefault(query, 0));
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<String> strings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            strings.add(scanner.nextLine());
        }

        int q = Integer.parseInt(scanner.nextLine());
        List<String> queries = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            queries.add(scanner.nextLine());
        }

        List<Integer> result = matchingStrings(strings, queries);

        for (int count : result) {
            System.out.println(count);
        }

        scanner.close();
    }
}