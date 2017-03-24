import java.util.ArrayList;

public class ArrayStuck<E> implements Stuck<E> {

    /*final private E[] stuck; //№1
    public ArrayStuck(int n){
        //stuck = new E[n]; //синтаксически неверно, хрен знает, есть ли у Е конструктор
        //stuck = new Object[n]; // синтаксически неверно, не факт, что совместимо с типом в строке №1
        stuck = (E[])(new Object[n]); //синтаксически верно, но тоже могут быть ошибки...
        //короче массив из неопределенного типа очень стремная хрень
        //ArrayList<E> без проблем
    }*/
    final private Object[] stuck; //№1
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
