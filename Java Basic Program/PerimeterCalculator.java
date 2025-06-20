import java.util.Scanner;

public class PerimeterCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a shape: 1. Square 2. Rectangle 3. Triangle");
        int choice = scanner.nextInt();

        double perimeter = 0;

        switch (choice) {
            case 1:
                System.out.print("Enter side length: ");
                double side = scanner.nextDouble();
                perimeter = 4 * side;
                break;
            case 2:
                System.out.print("Enter length: ");
                double length = scanner.nextDouble();
                System.out.print("Enter width: ");
                double width = scanner.nextDouble();
                perimeter = 2 * (length + width);
                break;
            case 3:
                System.out.print("Enter first side: ");
                double a = scanner.nextDouble();
                System.out.print("Enter second side: ");
                double b = scanner.nextDouble();
                System.out.print("Enter third side: ");
                double c = scanner.nextDouble();
                perimeter = a + b + c;
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        System.out.println("The perimeter is: " + perimeter);
        scanner.close();
    }
}