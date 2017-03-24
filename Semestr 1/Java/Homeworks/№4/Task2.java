/** Черняков Никита А3100
 *  Домашняя работа №4
 *  Задание №2
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Task2 {
    public static String[][] dinMas = new String[210][210]; // динамический массив, куда будем записывать весь
                                                            // кратчайший путь, которым можно пройти пирамиду из этой вершины

    public static int[][] din = new int[210][210];  // динамический массив, в котором мы будем хранить длину кратчайшего
                                                    // пути из данного значения пирамиды

    public static ArrayList<ArrayList<Integer>> mas = new ArrayList<>();  // сама пирамида

    /**
     * Считаем длину пути из строки
     * @param path Строка, в которой указан путь, длину которого мы считаем (путь в строке задан вершинами через .)
     * @return длина пути
     */
    public static int sumPath(String path){
        int sum = 0;
        Scanner str = new Scanner(path);
        str.useDelimiter("[.]+");
        while(str.hasNext()) {
            sum += Integer.parseInt(str.next());
        }
        return sum;
    }

    /**
     * Обход в глубину DFS
     *
     * Добавляем нашу текущую вершину в путь, а затем запускаем DFS снова
     * от значений снизу-слева, снизу-справа в пирамиде
     *
     * @param numOfString номер строки, в которой находится текущее значение пирамиды
     * @param position позиция значения в строке
     * @return возвращаем кратчайший путь в виде строки
     */
    public static String dfs(int numOfString, int position){
        //создание начальных данных
        ArrayList<Integer> str = mas.get(numOfString);
        String answer = String.valueOf(str.get(position)) + ".";

        //якорь(база рекурсии)
        if(numOfString == mas.size() - 1) {
            dinMas[numOfString][position] = answer;
            din[numOfString][position] = str.get(position);
            return answer;
        }

        //динамические данные
        String dinPath1 = dinMas[numOfString + 1][position];
        String dinPath2 = dinMas[numOfString + 1][position + 1];

        String path1 = new String();
        String path2 = new String();

        //определение двух вариантов путей
        if(din[numOfString + 1][position] != -1){
            path1 = dinPath1;
        }
        else {
            path1 = dfs(numOfString + 1, position);
        }
        if(din[numOfString + 1][position + 1] != -1){
            path2 = dinPath2;
        }
        else {
            path2 = dfs(numOfString + 1, position + 1);
        }

        //выбор кратчайшего пути из path1 и path2
        int lengthPath1 = sumPath(path1);
        int lengthPath2 = sumPath(path2);

        if(lengthPath1 < lengthPath2) {
            answer += path1;
            din[numOfString][position] = lengthPath1 + str.get(position);
        }
        else {
            answer += path2;
            din[numOfString][position] = lengthPath1 + str.get(position);
        }

        dinMas[numOfString][position] = answer; //запоминаем ответ
        return answer;
    }

    public static void main(String[] args)throws FileNotFoundException{
        //Scanner in = new Scanner(new File(args[0]));  это нужно если хотим считывать с файла

        //Генерация пирамиды
        int N = 200;
        Random rmd = new Random();
        for(int i = 0; i < N; i++){
            ArrayList<Integer> str = new ArrayList<>();
            for(int j = 0; j <= i; j++){
                str.add(rmd.nextInt(200));
                din[i][j] = -1;
            }
            mas.add(str);
        }

        /* счиьывание пирамиды из файла
        while(in.hasNext()){
            N++;
            ArrayList<Integer> str = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                str.add(in.nextInt());
            }
            mas.add(str);
        }*/

        /* вывод пирамиды, если нужно посмотреть, какая пирамида сгенерировалась
        for(ArrayList<Integer> e : mas){
            for(Integer i : e){
                System.out.print(i + " ");
            }
            System.out.println();
        }
        */

        String result = dfs(0, 0); //считаем ответ запускаясь из 1 элемента 1 строки нашей пирамиды

        //вывод последовательности
        Scanner str = new Scanner(result);
        str.useDelimiter("[.]+");
        while(str.hasNext()) {
            System.out.print(str.next() + " ");
        }
    }
}
