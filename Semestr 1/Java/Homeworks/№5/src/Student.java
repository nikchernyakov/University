import java.util.ArrayList;

public class Student {
    public int ind;
    public String group;
    public String secondName;
    public String firstName;
    public ArrayList<Mark> marks = new ArrayList<>();

    public Student(int ind) {
        this.ind = ind;
    }

    //добавление оценки
    public void addMark(Mark m){
        marks.add(m);
    }

    /*конструктор по идентификатору
    public Student(int ind, String secondName, String firstName){
        this.ind = ind;
        this.secondName = secondName;
        this.firstName = firstName;
    }
    */

    //конструктор по группе
    public Student(String group, String secondName, String firstName){
        this.group = group;
        this.secondName = secondName;
        this.firstName = firstName;
    }

    //удаление оценки
    public void removeMark(Mark m){
        for(int i = 0; i < marks.size(); i++){
            Mark curMark = marks.get(i);

            if(curMark.mark == m.mark
               && curMark.subject.equals(m.subject)){
                marks.remove(i);
                return;
            }
        }

        System.out.println("ОШИБКА: Оценка не найдена");
    }

    public void outStudent(){
        System.out.println(firstName + " " + secondName + " " + group);
    }

    public double average(){
        double sum = 0;
        for (Mark m : marks){
            sum += m.mark;
        }
        return sum/marks.size();
    }

    public void setInd(int ind) {
        this.ind = ind;
    }
}
