import java.util.List;

public class Sort<E extends Comparable<E> > {
    public static void main (String[] args) {
        Integer[][] arrays = new Integer[][]{
                { 5, 7, 10, 23 },
                { 2, 12 },
                { 6, 7, 7, 9 },
                { 1 }
        };
        List<Integer> list = new NewList<>(arrays).list;

        for(int it : list){
            System.out.println(it);
        }
    }

}
