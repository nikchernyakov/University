import java.util.function.Function;

/**
 * Created by nikchernyakov on 28.11.16.
 */
public class Test {
    private static int summing(Function<Integer, Integer> f){
        int s = 0;
        for(int k = 0; k < 100; k++) s += f.apply(k);
        return  s;

    }

    public static void main(String[] args) {
        int s = 0;
        Summator i = new Summator();
        for(; i.i < 100; i.add()) {
            s += summing(k -> k + i.i);
        }
        System.out.println(s);
    }

    private static class Summator{
        int i;
        void add(){
            i++;
        }
    }
}
