package Day11;

import java.util.ArrayList;

public class StreamTest {

    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("张三",20,"001"));
        students.add(new Student("李四",16,"002"));
        students.add(new Student("王五",22,"003"));

        students.stream().filter(student -> student.getAge() > 18).forEach(student -> {
            System.out.println(student.getName());
        });
    }
}