import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListDeque<E> implements Deque<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E elem;
        Node<E> prev;
        Node<E> next;

        Node(E elem, Node<E> prev, Node<E> next) {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void addFirst(E elem) {
        if (size == 0) {
            first = last = new Node<>(elem, null, null);
        } else {
            first = new Node<>(elem, null, first);
            first.next.prev = first;
        }
        size++;
    }

    @Override
    public E first() {
        return first.elem;
    }

    @Override
    public E removeFirst() {
        E value = first();
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return value;
    }

    @Override
    public void addLast(E elem) {
        if (size == 0) {
            first = new Node<>(elem, null, null);
            last = first;
        } else {
            last = new Node<>(elem, last, null);
            last.prev.next = last;
        }

        size++;
    }

    @Override
    public E last() {
        if (size < 1) {
            throw new NoSuchElementException("Попытка взятия элемента из пустой очереди");
        }
        return last.elem;
    }

    @Override
    public E removeLast() {
        E res;
        try {
            res = last();
        } catch (NoSuchElementException exc) {
            throw new IllegalStateException("Попытка удаления элемента из пустой очереди", exc);
        }

        if (size == 1) {
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
        }

        size--;
        return res;
    }

    private class Itr implements Iterator<E> {

        Node<E> ptr;
        Node<E> prevPtr;

        Itr(Node<E> ptr) {
            this.ptr = ptr;
        }

        @Override
        public boolean hasNext() {
            return ptr != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Попытка взятия следующего элемента итератором после достижения конца очереди");
            }

            prevPtr = ptr;
            ptr = ptr.next;
            return prevPtr.elem;
        }

        @Override
        public void remove() {
            if (prevPtr == null) {
                throw new IllegalStateException("Попытка некорректного удаления элемента итератором очереди");
            }

            if (prevPtr != first) {
                prevPtr.prev.next = prevPtr.next;
            }
            if (prevPtr != last) {
                prevPtr.next.prev = prevPtr.prev;
            }

            prevPtr = null;
            size--;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr(first);
    }

    public int size() {
        return size;
    }
}
