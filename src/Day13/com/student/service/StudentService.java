package Day13.com.student.service;

import Day13.com.student.dao.StudentDAO;
import Day13.com.student.entity.Student;

import java.util.List;
import java.sql.SQLException;
import Day13.com.student.util.JDBCUtil;
import java.sql.Connection;

public class StudentService {

    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    // 添加学生
    public boolean addStudent(Student student) throws SQLException {
        if (student == null) {
            throw new IllegalArgumentException("学生信息不能为空！");
        }

        if (student.getId() == null || student.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("学号不能为空！");
        }

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("姓名不能为空！");
        }

        if (student.getAge() < 0) {
            throw new IllegalArgumentException("年龄不能小于 0！");
        }

        return studentDAO.save(student);
    }

    // 查询所有学生
    public List<Student> findAllStudents() throws SQLException {
        return studentDAO.findAll();
    }

    // 根据学号查询学生
    public Student findStudentById(String id) throws SQLException {
        return studentDAO.findById(id);
    }

    // 修改学生
    public boolean updateStudent(Student student) throws SQLException {
        return studentDAO.update(student);
    }

    // 删除学生
    public boolean deleteStudent(String id) throws SQLException {
        return studentDAO.delete(id);
    }

    public boolean addTwoStudentsWithTransaction(
            Student student1,
            Student student2) throws SQLException {

        Connection connection = null;

        try {
            // 获取连接
            connection = JDBCUtil.getConnection();

            // 关闭自动提交
            connection.setAutoCommit(false);

            // 第一次新增
            boolean result1 = studentDAO.save(student1, connection);

            // 第二次新增
            boolean result2 = studentDAO.save(student2, connection);

            if (result1 && result2) {
                // 两次都成功
                connection.commit();
                return true;
            } else {
                // 有一次失败
                connection.rollback();
                return false;
            }

        } catch (Exception e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
                System.out.println("事务失败，已回滚！");

            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            return false;

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}