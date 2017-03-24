/**
 * Домашняя работа №6
 *  Черняков Никита А3100
 */

public class Task {
    public static void main(String[] args) {
        Colony myColony = new Colony(
                new boolean[][] {
                        {true, true, true},
                        {false, true},
                        {true, true, true}} );
                // очень забавный тест, выводит интересные картинки
                /*new boolean[][]{
                    {false, false, true, false, false},
                    {true, true, false, true, true},
                    {true, true, false, true, true}}); */

        System.out.println("Начальное состояние колонии:");
        myColony.print(System.out);
        for (int i = 1; i <= 100 && !myColony.isEmpty(); i++) {
            myColony.step();
            System.out.format("Колония через %d шагов:\n", (i));
            myColony.print(System.out);
        }
        if (myColony.isEmpty()) {
            System.out.println("Колония мертва!");
        }
    }
}