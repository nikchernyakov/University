import java.util.HashMap;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

public class Main {
    private static HashMap<Character, IntBinaryOperator> binaryOperators = new HashMap<>();
    static {
        binaryOperators.put('+', (a, b) -> a + b);
        binaryOperators.put('-', (a, b) -> a - b);
        binaryOperators.put('*', (a, b) -> a * b);
    }

    public static void main(String[] args) {

        // 5 + 2 * 3 - 4
        Node<Integer, Character> first = new BiNode<>('*',
                new Leaf<>(2),
                new Leaf<>(3));
        Node<Integer, Character> second = new BiNode<>('-',
                first,
                new Leaf<>(4));
        Node<Integer, Character> third = new BiNode<>('+',
                new Leaf<>(5),
                second);

        System.out.println("Operation: " + treeToString(third) + " = " + calculateTree(third));
        System.out.println("Invert operation: " + treeToString(invertTree(third)) + " = " + calculateTree(invertTree(third)));
    }

    private static Integer calculateTree(Node<Integer, Character> tree){
        return tree.process(Function.identity(), (c, a, b) -> binaryOperators.get(c).applyAsInt(a, b));
    }

    private static String treeToString(Node<Integer, Character> tree){
        return tree.process(Object::toString, (c, a, b) -> "(" + a + " " + c + " " + b + ")");
    }

    private static Node<Integer, Character> invertTree(Node<Integer, Character> tree){
        return tree.process(t -> new Leaf<>(-t), BiNode<Integer, Character>::new);
    }
}
