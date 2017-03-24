import java.util.ArrayList;

public class Subject {
    public String name;
    public ArrayList<Mark> marks = new ArrayList<>();

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, ArrayList<Mark> marks) {
        this.name = name;
        this.marks.addAll(marks);
    }

    public double average(){
        double sum = 0;
        for (Mark m : marks){
            sum += m.mark;
        }
        return sum/marks.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
