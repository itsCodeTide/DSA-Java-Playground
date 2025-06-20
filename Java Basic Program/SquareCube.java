import java.util.Scanner;

public class SquareCube {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        System.out.println("Square: " + (num * num));
        System.out.println("Cube: " + (num * num * num));
    }
}