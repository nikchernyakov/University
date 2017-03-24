import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = getEmployees(args[0]);
        System.out.println("Max salary: " + maxSalary(employees));
        System.out.println("Min salary: " + minSalary(employees));
        System.out.println("Average salary: " + averageSalary(employees));
        System.out.println("\nJob count:");
        jobCount(employees).forEach((s, i) -> System.out.println(s + " - " + i));
        System.out.println("\nABC:");
        abc(employees).forEach((c, i) -> System.out.println(c + " - " + i));
    }

    /**
     * Parsing employees data from file
     * 1) Get string lines from file
     * 2) Split all strings to 3 elements(name, job, salary)
     * 3) Create employees
     * @param path of file
     * @return list of employees
     */
    public static List<Employee> getEmployees(String path){
        try {
            return Files.lines(Paths.get(path))
                    .map(s -> s.split("[,\\s]+"))
                    .map(s -> new Employee(s[0], s[1], Integer.parseInt(s[2])))
                    .collect(Collectors.toList());
        } catch (IOException ex){
            System.out.println("File not found");
            return new ArrayList<>();
        }
    }

    /**
     * Find max salary
     * I think this method works faster than method in minSalary()
     * 1) Find employee with max Salary
     * 2) Convert employee to int
     */
    public static int maxSalary(List<Employee> list) {
        return list.parallelStream().max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .orElse(0);
    }

    /**
     * Find min salary
     * 1) Convert all Employees to int
     * 2) Find min
     */
    public static int minSalary(List<Employee> list) {
        return list.parallelStream().map(Employee::getSalary)
                .min(Integer::compareTo).orElse(0);
    }

    /**
     * Find average
     * 1) Convert to int
     * 2) Find average
     */
    public static double averageSalary(List<Employee> list) {
        return list.parallelStream()
                .mapToInt(Employee::getSalary)
                .average().orElse(0);
    }

    /**
     * Grouping in Map elemets <String, Integer>
     */
    public static Map<String, Integer> jobCount(List<Employee> list) {
        return list.parallelStream()
                .collect(Collectors.groupingBy(Employee::getJob, Collectors.summingInt(e -> 1)));
    }

    /**
     * 1) Get from employee his name
     * 2) Get first character from name
     * 3) Grouping in map <FirstCharacter, SumOfEmployees>
     */
    public static Map<Character, Integer> abc(List<Employee> list) {
        return list.parallelStream()
                .collect(Collectors.groupingBy(e -> e.getName().charAt(0), Collectors.summingInt(e -> 1)));
    }
}
