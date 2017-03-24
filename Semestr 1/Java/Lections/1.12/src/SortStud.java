import java.util.Arrays;
import java.util.Comparator;

public class SortStud {
    static Student[] array = new Student[]{
            new Student("Ivanov", 5.0),
            new Student("Petrov", 3.5),
            new Student("Sidorov", 4.2)
    };


    public static void main(String[] args) {
        class NameComparator implements Comparator<Student> {
            public int compare(Student s1, Student s2){
                return s1.getName().compareTo(s2.getName());
            }
        }
        System.out.println("Ivanov".compareTo("Petrov")); //смотрим как работает compareTo

        System.out.println(Arrays.toString(array)); //начальное состояние массива

        Arrays.sort(array, new StudentsComparator());  //по оценке
        System.out.println(Arrays.toString(array));

        Arrays.sort(array, new NameComparator());       // по имени
        System.out.println(Arrays.toString(array));

        Arrays.sort(array, new Comparator<Student>() {  // задаем компаратор прям здесь
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });

        Arrays.sort(array,  //все способы выше он удалил
                (s1,s2) -> s1.getName().compareTo(s2.getName())); //новый способ сортировки
        // -> взята из функционального программирования отделяет аргументы
        System.out.println(Arrays.toString(array));


    }
}
