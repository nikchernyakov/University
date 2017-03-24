/**Черняков Никита
 * А3100
 * Домашняя работа №3
 * Задание №1
 *
 * Составить и напечатать таблицу,
 * в которой для каждой буквы (в нижнем регистре) выводится число различных слов текстового файла,
 * начинающихся с этой буквы. Буквы, с которых не начинается ни одно слово, в таблицу попасть не должны.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task1 {
    /**
     * Считываем каждое слово из файла, затем с помощью getLetter берем из него первую букву в нижнем регистре
     * Затем обновляем значения в table для ключа letter
     * В конце выводим map в виде таблицы
     */
    public static void main(String[] args) throws FileNotFoundException{
        //считывание и объявление данных
        if(args.length == 0){
            System.out.println("Имя файла не задано в параметрах");
            return;
        }
        Scanner in = new Scanner(new File(args[0]));
        in.useDelimiter("[^a-zA-Zа-яА-ЯёЁ0-9]+");
        Map<Character, Integer> table = new HashMap<>();

        //заполнение map table
        while(in.hasNext()){
            String letter = in.next();
            letter = letter.toLowerCase();
            if(table.containsKey(letter.charAt(0))) {
                table.put(letter.charAt(0), table.get(letter) + 1);
            }
            else{
                table.put(letter.charAt(0), 1);
            }
        }

        //вывод таблицы
        System.out.println("Таблица букв:");
        for(Map.Entry entry : table.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}