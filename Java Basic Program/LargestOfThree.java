import java.util.Scanner;

public class LargestOfThree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter three numbers: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        
        int largest;
        if (a > b && a > c) {
            largest = a;
        } else if (b > c) {
            largest = b;
        } else {
            largest = c;
        }
        //int largest = Math.max(a, Math.max(b, c));
        
        System.out.println("Largest number: " + largest);
    }
}