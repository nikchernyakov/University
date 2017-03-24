/*
    Черняков Никита А3101
    Спец. семинар
    Домашняя работа №1 Задание 2
 */

import java.util.Iterator;

public class Main {
    static <E> boolean listsEqual(CycList<E> list1, CycList<E> list2) {
        Iterator<E> it1 = list1.iterator();
        Iterator<E> it2 = list2.iterator();
        while(it1.hasNext() && it2.hasNext()){
            it1.next();
            it2.next();
        }
        if(it1.hasNext() != it2.hasNext()){
            return false;
        }

        Iterator<E> iter1 = list1.iterator();
        while(iter1.hasNext()){
            Iterator<E> iterator1 = list1.iterator();
            Iterator<E> iterator2 = list2.iterator();
            boolean flag = true;
            while(iterator1.hasNext()){
                if(!(iterator1.next()).equals(iterator2.next())){
                    flag = false;
                    break;
                }
            }
            if(flag) { return true; }
            list2.shift(1);
            iter1.next();
        }
        return false;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new List<>();
        List<Integer> list2 = new List<>();
        for(int i = 1; i <= 5; i++){
            list1.add(i);
            list2.add(i);
        }
        list1.shift(4);
        if(listsEqual(list1, list2)){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
    }
}
