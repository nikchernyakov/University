import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleStream {
    private static class Col implements Collector<Integer, List<Integer>, List<Integer>> {

        @Override
        public Supplier<List<Integer>> supplier() {
            return null;
        }

        @Override
        public BiConsumer<List<Integer>, Integer> accumulator() {
            return List::add;
        }

        @Override
        public BinaryOperator<List<Integer>> combiner() {
            return (l1, l2) -> { l1.addAll(l2); return l1; };
        }

        @Override
        public Function<List<Integer>, List<Integer>> finisher() {
            return Function.identity();
        }

      /*  @Override
        public  characteristic(){
            return new HashSet<>();
        }*/
    }

    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,3,6,4,2,12,13,9);
        /* First example
        Integer y = list.stream()
                .filter(x -> x % 2 == 1)
                .map(x -> x*x)
                .max(Comparator.naturalOrder()).get();
        System.out.println(y);
        */
        /* Second
        Summator s = new Summator();
        list.stream()
                .filter(x -> x % 2 == 1)
                .map(x -> x*x)
                //.forEach(x -> s.add(x));
                .forEach(s::add); // Так лучше и работает (шок)
        System.out.println(s.s);
        */
        /* Third
        list.stream()
                .filter(x -> x % 2 == 1)
                .map(x -> x*x)
                //.sorted() // сортирует по возрастанию
                .parallel() // параллельно выводит 0_о
                .forEach(System.out::println);
        */
        /* toArray()
        Integer[] array = list.stream()
                .filter(x -> x % 2 == 1)
                .map(x -> x*x)
                //.toArray(size -> new Integer[size]); // Выдает массив Object[]
                .toArray(Integer[]::new);
        System.out.println(Arrays.toString(array));
        */
        /* all/anyMatch()
        boolean b = list.stream()
                .filter(x -> x % 2 == 1)
                .map(x -> x*x)
                //.anyMatch(x -> x > 100) // Проверяет наличие хоть одного элемента
                .allMatch(x -> x > 100);  // Проверяет все элемент
        */
        // stream.concat(s1, s2); // объединяет два потока
        /* reduce()
        Integer i = list.stream()
                .filter(x -> x % 2 == 1)
                .map(x -> x*x)//.parallel() ничего не изменяет для первого reduce
                //.reduce(0, (x, y) -> x + y);
                //.parallel() - тоже не меняет
                //.reduce(0, (x, y) -> y - x);
                //.reduce((x, y) -> x + y).get(); // Если уверен, что там есть элементы
                .parallel() // Теперь это более эффективно работает
                .reduce(0, (x, y) ->  x + y, (x, y) -> x + y); // первая лямбда - складывает; вторая - суммирует результат
        System.out.println(i);
        */

        //Map<Boolean, List<Integer>> i = list.stream()
        //Map<Integer, List<Integer>> i = list.stream()
        Long i = list.stream()
                .filter(x -> x % 2 == 1)
                .map(x -> x*x)//.parallel() ничего не изменяет
                //.collect(Supplier, BiConsumer, BiConsumer) // Общий вид (const, аккумулятор, который берет и не возвращает, два результата в один)
                //.collect(Supplier<R> s, BiConsumer<R, T> cons, BiConsumer<R, R> res)
                //.collect(ArrayList::new, List::add, ArrayList::addAll);
                //.collect(Collectors.groupingBy(x -> x > 100));
                //.collect(Collectors.groupingBy(x -> x % 10));
                //.collect(Collectors.counting());
                .collect(Collectors.summingLong(x -> (long) x));
        // Stream<Integer> list = Stream.of(1, 3, 6, 4, 2, 12, 13, 9);
        // Stream<Integer> list = Stream.iterate(1, x -> x + 1);

        System.out.println(i);
    }

    interface Collector<T, A, R>{
        Supplier<A> supplier();
        BiConsumer<A, T> accumulator();
        BinaryOperator<A> combiner();
        Function<A, R> finisher();

    }

    private static class Summator{
        int s;
        public void add(int x){ s += x; }
    }
}
