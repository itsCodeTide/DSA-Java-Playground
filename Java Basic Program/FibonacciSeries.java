public class FibonacciSeries {
    public static void main(String[] args) {
        int N = 10;
        int first = 0, second = 1, next;
        
        for (int i = 0; i < N; i++) {
            System.out.print(first + " ");
            next = first + second;
            first = second;
            second = next;
        }
    }
}