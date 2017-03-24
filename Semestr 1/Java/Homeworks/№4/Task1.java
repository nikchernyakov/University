/** Черняков Никита А3100
 *  Домашняя работа №4
 *  Задание №1
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Класс, который будет обозначать прямоугольник в таблице из 0
 * Класс содержит координаты левого верхнего и
 * правого нижнего элемента прямоугольника,
 * а также может быть подсчитана площадь этого прямоугольника
 */
class Area {
    int x1, y1;  // координаты левого вернего элемента
    int x2, y2;  // координаты правого нижнего элемента

    int calcArea(){  // вычисление площади
        return (x2 - x1 + 1)*(y2 - y1 + 1);
    }
}

public class Task1 {
    //задание таблицы
    public static int N = 200;
    public static int M = 200;
    public static int[][] table = new int[N][M];

    /**
     * Вычисление для текущей точки прямоугольника с максимальной площадью
     * @param x
     * @param y
     * Эти параметры это координаты точки
     * @return класс максимального прямоугольника
     */
    public static Area currentMaxArea(int x, int y){
        //задание прямоугольника, который в итоге будет возвращен
        Area result = new Area();
        result.x1 = x;
        result.y1 = y;
        result.x2 = x;
        result.y2 = y;
        while(result.y2 < M && table[x][result.y2] == 0)
            result.y2++;
        result.y2--;

        // вычисляем наибольший прямоугольник
        int x2 = x + 1;
        while(x2 < N && table[x2][y] == 0){
            int y2 = y;
            while(y2 < M && table[x2][y2] == 0){
                y2++;
            }
            y2--;
            if((x2 - x + 1)*(Math.min(y2,result.y2) - y + 1) > result.calcArea()){
                result.x2 = x2;
            }
            result.y2 = Math.min(y2,result.y2);
            x2++;
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException{
        /* считывание с файла
        Scanner in = new Scanner(new File(args[0]));
        in.useDelimiter("[^0-9]+");
        N = in.nextInt();
        M = in.nextInt();
        table = new int[N][M];
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                table[i][j] = in.nextInt();
            }
        }
        */

        /* генерация таблицы */
        Random rmd = new Random();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                table[i][j] = rmd.nextInt(2);
            }
        }

        /* вывод таблицы
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++)
                System.out.print(table[i][j] + " ");
            System.out.println();
        }
        */
        // просмотр всех значений из таблицы table с значением 0
        Area maxValue = new Area();
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++){
                Area curValue = new Area();
                if(table[i][j] == 0){
                    curValue = currentMaxArea(i, j);
                }

                if(curValue.calcArea() > maxValue.calcArea()){
                    maxValue = curValue;
                }
            }

        //вывод ответа
        System.out.println("Координаты левой верней точки прямоугольника:\n" + maxValue.x1 + " " + maxValue.y1);
        System.out.println("Координаты правой нижней точки прямоугольника:\n" + maxValue.x2 + " " + maxValue.y2);
    }
}
