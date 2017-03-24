import java.util.ArrayList;
import java.util.List;

class Tree<K extends Comparable<K>, V>{
    private class Node {
        K key;
        V value;
        Node less = null;
        Node more = null;
        Node(K key, V value) { this.key = key; this.value = value; }
    }
    Node root = null;

    // Добавление нового узла для использования извне
    public void add(K key, V value){
        if(root == null){
            root = new Node(key, value);
        }
        else {
            add(key, value, root);
        }
    }

    // Добавление нового узла для класса
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

    // Метод, который требуется написать по условию задачи
    public List<V> get(K start, K finish){
        List<V> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        return get(start, finish, root, result);
    }

    // Получение нужных значений
    private List<V> get(K start, K finish, Node next, List<V> result){
        if(next == null){
            return result;
        }

        if(next.key.compareTo(finish) > 0){
            return get(start, finish, next.less, result);
        }

        if(next.key.compareTo(start) < 0){
            return get(start, finish, next.more, result);
        }

        if(next.key.compareTo(finish) <= 0 && next.key.compareTo(start) >= 0) {
            result.add(next.value);
        }
        return get(start, finish, next.less, get(start, finish, next.more, result));
    }



}