/* �������� ������ �3100
   �������� ������ �1 
	
	������� ���� ����� BA � BC ������� ����� ��������� ������������ ���� ������ 
        (����  ���������� ������ A,B,C ������ ���������� �������� BA � BC, � ����� �� �����)
*/
import static java.lang.Math.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        final int x;
        x = 1;
        x = 2;
        Scanner scanner= new Scanner(System.in);
        double x1 = scanner.nextDouble();  //���������� x ����� A
        double y1 = scanner.nextDouble();  //���������� y ����� A
        double x2 = scanner.nextDouble();  //���������� x ����� B
        double y2 = scanner.nextDouble();  //���������� y ����� B
        double x3 = scanner.nextDouble();  //���������� x ����� C
        double y3 = scanner.nextDouble();  //���������� y ����� C
        double BA1 = x1-x2; 
        double BA2 = y1-y2;
        double BC1 = x3-x2;
        double BC2 = y3-y2;
        System.out.println(Math.cos((BA1*BC1+BA2*BC2)/(sqrt(BA1*BA1+BA2*BA2)*sqrt(BC1*BC1+BC2*BC2))); //�������� �� ������� ���������� ������������ �������� ������� ���� � ����� ��������� ����
    }
}
