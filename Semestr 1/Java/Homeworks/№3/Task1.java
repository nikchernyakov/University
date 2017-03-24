/**�������� ������
 * �3100
 * �������� ������ �3
 * ������� �1
 *
 * ��������� � ���������� �������,
 * � ������� ��� ������ ����� (� ������ ��������) ��������� ����� ��������� ���� ���������� �����,
 * ������������ � ���� �����. �����, � ������� �� ���������� �� ���� �����, � ������� ������� �� ������.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task1 {
    /**
     * ��������� ������ ����� �� �����, ����� � ������� getLetter ����� �� ���� ������ ����� � ������ ��������
     * ����� ��������� �������� � table ��� ����� letter
     * � ����� ������� map � ���� �������
     */
    public static void main(String[] args) throws FileNotFoundException{
        //���������� � ���������� ������
        if(args.length == 0){
            System.out.println("��� ����� �� ������ � ����������");
            return;
        }
        Scanner in = new Scanner(new File(args[0]));
        in.useDelimiter("[^a-zA-Z�-��-߸�0-9]+");
        Map<Character, Integer> table = new HashMap<>();

        //���������� map table
        while(in.hasNext()){
            String letter = in.next();
            letter = letter.toLowerCase();
            if(table.containsKey(letter.charAt(0))) {
                table.put(letter.charAt(0), table.get(letter) + 1);
            }
            else{
                table.put(letter.charAt(0), 1);
            }
        }

        //����� �������
        System.out.println("������� ����:");
        for(Map.Entry entry : table.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}