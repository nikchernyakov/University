import java.util.ArrayList;
import java.util.List;

/**
 * Created by Никита on 09.05.2016.
 */
public class Test extends Substrings{
    public static void main(String[] args) {
        List<Integer> whole = new ArrayList<>();
        List<Integer> part = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            whole.add(i);
        }
        part.add(5);
        part.add(6);
        part.add(7);
        System.out.println(subsequence(whole, part));
    }
}
