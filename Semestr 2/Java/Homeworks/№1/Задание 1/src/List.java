/*
    Черняков Никита А3101
    Спец. семинар
    Домашняя работа №1 Задание 1
 */
public class List<E extends Comparable<E>>{

    public static class Node<E>{
        private E info;
        private Node<E> next;

        public Node(E info) {
            this.info = info;
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

        public boolean hasNext(){
            return next != null;
        }

    }

    Node<E> head = null;

    /**
     * Размер списка
     */
    public int size(){
        if(head == null) return 0;
        int size = 1;
        Node<E> curElem = head;
        while(curElem.hasNext()){
            size++;
            curElem = curElem.getNext();
        }
        return size;
    }

    /**
     * Добавление элемента в конец списка
     * ДЛЯ КЛАССА
     */
    private void add(Node<E> curNode, E elem){
        if(!curNode.hasNext()){
            curNode.setNext(new Node<>(elem));
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
            return;
        }
        add(head, elem);
    }

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
     * Меняет два элеента с индексами i и j в списке местами
    */
    public void swap(int i, int j) {
        if(i == j) return;
        if((i >= size() || i < 0) || (j >= size() || j < 0)){
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        if(i >= j) { int t = j; j = i; i = t;}

        Node<E> prevI = new Node<>(null);
        prevI.setNext(head);
        int ind = -1;
        while(ind < i - 1) { prevI = prevI.getNext(); ind++; }
        Node<E> nodeI = prevI.getNext();
        Node<E> prevJ = prevI;
        while(ind < j - 1) { prevJ = prevJ.getNext(); ind++; }
        Node<E> nodeJ = prevJ.getNext();
        if(i > 0) {
            prevI.setNext(nodeJ);
        }
        else{
            head = nodeJ;
        }
        prevJ.setNext(nodeI);
        Node<E> nextJ = nodeJ.getNext();
        nodeJ.setNext(nodeI.getNext());
        nodeI.setNext(nextJ);
    }

    /**
     * Сортирует список по возрастанию
    */
    public void sort() {
        int size = size();
        for(int i = 0; i < size - 1; i++){
            Node<E> node = new Node<>(null);
            node.setNext(head);

            for(int j = 0; j < size - i - 1; j++){
                Node<E> prevNode = node;
                Node<E> curNode = prevNode.getNext();
                Node<E> nextNode = curNode.getNext();

                if(curNode.getInfo().compareTo(nextNode.getInfo()) > 0){
                    prevNode.setNext(nextNode);
                    curNode.setNext(nextNode.getNext());
                    nextNode.setNext(curNode);
                }
                node = node.getNext();
            }

        }
    }
}
