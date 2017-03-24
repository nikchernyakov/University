
import java.util.LinkedList;

public class Dictionary {
    private static class Node {
        char symbol;
        LinkedList<Node> tails;
        Node(char symbol){
            this.symbol = symbol;
            this.tails = new LinkedList<>();
        }
        char getSymbol(){
            return symbol;
        }
    }
    private LinkedList<Node> roots = new LinkedList<>();
    int size = 0;

    // Количество слов
    int size(){
        return size;
    }

    // Наличие слова в дереве
    private boolean hasWord(String word, LinkedList<Node> list){
        for(Node it : list){
            if(word.charAt(0) == it.symbol){
                if(word.length() == 1 || hasWord(word.substring(1), it.tails))
                    return true;
            }
        }
        return false;
    }

    boolean hasWord(String word){
        return hasWord(word + "0", roots);
    }

    private LinkedList<Node> addWord(String word, LinkedList<Node> list){
        if(word.charAt(0) == '0'){
            list.add(new Node(word.charAt(0)));
            return list;
        }
        boolean flag = false;
        for(Node it : list){
            if(it.symbol == word.charAt(0)){
                flag = true;
            }
        }
        if(!flag)list.add(new Node(word.charAt(0)));
        for(Node it : list){
            if(it.symbol == word.charAt(0)){
                it.tails = addWord(word.substring(1), it.tails);
            }
        }
        return list;
    }

    // Добавление слова
    boolean addWord(String word){
        if(hasWord(word)){
            return false;
        }
        size++;
        roots = addWord(word + "0", roots);
        return true;
    }

    private LinkedList<Node> removeWord(String word, LinkedList<Node> list){
        for(Node it : list){
            if(it.symbol == word.charAt(0)){
                if(word.charAt(0) == '0'){
                    list.remove(it);
                }
                else{
                    it.tails = removeWord(word.substring(1), it.tails);
                    if(it.tails.isEmpty()) {
                        list.remove(it);
                        return list;
                    }
                }

            }
        }
        return list;
    }

    // Удаление слова
    boolean removeWord(String word){
        if(!hasWord(word)){
            return false;
        }
        size--;
        roots = removeWord(word + "0", roots);
        return true;
    }
}
