import java.util.Scanner;

public class EvenOdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        /*if(num % 2 == 0){
        System.out.print("Even Number.");
         }
        else {
        System.out.print("Odd Number.");
        } */
        System.out.println(num + " is " + (num % 2 == 0 ? "Even" : "Odd")); //Ternary Operator
    }
}