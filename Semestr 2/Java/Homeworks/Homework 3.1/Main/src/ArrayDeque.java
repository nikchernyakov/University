import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Deque<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private Object[] array;
    private int first;
    private int last;
    private int size;

    public ArrayDeque(int initCapacity) {
        initialize(initCapacity);
    }

    public ArrayDeque() {
        initialize(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Инициализирует внутренний массив указанной вместимости.
     * @throws IllegalArgumentException попытка инициализировать массив неположительного размера.
     */
    private void initialize(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Попытка инициализировать внутренний массив очереди неположительной величиной");
        }
        array = new Object[capacity];
        first = last = capacity / 2;
    }

    /**
     * Расширяет внутренний массив вдвое, располагая имеющиеся элементы посередине.
     */
    private void expand() {
        Object[] newArray = new Object[array.length * 2];
        int curr = (newArray.length - size) / 2;
        int newFirst = curr;

        for (int i = 0; i < size; i++) {
            newArray[curr++] = array[(first + i) % array.length];
        }

        first = newFirst;
        last = size == 0 ? first : first + size - 1;
        array = newArray;
    }

    @Override
    public void addFirst(E elem) {
        if (size == array.length) {
            expand();
        }

        if (size != 0) {
            first = (first - 1) % array.length;
        }
        array[first] = elem;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E first() {
        if (size < 1) {
            throw new NoSuchElementException("Попытка взятия элемента из пустой очереди");
        }
        return (E) array[first];
    }

    @Override
    public E removeFirst() {
        E res;
        try {
            res = first();
        } catch (NoSuchElementException exc) {
            throw new IllegalStateException("Попытка удаления элемента из пустой очереди", exc);
        }

        array[first] = null;
        if (size != 0) {
            first = (first + 1) % array.length;
        }
        size--;

        return res;
    }

    @Override
    public void addLast(E elem) {
        if (size == array.length) {
            expand();
        }

        if (size != 0) {
            last = (last + 1) % array.length;
        }
        array[last] = elem;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E last() {
        if (size < 1) {
            throw new NoSuchElementException("Попытка взятия элемента из пустой очереди");
        }
        return (E) array[last];
    }

    @Override
    public E removeLast() {
        E res;
        try {
            res = last();
        } catch (NoSuchElementException exc) {
            throw new IllegalStateException("Попытка удаления элемента из пустой очереди", exc);
        }

        array[last] = null;
        if (size != 0) {
            last = (last - 1) % array.length;
        }
        size--;

        return res;
    }

    /**
     * Возвращает true, если в массиве по указанному индексу pos находится действующий элемент очереди,
     * и false в ином случае.
     */
    private boolean ensureBounds(int pos) {
        return  !(  size == 0 ||
                    last < first && pos > last && pos < first ||
                    last > first && (pos > last || pos < first)  );
    }

    private class Itr implements Iterator<E> {

        int curr;
        int prev = -1;

        Itr(int pos) {
            if (!ensureBounds(pos)) {
                throw new IllegalArgumentException("Попытка создать некорректный итератор очереди");
            }

            curr = pos;
        }

        @Override
        public boolean hasNext() {
            return ensureBounds(curr);
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Попытка взятия следующего элемента итератором после достижения конца очереди");
            }
            E res = (E) array[curr];
            prev = curr;
            curr = (curr + 1) % array.length;

            return res;
        }

        @Override
        public void remove() {
            if (prev == -1) {
                throw new IllegalStateException("Попытка некорректного удаления элемента итератором очереди");
            }

            int next = (prev + 1) % array.length;
            while (prev != last) {
                array[prev] = array[next];
                prev = next;
                next = (next + 1) % array.length;
            }

            prev = -1;
            curr = (curr - 1) % array.length;
            size--;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr(first);
    }

//    public int size() {
//        return size;
//    }
}
