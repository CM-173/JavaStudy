package Day03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StudentManager {
    HashMap<String, Student> students = new HashMap<>();//<key,value>
    Scanner scanner = new Scanner(System.in);

    public void addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            System.out.println("学号已存在，不能重复录入");
        } else {
            students.put(student.getId(), student);
            System.out.println("已录入。");
        }
    }

    public void showStudents() {
        for (String key : students.keySet()) {
            System.out.println(key + ":" + students.get(key).getName() + ":" + students.get(key).getAge() + ":" + students.get(key).getId());
        }
    }

    public void deleteStudent(String id) {
        ArrayList arrayList = new ArrayList();
        if (students.containsKey(id)) {
            students.remove(id);
        } else {
            System.out.println("未找到");
        }
    }

    public void updateStudent(String id) {
        if (students.containsKey(id)) {
            Student student = students.get(id);
            System.out.println("1.姓名：" + student.getName());
            System.out.println("2.年龄：" + student.getAge());
            System.out.println("3.学号：" + student.getId());
            System.out.println("请输入需要修改的信息：");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("请输入新姓名：");
                student.setName(scanner.next());
            } else if (choice == 2) {
                System.out.println("请输入新年龄：");
                student.setAge(scanner.nextInt());
            } else if (choice == 3) {
                System.out.println("请输入新学号：");
                String newId = scanner.next();
                if (students.containsKey(newId)) {
                    System.out.println("学号已存在，不能修改");
                } else {
                    students.remove(id);
                    student.setId(newId);
                    students.put(newId, student);
                    System.out.println("修改成功");
                }
            } else {
                System.out.println("错误");
            }
        } else {
            System.out.println("未找到");
        }
    }
}
