import java.util.Iterator;
import java.util.NoSuchElementException;

/*
    Черняков Никита А3101
    Спец. семинар
    Домашняя работа №1 Задание 2
 */
public class List<E extends Comparable<E>> implements CycList<E>{

    public static class Node<E>{
        private E info;
        private Node<E> next;

        public Node(E info) {
            this.info = info;
        }

        public Node(E info, Node<E> next){
            this.info = info;
            this.next = next;
        }

        public Node<E> getNext() {
            return next;
        }

        public E getInfo() {
            return info;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

    }

    Node<E> head = null;
    Node<E> tail = null;


    /**
     * Возвращает узел с индексом ind
     */
    public Node<E> getNode(int ind){
        Node<E> curElem = head;
        for(int i = 0; i < ind; i++){
            curElem = curElem.getNext();
        }
        return curElem;
    }

    /**
     * Возвращает элемент списка с индексом ind
     */
    public E getElem(int ind){
        return getNode(ind).getInfo();
    }

    /**
     * Добавление элемента в конец списка
     * ДЛЯ КЛАССА
     */
    private void add(Node<E> curNode, E elem){
        if(curNode == tail){
            curNode.setNext(new Node<>(elem,head));
            tail = curNode.getNext();
            return;
        }
        add(curNode.getNext(), elem);
    }

    /**
     * Добавление элемента в конец списка
     * ДЛЯ ПОЛЬЗОВАТЕЛЯ
     */
    public void add(E elem){
        if(head == null) {
            head = new Node<>(elem);
            head.setNext(head);
            tail = head;
            return;
        }
        add(head, elem);
    }

    public void shift(int delta){
        for(int i = 0; i < delta; i++){ shift(); }
    }

    private void shift(){
        head = tail;
        while(tail.getNext() != head){
            tail = tail.getNext();
        }
    }

    public class Itr implements Iterator<E> {
        Node<E> cursor;
        Node<E> head;
        boolean flag = false; //не было пройдено не одного элемента

        public Itr(Node<E> head){
            this.head = head;
            this.cursor = head;
        }

        @Override
        public boolean hasNext() {
            return cursor != head || !flag;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E info = cursor.getInfo();
            cursor = cursor.getNext();
            flag = true;
            return info;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr(head);
    }

}
