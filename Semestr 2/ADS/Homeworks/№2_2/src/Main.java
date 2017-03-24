public class Main {
    public static void main(String[] args) {
        Dictionary words = new Dictionary();
        System.out.println(words.hasWord("bot"));
        System.out.println(words.addWord("byte"));
        System.out.println(words.addWord("bite"));
        System.out.println(words.addWord("bit"));
        System.out.println(words.addWord("site"));
        System.out.println(words.addWord("sit"));
        System.out.println(words.hasWord("site"));
        System.out.println(words.hasWord("bot"));
        System.out.println(words.removeWord("bot"));
        System.out.println(words.removeWord("sit"));
        System.out.println(words.removeWord("byte"));
        words.getWords();
        System.out.println(words.size());
    }
}
