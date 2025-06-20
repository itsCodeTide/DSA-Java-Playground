public class GCDWithoutRecursion {
    public static void main(String[] args) {
        int a = 56, b = 98;
        int gcd = 1;

        for (int i = 1; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }

        System.out.println("GCD of " + a + " and " + b + " is " + gcd);
    }
}