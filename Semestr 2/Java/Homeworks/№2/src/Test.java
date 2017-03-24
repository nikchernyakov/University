/**
 * Created by Никита on 30.05.2016.
 */
public class Test {
    static void sortWith(String algo, int[] a)throws  Exception{
        Sorter.class.getMethod(algo,int[].class).invoke(a);
    }

    public static void main(String[] args) {

    }
}
