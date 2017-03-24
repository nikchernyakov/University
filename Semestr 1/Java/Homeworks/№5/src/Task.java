import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task {
    public static ArrayList<Student> allStudents = new ArrayList<>(); //список всех студентов
    public static HashMap<String, ArrayList<Integer>> groups = new HashMap<>(); //список групп
    public static ArrayList<Subject> subjects = new ArrayList<>();  //список предметов и всех оценок по ним

    /**
     * дает новый уникальный идентификатор
     */
    public static int getNewInd(){
        int ind = 1;
        while(allStudents.get(ind) != null){
            ind++;
            if(ind == allStudents.size()){
                allStudents.add(null);
                return ind;
            }
        }
        return ind;
    }

    /**
     *  находит студента во всей БД
     */
    public static int findStudent(Student student){
        for(int i = 1; i < allStudents.size(); i++){
            Student curStudent;
            curStudent = allStudents.get(i);
            if(curStudent != null
                    && curStudent.firstName.equals(student.firstName)
                        && curStudent.secondName.equals(student.secondName)
                            && curStudent.group.equals(student.group)){
                return i;
            }
        }
        return -1;
    }

    /**
     * обновляем данные о студенте в списке групп
     */
    public static void updateBase(Student student){
        ArrayList<Integer> curGroup;

        if(groups.containsKey(student.group)){      //проверяем, есть ли такая группа вообще
            curGroup = groups.get(student.group);
        }
        else {
            curGroup = new ArrayList<>();
        }

        curGroup.add(student.ind);   //добавляем студента в группу
        groups.put(student.group, curGroup);  //обновляем map
    }

    /**
     * добавляет оценку в массив предметов
     * если такого предмета еще нет, создает ее
     */
    public static void addMarkToSubjects(Mark mark){
        int ind = -1;
        //находим позицию предмета
        for(int i = 0; i < subjects.size(); i++){
            Subject curSub = subjects.get(i);

            if(curSub.name.equals(mark.subject)){
                ind = i;
                break;
            }
        }

        //если предмета не существует, добавляем его
        if(ind == -1){
            subjects.add(new Subject(mark.subject));
            ind = subjects.size() - 1;
        }

        //добавляем оценку
        Subject curSub = subjects.get(ind);
        curSub.marks.add(mark);
        subjects.set(ind, curSub);
    }

    /**
     * удаляет оценку из массива предметов
     * если оценок по этому предмету больше нет, удаляет ее
     * @param mark оценка, которую нужно удалить
     */
    public static void removeMarkFromSubject(Mark mark){
        Subject subject = new Subject("");
        int ind = -1;

        //находим предмет
        for(int i = 0; i < subjects.size(); i++){
            Subject curSub = subjects.get(i);

            if(curSub.name.equals(mark.subject)){
                subject = new Subject(curSub.name, curSub.marks);
                ind = i;
                break;
            }
        }

        //находим оценку и удаляем ее
        for(int i = 0; i < subject.marks.size(); i++){
            Mark curMark = subject.marks.get(i);

            if(curMark.ind == mark.ind
                    && curMark.mark == mark.mark){
                subject.marks.remove(i);
                break;
            }
        }

        //если предмет не имеет больше оценок, удаляем его
        if(subject.marks.isEmpty()){
            subjects.remove(ind);
        }
        else{
            subjects.set(ind, subject);
        }
    }

    /**
     * добавляем студента
     * @param ind идентификатор
     * @param student студент
     * @param mark оценка студента
     */
    public static void addMark(int ind, Student student, Mark mark) {
        if (ind == -1) {        //если нет еще такого студента
            student.addMark(mark);
            int newInd = getNewInd();
            student.ind = newInd;     //присваиваем идентификатор
            mark.ind = newInd;
            allStudents.set(newInd,student);
            updateBase(student);
        }
        else{   //если есть, просто добавляем ему оценку
            mark.ind = ind;
            Student curStudent;
            curStudent = allStudents.get(ind);
            curStudent.addMark(mark);
            allStudents.set(ind, curStudent);
        }
        addMarkToSubjects(mark);
    }

    /**
     * удаляем оценку у студента
     * если оценок после этого не остается, удаляем студента
     * @param ind идентификатор студента
     * @param mark оценка, которую нужно удалить
     */
    public static void removeMark(int ind, Mark mark){
        if(ind == -1){   //если такого студента нет
            System.out.println("ОШИБКА: Студент не найден");
        }
        else{  //если есть
            mark.ind = ind;
            removeMarkFromSubject(mark);
            Student curStudent;
            curStudent = allStudents.get(ind);
            curStudent.removeMark(mark); //удаляем оценку
            if(curStudent.marks.isEmpty()){  //проверяем на наличие оценок у студента, если нет, то мы его удаляем
                removePerson(ind);
            }
            else{
                allStudents.set(ind,curStudent);
            }
        }
    }

    /**
     * Выводит все оценки каждого студента каждой группы
     */
    public static void listAll(){
        //бежим по всем группам в map`е
        for(Map.Entry<String, ArrayList<Integer>> entry : groups.entrySet()){
            System.out.println("Группа " + entry.getKey());

            ArrayList<Integer> students = entry.getValue();
            //бежим по всем студентам группы
            for(Integer ind : students){
                Student curStudent;
                curStudent = allStudents.get(ind);
                System.out.println(ind + ")" + "\t" + curStudent.firstName + " " + curStudent.secondName);

                //бежим по всем оценкам студента
                for(Mark mark : curStudent.marks){
                    System.out.println("\t\t" + mark.subject + " " + mark.mark);
                }
            }
        }
    }

    /**
     * удаляет студента и все его оценки
     * @param ind идентификатор студента, которого нужно удалить
     */
    public static void removePerson(int ind){
        if(ind == -1){
            System.out.println("ОШИБКА: Студент не найден");
        }

        Student curStudent;
        curStudent = allStudents.get(ind);

        ArrayList<Integer> curGroup = groups.get(curStudent.group);
        curGroup.remove(curGroup.indexOf(ind)); //удаляем студента

        if(curGroup.isEmpty()){  //если группа осталась пуста, удаляем ее
            groups.remove(curStudent.group);
        }
        else
            groups.put(curStudent.group, curGroup);

        for(Mark m : curStudent.marks){
            removeMarkFromSubject(m);
        }

        allStudents.set(ind,null);
    }

    /**
     * находит студента с наименьшим средним баллом
     * @return возвращает студента
     */
    public static Student worstStudent(){
        double minAverage = 5;
        int ind = 0;

        //идем по всем студентам и ищем самого плохого
        for(int i = 1; i < allStudents.size(); i++){
            Student curStudent;
            curStudent = allStudents.get(i);
            if(curStudent != null){
                double curAverage = curStudent.average();
                if(curAverage <= minAverage){
                    minAverage = curAverage;
                    ind = i;
                }
            }
        }

        return allStudents.get(ind);
    }

    /**
     * выволит средний средний балл каждого предмета
     */
    public static void average(){
        if(subjects.isEmpty()){
            System.out.println("ОШИБКА: Нет не одного предмета в базе");
            return;
        }
        for(Subject subject : subjects){
            System.out.println(subject.name + " - " + subject.average());
        }
    }

    /**
     * выводит все оценки по предмету
     * @param name предмет, оценки которого нужно выводить
     */
    public static void marks(String name){
        //находим этот предмет
        for(int i = 0; i < subjects.size(); i++){
            Subject curSub = subjects.get(i);

            if(curSub.name.equals(name)){
                //выводим оценки по этому предмету
                for(Mark m : curSub.marks){
                    allStudents.get(m.ind).outStudent();
                    System.out.println(" - " + m.mark);
                }
                return;
            }
        }

        System.out.println("ОШИБКА: Такого предмета нет");
    }

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in;
        if(args.length == 0 ) { //ввод через консоль
            in = new Scanner(System.in);
        }
        else{  //ввод через файл
            in = new Scanner(new File(args[0]));
        }
        String command;
        allStudents.add(null);
        allStudents.add(null);

        //считывание команд
        System.out.println("Вводите команды:");
        do {
            command = in.next();

            //Команда addMark
            if(command.equals("addMark")){
                Student student = new Student(in.next(), in.next(), in.next());
                int ind = findStudent(student);
                Mark mark = new Mark(in.next(), in.nextInt());

                addMark(ind, student, mark);
            }

            //Команда removeMark
            else if(command.equals("removeMark")){
                Student student = new Student(in.next(), in.next(), in.next());
                int ind = findStudent(student);
                Mark mark = new Mark(in.next(), in.nextInt());

                removeMark(ind, mark);
            }

            //Команда listAll
            else if(command.equals("listAll")){
                listAll();
            }

            //Команда removePerson
            else if(command.equals("removePerson")){
                Student student = new Student(in.next(), in.next(), in.next());
                int ind = findStudent(student);

                removePerson(ind);
            }

            //Команда worstStudent
            else if(command.equals("worstStudent")){
                Student student = worstStudent();

                if(student == null) {
                    System.out.println("ОШИБКА: В базе нет ни одного студента");
                }

                else {
                    System.out.println("Студент с наихудшим средним баллом по всем предметам:");
                    student.outStudent();
                }
            }

            //Команда average
            else if(command.equals("average")){
                average();
            }

            //Команда marks
            else if(command.equals("marks")){
                marks(in.next());
            }

            //Если такой команды нет
            else if(!command.equals("quit")){
                System.out.println("ОШИБКА: Команда не распознана");
            }
        }while(!command.equals("quit"));
    }
}
