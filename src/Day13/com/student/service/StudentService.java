package Day13.com.student.service;

import Day13.com.student.dao.StudentDAO;
import Day13.com.student.entity.Student;

import java.util.List;
import java.sql.SQLException;

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
}