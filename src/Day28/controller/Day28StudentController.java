package Day28.controller;

import Day13.com.student.entity.Student;
import Day28.service.Day28StudentService;
import Day28.exception.BusinessException;

public class Day28StudentController {

    private final Day28StudentService studentService =
            new Day28StudentService();

    public void addStudent(String id, String name, int age) {

        try {
            Student student = new Student(id, name, age);

            studentService.addStudent(student);

            System.out.println("新增学生成功！");

        } catch (BusinessException e) {
            System.out.println("业务处理失败：" + e.getMessage());

        } catch (RuntimeException e) {
            System.out.println("系统处理失败，请检查程序或数据库。");
        }
    }
}
