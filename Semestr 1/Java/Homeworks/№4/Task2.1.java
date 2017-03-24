import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Task2 {
    public static ArrayList<ArrayList<ArrayList<Integer>>> dinMas = new ArrayList<>();
    public static int[][] din = new int[210][210];

    /**
     * Считаем длину пути
     * @param path Путь длину которого мы считаем
     * @return длина пути
     */
    public static int sumPath(ArrayList<Integer> path){
        int sum = 0;
        for(Integer i : path){
            sum += i;
        }
        return sum;
    }

    /**
     * Обход в глубину
     * @param mas
     * @param numOfString
     * @param position
     * @return
     */
    public static ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> mas, int numOfString, int position){
        //создание начальных данных
        ArrayList<Integer> str = mas.get(numOfString);
        ArrayList<Integer> answer = new ArrayList<>();
        ArrayList<ArrayList<Integer>> dinCopy = dinMas.get(numOfString);
        answer.add(str.get(position));

        //якорь
        if(numOfString == mas.size() - 1) {
            dinCopy.addAll(dinMas.get(numOfString));
            dinCopy.set(position, answer);
            dinMas.set(numOfString, dinCopy);
            din[numOfString][position] = str.get(position);
            return answer;
        }

        //динамические данные
        ArrayList<ArrayList<Integer>> dinStr = dinMas.get(numOfString + 1);
        ArrayList<Integer> dinPath1 = dinStr.get(position);
        ArrayList<Integer> dinPath2 = dinStr.get(position + 1);

        ArrayList<Integer> path1 = new ArrayList<>();
        ArrayList<Integer> path2 = new ArrayList<>();

        //определение двух вариантов путей
        if(din[numOfString + 1][position] != -1){
            path1.addAll(dinPath1);
        }
        else {
            path1.addAll(dfs(mas, numOfString + 1, position));
        }

        if(din[numOfString + 1][position + 1] != -1){
            path2.addAll(dinPath2);
        }
        else {
            path2.addAll(dfs(mas, numOfString + 1, position + 1));
        }

        int lengthPath1 = sumPath(path1);
        int lengthPath2 = sumPath(path2);
        if(lengthPath1 < lengthPath2) {
            answer.addAll(path1);
            din[numOfString][position] = lengthPath1 + str.get(position);
        }
        else {
            answer.addAll(path2);
            din[numOfString][position] = lengthPath1 + str.get(position);
        }

        dinCopy.addAll(dinMas.get(numOfString));
        dinCopy.set(position, answer);
        dinMas.set(numOfString, dinCopy);
        return answer;
    }

    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args)throws FileNotFoundException{
        //Scanner in = new Scanner(new File(args[0]));
        ArrayList<ArrayList<Integer>> mas = new ArrayList<>();
        int N = 20;
        Random rmd = new Random();
        for(int i = 0; i < N; i++){
            ArrayList<Integer> str = new ArrayList<>();
            ArrayList<ArrayList<Integer>> dinStr = new ArrayList<>();
            ArrayList<Integer> dinPath = new ArrayList<>();

            for(int j = 0; j <= i; j++){
                str.add(rmd.nextInt(200));
                din[i][j] = -1;
                dinStr.add(dinPath);
            }

            dinMas.add(dinStr);
            mas.add(str);
        }
        /*while(in.hasNext()){
            N++;
            ArrayList<Integer> str = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                str.add(in.nextInt());
            }
            mas.add(str);
        }*/
        /**
         * вывод пирамиды

        for(ArrayList<Integer> e : mas){
            for(Integer i : e){
                System.out.print(i + " ");
            }
            System.out.println();
        }
        */
        ArrayList<Integer> result = dfs(mas, 0, 0);

        for(Integer i : result){
            System.out.print(i + " ");
        }
    }
}
