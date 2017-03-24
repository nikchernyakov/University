import java.util.List;

/**
 * Created by Никита on 29.05.2016.
 */
public class Main {
    public static void main(String[] args) {
        Tree<Integer,Integer> tree = new Tree<>();
        tree.add(5,5);
        tree.add(2,2);
        tree.add(7,7);
        tree.add(1,1);
        tree.add(4,4);
        tree.add(6,6);
        tree.add(9,9);
        tree.add(3,3);
        tree.add(8,8);
        tree.add(10,10);

        List<Integer> list = tree.get(2,9);
        for(int iter : list){
            System.out.print(iter + " ");
        }
    }
}
