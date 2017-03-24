/* Черняков Никита А3100
   Домашняя работа №1 
	
	Косинус угла между BA и BC находим через скалярное произведение этих сторон 
        (зная  координаты вершин A,B,C найдем координаты векторов BA и BC, а также их длину)
*/
import static java.lang.Math.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        final int x;
        x = 1;
        x = 2;
        Scanner scanner= new Scanner(System.in);
        double x1 = scanner.nextDouble();  //координата x точки A
        double y1 = scanner.nextDouble();  //координата y точки A
        double x2 = scanner.nextDouble();  //координата x точки B
        double y2 = scanner.nextDouble();  //координата y точки B
        double x3 = scanner.nextDouble();  //координата x точки C
        double y3 = scanner.nextDouble();  //координата y точки C
        double BA1 = x1-x2; 
        double BA2 = y1-y2;
        double BC1 = x3-x2;
        double BC2 = y3-y2;
        System.out.println(Math.cos((BA1*BC1+BA2*BC2)/(sqrt(BA1*BA1+BA2*BA2)*sqrt(BC1*BC1+BC2*BC2))); //выразили из формулы скалярного произведения векторов косинус угла и затем посчитали угол
    }
}
