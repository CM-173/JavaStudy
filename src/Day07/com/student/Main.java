package Day07.com.student;


import Day07.com.student.entity.Student;
import Day07.com.student.exception.IdExistException;
import Day07.com.student.exception.StudentNotFoundException;
import Day07.com.student.service.StudentService;

import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService();

        while (true) {
            System.out.println("==================");
            System.out.println("学生管理系统");
            System.out.println("1. 添加学生");
            System.out.println("2. 删除学生");
            System.out.println("3. 退出");
            System.out.println("==================");

            System.out.print("请选择：");

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("请输入数字");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("请输入姓名：");
                        String name = scanner.next();
                        System.out.print("请输入年龄：");
                        int age = scanner.nextInt();
                        System.out.print("请输入学号：");
                        String id = scanner.next();
                        Student student = new Student(name, age, id);
                        studentService.addStudent(student);
                        System.out.println("添加成功");
                    } catch (IdExistException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("输入格式错误");
                        scanner.nextLine();
                    }
                    break;
                case 2:
                    try {
                        System.out.print("请输入删除学生ID：");
                        String id = scanner.next();
                        studentService.deleteStudent(id);
                        System.out.println("删除成功");
                    } catch (StudentNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("退出系统");
                    return;
                default:
                    System.out.println("没有这个选项");
            }
        }
    }
}