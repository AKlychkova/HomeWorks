import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[10];
        // Заполняем случайными числами от 0 до 100
        for (int i = 0; i < array.length; ++i) {
            array[i] = (int) (Math.random() * 100);
        }
        System.out.println("Array: " + Arrays.toString(array));
        int[] sorted_array = Arrays.copyOf(array,array.length);
        Arrays.sort(sorted_array);
        System.out.println("Min: " + sorted_array[0]);
        System.out.println("Max: " + sorted_array[sorted_array.length-1]);
        System.out.println("Median: " + sorted_array[sorted_array.length/2]);
    }
}