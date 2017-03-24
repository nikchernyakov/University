public class Complex {
    final private double re, im;

    public Complex(double re, double im){
        this.re = re;
        this.im = im;
    }

    //конструктор
    public Complex(double re){
        this.re = re;
        im = 0;
        // или this(re,0)
    }

    public double getRe(){
        return re;
    }

    public double getIm(){
        return im;
    }

    @Override
    public String toString(){
        return String.format("%g + %gi", re, im);
    }

    /*public static Complex add(Complex c1, Complex c2){   //сумма комплексных чисел
        return new Complex(c1.re + c2.re, c1.im + c2.im);
    }*/

    public Complex add(Complex c2){
        return new Complex(re + c2.re, im + c2.im);
    }

    public static Complex add(Complex c1, Complex c2){
        return c1.add(c2);
    }

}
