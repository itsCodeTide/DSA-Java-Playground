public class DecimalToBinary {
    public static void main(String[] args) {
        int num = 10;
        int[] binary = new int[32]; 
        int index = 0;

        while (num > 0) {
            binary[index++] = num % 2;
            num /= 2;
        }

        System.out.print("Binary representation: ");
        for (int i = index - 1; i >= 0; i--) {
            System.out.print(binary[i]);
        }
    }
}