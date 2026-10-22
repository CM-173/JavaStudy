
package Day27.controller;

import Day13.com.student.entity.Student;
import Day27.service.Day27StudentService;

public class Day27StudentController {

    private final Day27StudentService studentService =
            new Day27StudentService();

    // 模拟接收新增学生请求
    public void addStudent(String id, String name, int age) {

        try {
            // Controller 接收参数，并组装学生对象
            Student student = new Student(id, name, age);

            // 调用 Service 处理业务
            studentService.addStudent(student);

            // 根据执行结果反馈信息
            System.out.println("新增学生成功！");

        } catch (RuntimeException e) {
            System.out.println("新增学生失败：" + e.getMessage());
        }
    }
}
