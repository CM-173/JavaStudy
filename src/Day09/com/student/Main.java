package Day09.com.student;

import Day09.com.student.entity.Student;
import Day09.com.student.exception.IdExistException;
import Day09.com.student.exception.StudentNotFoundException;
import Day09.com.student.service.StudentService;
import Day09.com.student.dao.StudentDAO;
import Day09.com.student.factory.DAOFactory;

import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDAO studentDAO = DAOFactory.getStudentDAO();


        StudentService studentService =
                new StudentService(studentDAO);

        while (true) {
            System.out.println("==================");
            System.out.println("学生管理系统");
            System.out.println("1. 添加学生");
            System.out.println("2. 查询全部");
            System.out.println("3. 根据id查询");
            System.out.println("4. 修改学生");
            System.out.println("5. 删除学生");
            System.out.println("6. 退出");
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
                        HashMap<String, Student> students = studentService.showStudents();
                        if (students.isEmpty()){
                            System.out.println("系统内暂无学生数据。");
                        }else {
                            for (String key : students.keySet()) {
                                Student student = students.get(key);
                                System.out.println("姓名：" + student.getName());
                                System.out.println("年龄：" + student.getAge());
                                System.out.println("学号：" + student.getId());
                            }
                        }
                        System.out.println("查询完毕");
                    } catch (Exception e) {
                        System.out.println("操作失败，请检查输入");
                    }
                    break;
                case 3:
                    try {
                        System.out.println("请输入查询学生ID：");
                        String studentId = scanner.next();
                        Student student = studentService.findStudent(studentId);
                        if (student != null) {
                            System.out.println("姓名：" + student.getName());
                            System.out.println("年龄：" + student.getAge());
                            System.out.println("学号：" + student.getId());
                        } else {
                            System.out.println("没有找到该学生");
                        }
                    } catch (Exception e) {
                        System.out.println("操作失败，请检查输入");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("请输入要修改的学生ID：");
                        String studentId = scanner.next();
                        Student student = studentService.findStudent(studentId);
                        if (student != null) {
                            System.out.println("姓名：" + student.getName());
                            System.out.println("年龄：" + student.getAge());
                            System.out.println("学号：" + student.getId());
                            System.out.println("1.修改姓名");
                            System.out.println("2.修改年龄");
                            System.out.println("请选择：");
                            int choose = scanner.nextInt();
                            switch (choose) {
                                case 1:
                                    System.out.println("请输入新姓名：");
                                    String newName = scanner.next();
                                    student.setName(newName);
                                    studentService.updateStudent(student);
                                    System.out.println("学生已更新");
                                    break;

                                case 2:
                                    System.out.println("请输入新年龄：");
                                    int newAge = scanner.nextInt();
                                    student.setAge(newAge);
                                    studentService.updateStudent(student);
                                    System.out.println("学生已更新");
                                    break;
                            }
                        } else {
                            System.out.println("没有找到该学生");
                        }
                    } catch (Exception e) {
                        System.out.println("操作失败，请检查输入");
                    }
                    break;
                case 5:
                    try {
                        System.out.print("请输入删除学生ID：");
                        String id = scanner.next();
                        studentService.deleteStudent(id);
                        System.out.println("删除成功");
                    } catch (StudentNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("退出系统");
                    return;
                default:
                    System.out.println("没有这个选项");
            }
        }
    }
}