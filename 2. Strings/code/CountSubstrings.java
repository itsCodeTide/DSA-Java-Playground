public class CountSubstrings {
    public int countAllSubstrings(String s) {
        int n = s.length();
        return n * (n + 1) / 2;
    }

    public static void main(String[] args) {
        CountSubstrings obj = new CountSubstrings();
        System.out.println(obj.countAllSubstrings("abc")); // Output: 6
    }
}