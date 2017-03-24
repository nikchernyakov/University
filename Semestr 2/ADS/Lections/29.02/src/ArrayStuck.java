import java.util.ArrayList;

public class ArrayStuck<E> implements Stuck<E> {

    /*final private E[] stuck; //�1
    public ArrayStuck(int n){
        //stuck = new E[n]; //������������� �������, ���� �����, ���� �� � � �����������
        //stuck = new Object[n]; // ������������� �������, �� ����, ��� ���������� � ����� � ������ �1
        stuck = (E[])(new Object[n]); //������������� �����, �� ���� ����� ���� ������...
        //������ ������ �� ��������������� ���� ����� �������� �����
        //ArrayList<E> ��� �������
    }*/
    final private Object[] stuck; //�1
    private int top = 0;
    public ArrayStuck(int n){
        stuck = new Object[n];
    }

    public void push(E e) {
        stuck[top++] = e;
    }

    public E pop() {
        return (E)stuck[--top];
    }

    public boolean isEmpty() {
        return false;
    }
}
