package Day03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎进入学生管理系统。");
            System.out.println("1.添加学生\n" +
                    "2.查看学生\n" +
                    "3.删除学生\n" +
                    "4.修改学生\n" +
                    "5.退出");

            int a = scanner.nextInt();
            if (a == 1) {
                System.out.println("请输入学生姓名：");
                String name = scanner.next();
                System.out.println("请输入学生年龄：");
                int age = scanner.nextInt();
                System.out.println("请输入学生学号：");
                String id = scanner.next();
                manager.addStudent(new Student(name, age, id));
                System.out.println("输入任意键返回菜单");
                scanner.next();
            } else if (a == 2) {
                manager.showStudents();
                System.out.println("输入任意键返回菜单");
                scanner.next();
            } else if (a == 3) {
                System.out.println("请输入需要删除的学生学号：");
                String id = scanner.next();
                manager.deleteStudent(id);
                System.out.println("输入任意键返回菜单");
                scanner.next();
            } else if (a == 4) {
                System.out.println("请输入需要修改的学生学号：");
                String id = scanner.next();
                System.out.println("输入新名字");
                String name = scanner.next();
                manager.updateStudent(id);
                System.out.println("输入任意键返回菜单");
                scanner.next();
            } else if (a == 5) {
                System.out.println("已退出");
                break;
            } else {
                System.out.println("错误");
                System.out.println("输入任意键返回菜单");
                scanner.next();
            }
        }

    }
}
