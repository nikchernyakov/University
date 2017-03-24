
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


    /**
     * Метод для вывода всего бора
     */
    public void getWords(){
        getWords(roots,1);
    }

    /**
     * Метод для вывода всего бора, который работает внутри класса
     * @param tails Хвосты родителя
     * @param level Уровень бора
     */
    private void getWords(LinkedList<Node> tails, int level){
        for(Node it : tails){
            System.out.println(it.getSymbol() + " " + level);
            getWords(it.tails, level + 1);
            //System.out.println();
        }

    }

    /**
     * Поиск слова в боре для поиска извне
     */
    public boolean hasWord(String word){
        return hasWord(word + "0", roots);
    }

    /**
     * Поиск суффикса слова в боре
     */
    private boolean hasWord(String word, LinkedList<Node> tails){
        for(Node it : tails){
            if(word.charAt(0) == it.symbol){    //здесь можно поменять для Ватутина
                return word.length() <= 1 || hasWord(word.substring(1), it.tails);
            }
        }
        return false;
    }

    /**
     * Добавление слова в бор
     */
    public boolean addWord(String word){
        if(hasWord(word)){
            return false;
        }
        roots = addWord(word + "0", roots);
        size++;
        return true;
    }

    private LinkedList<Node> addWord(String word, LinkedList<Node> tails){
        char symbol = word.charAt(0);
        if(symbol == '0'){
            tails.add(new Node(symbol));
            return tails;
        }

        for(Node it : tails){
            if(it.symbol == symbol){
                it.tails = addWord(word.substring(1), it.tails);
                return tails;
            }
        }

        tails.add(new Node(symbol));
        /*  Можно так, но мне так не нравится
            Еще раз тоже самое делаю
        for(Node it : tails){
            if(it.symbol == symbol){
                it.tails = addWord(word.substring(1), it.tails);
            }
        }
        */
        Node last = tails.getLast();
        last.tails = addWord(word.substring(1), tails.getLast().tails);
        tails.set(tails.size()-1, last);
        return tails;
    }

    /**
     * Удаление слова из бора
     */
    boolean removeWord(String word){
        if(!hasWord(word)){
            return false;
        }
        roots = removeWord(word + "0", roots);
        size--;
        return true;
    }

    /**
     * Удаление части слова из бора
     */
    private LinkedList<Node> removeWord(String word, LinkedList<Node> tails){
        char symbol = word.charAt(0);
        for(Node it : tails){
            if(it.symbol == symbol){
                if(symbol!='0'){
                    it.tails = removeWord(word.substring(1), it.tails);
                }
                if(it.tails.isEmpty()){
                    tails.remove(it);
                    return tails;
                }

            }
        }
        return tails;
    }

    /**
     * Количество слов в боре
     */
    public int size(){
        return size;
    }

}
