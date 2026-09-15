package Day05;

import Day05.com.student.entity.Student;
import Day05.com.student.service.StudentService;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();

        while (true) {

            System.out.println("=================");
            System.out.println("学生管理系统");
            System.out.println("1.添加学生");
            System.out.println("2.查看学生");
            System.out.println("3.查询学生");
            System.out.println("4.修改学生");
            System.out.println("5.删除学生");
            System.out.println("0.退出");
            System.out.println("=================");

            System.out.print("请选择：");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("姓名：");
                    String name = scanner.next();

                    System.out.print("年龄：");
                    int age = scanner.nextInt();

                    System.out.print("学号：");
                    String id = scanner.next();

                    Student student = new Student(name, age, id);

                    service.addStudent(student);
                    break;
                case 2:
                    service.showStudents();
                    break;
                case 3:
                    System.out.print("请输入学号：");
                    String searchId = scanner.next();
                    service.findStudent(searchId);
                    break;
                case 4:
                    System.out.print("请输入修改学生学号：");
                    String updateId = scanner.next();
                    System.out.print("新姓名：");
                    String newName = scanner.next();
                    System.out.print("新年龄：");
                    int newAge = scanner.nextInt();
                    Student updateStudent = new Student(newName,newAge,updateId);
                    service.updateStudent(updateStudent);
                    break;
                case 5:
                    System.out.print("请输入删除学生学号：");
                    String deleteId = scanner.next();
                    service.deleteStudent(deleteId);
                    break;
                case 0:
                    System.out.println("退出系统");
                    return;
                default:
                    System.out.println("输入错误");
            }
        }
    }
}