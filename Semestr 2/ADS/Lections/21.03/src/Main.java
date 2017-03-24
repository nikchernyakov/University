import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Никита on 21.03.2016.
 */
public class Main {
    public static <E> Iterator<E> addElement(Iterator<E> iter, E element){
        List<E> list = new ArrayList<>();
        while (iter.hasNext()){
            list.add(iter.next());
        }
        list.add(element);
        return list.iterator();
    }
}
