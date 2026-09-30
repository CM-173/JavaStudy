package Day13;

import Day13.com.student.dao.StudentDAO;
import Day13.com.student.dao.StudentDAOImpl;
import Day13.com.student.entity.Student;
import Day13.com.student.service.StudentService;

import java.util.List;

public class TestStudentDAO {

    public static void main(String[] args) throws Exception {
        StudentDAO studentDAO = new StudentDAOImpl();
        StudentService studentService = new StudentService(studentDAO);

        // 创建一条合法的学生信息
        Student student = new Student("T003", "测试学生小王", 20);

        try {
            boolean result = studentService.addStudent(student);

            if (result) {
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败！");
            }

            // 查询并验证
            Student savedStudent = studentService.findStudentById("T003");

            if (savedStudent != null) {
                System.out.println("查询验证成功：" + savedStudent);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("业务校验失败：" + e.getMessage());
        }
    }
}