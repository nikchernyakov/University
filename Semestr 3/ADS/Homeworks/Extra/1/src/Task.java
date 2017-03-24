import java.util.Arrays;
import java.util.Random;

public class Task {

    private static int mergeSort(int[] array, int p, int r) {
        if (r - p > 1) {
            int q = (p + r) / 2;
            return mergeSort(array, p, q) + mergeSort(array, q, r) + merge(array, p, q, r);
        }
        return 0;
    }

    private static int merge(
            int[] array, int p, int q, int r) {
        // Создаем копию участков в промежуточном массиве.
        int[] arrayCopy = Arrays.copyOfRange(array, p, r);
        int count = 0; // Количество инверсий на этом промежутке

        int i1 = 0;     // Индекс по первому участку в копии
        int i2 = q - p; // Индекс по второму учаску в копии
        int i = p;      // Индекс по исходному массиву, куда пересылается результат.

        while (i1 < q-p && i2 < r-p) {
            if(arrayCopy[i1] > arrayCopy[i2]){
                count++;  // Добавляем очередную инверсию
                array[i++] = arrayCopy[i2++];
            }
            else{
                array[i++] = arrayCopy[i1++];
                // Следующему элементу в первом отрезке добавляем число инверсий найденных для предыдущего элемента
                if(i1 < q - p) count += i2 - (q - p);
            }

        }
        while (i1 < q-p) array[i++] = arrayCopy[i1++];
        while (i2 < r-p) array[i++] = arrayCopy[i2++];
        return count;
    }

    public static int mergeSort(int[] array) {
        return mergeSort(array, 0, array.length);
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] array = new int[5];
        for(int i = 0; i < array.length; array[i++] = r.nextInt(10));
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println("\n" + mergeSort(array));
    }
}
