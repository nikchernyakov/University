public class Student/* implements Comparable<Student> */{
    private String name;
    private double average;

    public Student(String name, double average){
        this.name = name;
        this.average = average;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("%s - %g", name, average);
    }

    public double getAverage(){
        return average;
    }

    public void setAverage(double average){
        this.average = average;
    }

    //он ее какк бы убрал, а я как бы закомментил
    //все это потому что у нас компаратор появился
    /*
    @Override
    public int compareTo(Student o) {
        //return average < o.average ? 1 : average == o.average ? 0 : -1; // сортировка по оценке
        return name.compareTo(o.name); // сортировка по имени
    }
    */
}
