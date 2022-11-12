public class Main {
    public static boolean IsPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int num = 2; num <= 100; ++num) {
            if (IsPrime(num)) {
                System.out.println(num);
            }
        }
    }
}