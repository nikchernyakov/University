interface Deque<E> extends Iterable<E> {

    void addFirst(E elem);
    E first();
    E removeFirst();

    void addLast(E elem);
    E last();
    E removeLast();
}
