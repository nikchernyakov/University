import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class NewList<E extends Comparable<E>>{
    List<E> list = new LinkedList<E>();

    public NewList(E[][] arrays) {
        for(E[] array : arrays){
            for(E value : array){
                list.add(value);
            }
        }
        list.sort(new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.compareTo(o2);
            }
        });
    }
    
}

