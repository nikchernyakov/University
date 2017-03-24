import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new List<>();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        System.out.println(list.size());
        list.swap(0, 9);
        list.swap(1, 2);
        list.swap(4, 1);
        list.swap(8, 3);
        list.sort();
        list.swap(3, 3);
        list.swap(7, 3);
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.getElem(i) + " ");
        }
    }
}
