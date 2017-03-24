/* �������� ������
    �3100
    �������� ������ �1 ������� 2
 */
import static java.lang.Math.*;
import java.util.Scanner;
/* �������� �������:
    �������� ��������� ���� ������, �� ������� ����� �������� �������. ����� ����� ��������� ������ �������� x � y
    �� �������� ��������� ����������� �� ������� � ��������� �� ���
    ������� ������� �� ������������ � ��������� ����������, ��� ��� ��� ������� ���������� �����������
 */
public class Test {
    public static double det(double x1, double y1, double x2, double y2){  // ������� ������� ������������ ������� 2*2
        return x1 * y2 - x2 * y1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
// ���������� ������
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();
        double x4 = scanner.nextDouble();
        double y4 = scanner.nextDouble();
//  ���������� �������� � ��������
        double s1x = x2 - x1, s1y = y2 - y1; // x � y ���������� 1 �������
        double s2x = x4 - x3, s2y = y4 - y3; // x � y ���������� 2 �������
// ������� ������������ C ��� ������ ���������� ������ ����� ��������� ������
        double C1 = x1 * y2 - x2 * y1 ;  // ����������� 1 ������
        double C2 = x3 * y4 - x4 * y3 ;  // ����������� 2 ������
// ��������� �� �������������� ��� �������,
        if(s1x * s2y == s2x * s1y){
          /* ����������� ���������� 3-� � 4-� ����� � ��������� ������ ��� ������ ������,
           ���� ���� �����-������ �� ���� ����� ����� �� ���� ������, �� ������� �������� ��������� */
            if(C1 == C2){
                if((x3 >= min(x1, x2) && x3 <= max(x1, x2)) || (x4 >= min(x1, x2) && x4 <= max(x1, x2))) {
                    System.out.println("Segments are same");  //������� ��������� (��������)
                }
                else{
                    System.out.println("Segments do not intersect");  //������� �� ���������
                }
            }
            else{
                System.out.println("Segments do not intersect");  //������� �����������
            }
            return;
        }
// ������ ������� ���������� ������� �������
    // ������� ������������
        double d = det(-s1y, s1x, -s2y, s2x);
        double dX = det(s1x, C1, s2x, C2);
        double dY = det(-s1y, C1, -s2y, C2);
    // ������� x � y
        double x = dX / d;
        double y = dY / d;
// ����� ������ (���� ������� ����� �����������)
        if((x >= min(x1, x2) && x <= max(x1, x2)) && (x >= min(x3, x4) && x <= max(x3, x4))) {   //��������� �������������� ����� �������
            System.out.println("Segments intersect in (" + x + ";" + y + ")"); // ����� ����������� ��������
        }
        else{
            System.out.println("Segments do not intersect");  // ������� �� ������������
        }
    }
}
