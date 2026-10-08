package Day13;

import Day13.com.student.dao.StudentDAO;
import Day13.com.student.dao.StudentDAOImpl;
import Day13.com.student.entity.Student;
import Day13.com.student.service.StudentService;

import java.util.List;
import java.sql.SQLException;

public class TestStudentDAO {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAOImpl();
        StudentService studentService = new StudentService(studentDAO);

        try {
            Student student = new Student("T006", "测试学生小刘", 20);

            boolean result = studentService.addStudent(student);

            if (result) {
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败！");
            }

        } catch (SQLException e) {
            System.out.println("数据库操作失败！");
            System.out.println("错误信息：" + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("参数错误：" + e.getMessage());
        }
    }
}