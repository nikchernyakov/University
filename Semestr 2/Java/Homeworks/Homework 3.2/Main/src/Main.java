/**
 * Дубовицкий Е. И.
 * группа А3101
 * Специальный семинар
 * Домашняя работа № 3
 * Задача 2
 */

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;


public class Main {

    static final int ARRAY_SIZE = 20;

    public static void main(String[] args) {
        Random rand = new Random();
        Integer[] array = new Integer[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = rand.nextInt(100);
        }
        if (ARRAY_SIZE < 100) {
            System.out.println("Исходный массив:\n" + Arrays.toString(array));
        }

        long time = System.currentTimeMillis();
        try {
            Sort.class.getMethod(args[0], Comparable[].class).invoke(new Sort(), (Object) array);
        } catch (IllegalAccessException e) {
            System.out.println("Ошибка доступа к выбранному методу.");
        } catch (InvocationTargetException e) {
            System.out.println("Неизвестная ошибка во время сортировки.");
        } catch (NoSuchMethodException e) {
            System.out.println("Указанный метод не обнаружен.");
        }
        time = System.currentTimeMillis() - time;

        if (ARRAY_SIZE < 100) {
            System.out.println("Отсортированный методом " + args[0] + " массив:\n" + Arrays.toString(array));
        }
        System.out.println("Время сортировки: " + time + "мс");
    }
}

/*
    Результаты замера производительности методов:
        Кол-во элементов  | bubbleSort | quickSort | mergeSort
        ------------------+------------+-----------+----------
        100               | 1мс        | 1мс       | 1мс
        1 000             | 17мс       | 2мс       | 2мс
        10 000            | 208мс      | 15мс      | 6мс
        100 000           | -          | 437мс     | 84мс
        1 000 000         | -          | -         | 294мс

        "-" означает, что метод работал больше секунды.
        Размер подборки: по 3 замера на каждую ячейку.
 */
