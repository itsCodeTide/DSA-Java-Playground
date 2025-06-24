public class StringOperationsDemo {
    public static void main(String[] args) {
        String s1 = "Labh";
        String s2 = new String("Jethe");

        // Length
        System.out.println("Length: " + s1.length());

        // Character access
        System.out.println("Char at 0: " + s1.charAt(0));

        // Substring
        System.out.println("Substring: " + s1.substring(1, 4));

        // Concatenation
        System.out.println("Concatenation: " + s1 + s2);

        // Equality checks
        System.out.println("Equals: " + s1.equals(s2));
        System.out.println("EqualsIgnoreCase: " + s1.equalsIgnoreCase("HELLO"));

        // Compare
        System.out.println("CompareTo: " + s1.compareTo(s2));

        // Prefix/Suffix check
        System.out.println("Starts with 'He': " + s1.startsWith("He"));
        System.out.println("Ends with 'lo': " + s1.endsWith("lo"));

        // Indexes
        System.out.println("IndexOf('l'): " + s1.indexOf('l'));
        System.out.println("LastIndexOf('l'): " + s1.lastIndexOf('l'));

        // Replace
        System.out.println("Replace 'l' with 'p': " + s1.replace('l', 'p'));

        // Upper and lower case
        System.out.println("ToUpperCase: " + s1.toUpperCase());
        System.out.println("ToLowerCase: " + s1.toLowerCase());

        // Trim
        String s3 = "   hello   ";
        System.out.println("Trimmed: '" + s3.trim() + "'");

        // Split
        String s4 = "a,b,c";
        String[] parts = s4.split(",");
        System.out.print("Split: ");
        for (String part : parts) {
            System.out.print(part + " ");
        }
        System.out.println();

        // Convert to char array
        char[] chars = s1.toCharArray();
        System.out.print("Char Array: ");
        for (char ch : chars) {
            System.out.print(ch + " ");
        }
        System.out.println();

        // Empty and blank checks
        String s5 = "";
        String s6 = "   ";
        System.out.println("IsEmpty: " + s5.isEmpty());
        System.out.println("IsBlank (Java 11+): " + s6.isBlank());
    }
}