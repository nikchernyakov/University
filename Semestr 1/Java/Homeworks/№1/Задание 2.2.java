/* Черняков Никита
    А3100
    Домашняя работа №1 Задание 2 второй способ
 */
import static java.lang.Math.*;
import java.util.Scanner;

public class Test {
    public static double pr(double x,double y,double x0, double y0,double x1, double y1)
    {
        return (x-x0)*(y1-y0)-(y-y0)*(x1-x0);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();
        double x4 = scanner.nextDouble();
        double y4 = scanner.nextDouble();

        double d1 = pr(x1, y1, x3, y3, x4, y4);
        double d2 = pr(x2, y2, x3, y3, x4, y4);
        double d3 = pr(x3, y3, x1, y1, x2, y2);
        double d4 = pr(x4, y4, x1, y1, x2, y2);
        double t;

        if (x1 > x2 || (x1 == x2 && y1 > y2)) {
            t = x1;
            x1 = x2;
            x2 = t;
            t = y1;
            y1 = y2;
            y2 = t;
        }

        if (x3 > x4 || (x3 == x4 && y3 > y4)) {
            t = x1;
            x1 = x2;
            x2 = t;
            t = y1;
            y1 = y2;
            y2 = t;
        }

        if (d1 == 0 && d2 == 0) {
            if (x1 == x2 && x3 == x4 && x1 == x3) {
                if (y3 <= y2 && y1 <= y4) {
                    System.out.println("Segments intersect");
                }
                else {
                    System.out.println("Segments do not intersect"*);
                }
            }
            else {
                if (x3 <= x2 && x1 <= x4){
                    System.out.println("Segments intersect");
                }
                else
                    System.out.println("Segments do not intersect"*);
            }
        }
        else if (d1 * d2 <= 0
                    && d3 * d4 <= 0) {
            System.out.println("Segments intersect"*);
        }
        else {
            System.out.println("Segments do not intersect"*);
        }
    }
}
