/**
 * Домашняя работа по алгоритмам и сд №2
 * Задание 1
 * Черняков Никита А3101
 *
 */

import java.util.ArrayList;
import java.util.List;

class Tree<K extends Comparable<K>, V>{
    private class Node {
        K key;
        V value;
        Node less = null;
        Node more = null;
        Node(K key, V value) { this.key = key; this.value = value; }
        Node(K key) { this(key, null); }
    }
    Node root;

    Tree(){
        root = null;
    }

    /**
     * Добавление узла в дерево
     * Запускается от корня
     * @param key
     * @param value
     */
    public void add(K key, V value){
        if(root == null){
            root = new Node(key, value);
        }
        else {
            add(key, value, root);
        }
    }

    /**
     * Добавление узла в дерево
     * Проверяет в какое поддерево нужно класть узел и кладет
     * @param key
     * @param value
     * @param parent
     */
    private void add(K key, V value, Node parent){
        if(key.compareTo(parent.key) > 0){
            if(parent.more == null){
                parent.more = new Node(key, value);
            }
            else {
                add(key, value, parent.more);
            }
        }
        else {
            if(parent.less == null){
                parent.less = new Node(key, value);
            }
            else {
                add(key, value, parent.less);
            }
        }
    }

    /**
     * Объединяет два списка
     * @param list1
     * @param list2
     * @return Общий список
     */
    public List<V> union(List<V> list1, List<V> list2){
        List<V> list = new ArrayList<>();
        for(V iter : list1){
            list.add(iter);
        }
        for(V iter : list2){
            list.add(iter);
        }
        return list;
    }

    /**
     * Именно с этого метода начинается поиск элементов с нужными ключами в дереве
     * @param start
     * @param finish
     * @return Список нужных значений
     */
    public List<V> get(K start, K finish){
        List<V> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        // Если нужно работать только с левым поддеревом
        if(root.key.compareTo(finish) > 0){
            return get(start, finish, root.less);
        }
        // Если нужно работать только с правым поддеревом
        if(root.key.compareTo(start) < 0){
            return get(start, finish, root.more);
        }
        else {
            return get(start, finish, root);
        }
    }

    private List<V> get(K start, K finish, Node next){
        List<V> result = new ArrayList<>();

        if(next == null){
            return result;
        }

        // Если нужно работать только с левым поддеревом
        if(next.key.compareTo(finish) > 0){
            return get(start, finish, next.less);
        }
        // Если нужно работать только с правым поддеревом
        if(next.key.compareTo(start) < 0){
            return get(start, finish, next.more);
        }

        result = union(getLess(start, next.less), getMore(finish, next.more));
        if(next.key.compareTo(finish) <= 0 && next.key.compareTo(start) >= 0) {
            result.add(next.value);
        }
        return result;
    }

    /**
     *  Этот метод работает с левым поддеревом, зная что родитель этого дерева входит в отрезок нужных ключей
     *  Если узел в этом дереве входит в отрезок нужных ключей, то и все правое поддерево тоже входит в него
     *  Если узел не входит, то нужно начать проверять по таким же критериям правое поддерево
     * @param border
     * @param next
     * @return
     */
    private List<V> getLess(K border, Node next){
        List<V> result = new ArrayList<>();
        if(next == null){
            return result;
        }
        if(next.key.compareTo(border) >= 0){
            result = union(getLess(border,next.less), getAll(next.more));
            result.add(next.value);
        }
        else {
            result = union(result, getLess(border, next.more));
        }
        return result;
    }


    /**
     *  Метод аналогичен методу getLess
     *  Работает с правым поддеревом
     * @param border
     * @param next
     * @return
     */
    private List<V> getMore(K border, Node next){
        List<V> result = new ArrayList<>();
        if(next == null){
            return result;
        }
        if(next.key.compareTo(border) <= 0){
            result = union(getMore(border,next.more), getAll(next.less));
            result.add(next.value);
        }
        else {
            result = union(result, getMore(border, next.less));
        }
        return result;
    }

    /**
     * Записывает все значения узлов в список
     * @param tree
     * @return
     */
    private List<V> getAll(Node tree){
        List<V> result = new ArrayList<>();
        if(tree == null){
            return result;
        }
        result = union(getAll(tree.less), getAll(tree.more));
        result.add(tree.value);
        return result;
    }
}