public class LCM {
    public static void main(String[] args) {
        int a = 56, b = 98;
        int max = (a > b) ? a : b; //Ternary statement

        while (true) {
            if (max % a == 0 && max % b == 0) {
                System.out.println("LCM of " + a + " and " + b + " is " + max);
                break;
            }
            max++; 
        }
    }
}