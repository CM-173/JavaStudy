package Day11;

import Day08.com.student.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectTest {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("张三",20,"001"));
        students.add(new Student("李四",16,"002"));
        students.add(new Student("王五",22,"003"));

        List<String> names = students.stream().map(student -> student.getName()).collect(Collectors.toList());
        System.out.println(names);

    }
}
