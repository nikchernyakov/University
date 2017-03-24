public class Program {
    public static void main(String[] args) {
        Complex c1 = new Complex(2, -1);
        System.out.println(c1);
        Complex c2 = new Complex(3);
        System.out.println(c2);
        //System.out.println(Direction.LEFT);
        System.out.println(Complex.add(c1, c2));
        System.out.println(c1.add(c2));
    }
}
