/**
 * Дубовицкий Евгений
 * группа А3101
 * Специальный семинар
 * Домашняя работа № 3
 * Задача 1
 */

import java.lang.reflect.InvocationTargetException;
import java.util.Random;


public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Deque<Integer> deq = null;
        try {
            deq = (Deque) Class.forName(args[0]).newInstance();
        } catch (ClassNotFoundException exc) {
            System.out.println("Указанный класс не найден.");
        } catch (InstantiationException exc) {
            System.out.println("Не существует пустого конструктора указанного класса.");
        } catch (IllegalAccessException exc) {
            System.out.println("Нет доступа к пустому конструктору заданного класса.");
        } catch (ClassCastException exc) {
            System.out.println("Указанный класс несовместим с Deque.");
        }
        if (deq == null) {
            return;
        }

        Random rand = new Random();
        int n = Integer.parseInt(args[1]);
        System.out.println("Созданная очередь:");
        for (int i = 0; i < n; i++) {
            int number = rand.nextInt(10);
            deq.addLast(number);
            System.out.print(number + " ");
        }

                int sum = 0;
        for (int elem : deq) {
            sum += elem;
        }
        System.out.println("\nСумма всех элементов: " + sum);

        try {
            n = (Integer) deq.getClass().getMethod("size").invoke(deq);
            System.out.println("Количество элементов в очереди: " + n);
        } catch (NoSuchMethodException | IllegalAccessException | ClassCastException exc) {
            System.out.println("В классе " + args[0] + " метод public int size() не реализован.");
        } catch (InvocationTargetException exc) {
            System.out.println("Неизвестная ошибка при работе метода.");
        }

        System.out.println("Удаляем из очереди по два элемента слева и справа:");
        deq.removeFirst();
        deq.removeFirst();
        deq.removeLast();
        deq.removeLast();
        for (int elem : deq) {
            System.out.print(elem + " ");
        }
    }
}
