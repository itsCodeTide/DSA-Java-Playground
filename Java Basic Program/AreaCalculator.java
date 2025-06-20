import java.util.Scanner;

public class AreaCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a shape: 1. Square 2. Rectangle 3. Triangle");
        int choice = scanner.nextInt();

        double area = 0;

        switch (choice) {
            case 1:
                System.out.print("Enter side length: ");
                double side = scanner.nextDouble();
                area = side * side;
                break;
            case 2:
                System.out.print("Enter length: ");
                double length = scanner.nextDouble();
                System.out.print("Enter width: ");
                double width = scanner.nextDouble();
                area = length * width;
                break;
            case 3:
                System.out.print("Enter base: ");
                double base = scanner.nextDouble();
                System.out.print("Enter height: ");
                double height = scanner.nextDouble();
                area = (base * height) / 2;
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        System.out.println("The area is: " + area);
        scanner.close();
    }
}