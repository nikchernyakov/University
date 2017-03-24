package hash;

// Массив списка объектов
public class Dictionary<V> {

    // Узел списка
    private static class Node<V>{
        String key;
        V value;
        Node<V> next;
        Node(String key, V value){
            this.key = key;
            this.value = value;
        }
        Node(String key, V value, Node<V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    final static double rate = 0.75;  // Коэффициент отношения количества слов к длине массива
    Node<V>[] hasTable= new Node[10];

    V get(String word){

    }
    void put(String word, V value){

    }

    // Перехэширование
    void rehash(){

    }

}
