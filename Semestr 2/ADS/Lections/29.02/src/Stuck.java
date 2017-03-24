/**
 * Created by Никита on 29.02.2016.
 */
public interface Stuck<E> {
    void push(E e);
    E pop();
    boolean isEmpty();
}
