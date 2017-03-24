/** Черняков Никита
 *  А3100
 *  Домашняя работа №3
 *  Задание 2
 *
 *  Сгенерировать и вывести случайную последовательность из
 *  N=1000 натуральных чисел и найти в ней самую длинную неубывающую последовательность чисел.
 *  Программа должна иметь N в качестве аргумента программы.
 */

import java.util.ArrayList;
import java.util.Random;

public class Task2 {

    public static void main(String[] args){
        //объявление данных
        int N = 1000;
        Random rmd = new Random();
        int[] mas = new int[N];

        //генерация последовательности
        for(int i = 0; i < N; i++){
            mas[i] = rmd.nextInt();
            mas[i] = Math.abs(mas[i]);
            mas[i]++;
        }

        //вычисление самой длинной неубывающей последовательности
        ArrayList<Integer> maxSequence = new ArrayList<>();     //максимальная из найденных неубывающая последовательность
        ArrayList<Integer> currentSequence = new ArrayList<>(); //текущая неубывающей последовательности
        currentSequence.add(mas[0]);

        for(int i = 1; i < N; i++){
            if(mas[i - 1] <= mas[i]){
                currentSequence.add(mas[i]);
            }
            else{
                if(currentSequence.size() > maxSequence.size()) {
                    maxSequence.clear();
                    for(int k = 0; k < currentSequence.size(); k++)
                        maxSequence.add(currentSequence.get(k));
                }
                currentSequence.clear();
                currentSequence.add(mas[i]);
            }
        }
        if(currentSequence.size() > maxSequence.size()) {
            maxSequence.clear();
            for(int i = 0; i < currentSequence.size(); i++)
                maxSequence.add(currentSequence.get(i));
        }

        //вывод последовательности, которую мы сгенерировали
        System.out.println("Сгенерированная последовательность:");
        for(int i = 0; i < N; i++) {
            System.out.print(mas[i] + " ");
        }
        System.out.println("\nМаксимальная неубывающая последовательность:");
        //вывод максимальной неубывающей последовательности
        for(int i = 0; i < maxSequence.size(); i++){
            System.out.print(maxSequence.get(i) + " ");
        }
    }
}
