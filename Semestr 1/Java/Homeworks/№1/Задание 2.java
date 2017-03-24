/* Черняков Никита
    А3100
    Домашняя работа №1 Задание 2
 */
import static java.lang.Math.*;
import java.util.Scanner;
/* Алгоритм решения:
    Выражаем уравнение двух прямых, на которых лежат заданные отрезки. Затем через уравнение прямой выражаем x и y
    Не забываем проверять параллельны ли отрезки и совпадают ли они
    Заменил деление на произведение в некоторых уравнениях, так как при делении появляется погрешность
 */
public class Test {
    public static double det(double x1, double y1, double x2, double y2){  // функция считает определитель матрицы 2*2
        return x1 * y2 - x2 * y1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
// Считывание данных
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();
        double x4 = scanner.nextDouble();
        double y4 = scanner.nextDouble();
//  Приведение отрезков к векторам
        double s1x = x2 - x1, s1y = y2 - y1; // x и y координаты 1 вектора
        double s2x = x4 - x3, s2y = y4 - y3; // x и y координаты 2 вектора
// Находим коэффициенты C для общего уравниения прямой через уравнение прямой
        double C1 = x1 * y2 - x2 * y1 ;  // коэффициент 1 прямой
        double C2 = x3 * y4 - x4 * y3 ;  // коэффициент 2 прямой
// Проверяем на коллинеарность два вектора,
        if(s1x * s2y == s2x * s1y){
          /* Подставляем координаты 3-й и 4-й точки в уравнение прямой для первой прямой,
           если хоть какая-нибудь из этих точек лежит на этой прямой, то отрезки частично совпадают */
            if(C1 == C2){
                if((x3 >= min(x1, x2) && x3 <= max(x1, x2)) || (x4 >= min(x1, x2) && x4 <= max(x1, x2))) {
                    System.out.println("Segments are same");  //отрезки совпадают (частично)
                }
                else{
                    System.out.println("Segments do not intersect");  //отрезки не совпадают
                }
            }
            else{
                System.out.println("Segments do not intersect");  //отрезки параллельны
            }
            return;
        }
// Решаем систему уравненией методом Крамера
    // Находим детерминанты
        double d = det(-s1y, s1x, -s2y, s2x);
        double dX = det(s1x, C1, s2x, C2);
        double dY = det(-s1y, C1, -s2y, C2);
    // Находим x и y
        double x = dX / d;
        double y = dY / d;
// Вывод ответа (если нашлась точка пересечения)
        if((x >= min(x1, x2) && x <= max(x1, x2)) && (x >= min(x3, x4) && x <= max(x3, x4))) {   //проверяем принадлежность точки отрезку
            System.out.println("Segments intersect in (" + x + ";" + y + ")"); // точка пересечения отрезков
        }
        else{
            System.out.println("Segments do not intersect");  // отрезки не пересекаются
        }
    }
}
