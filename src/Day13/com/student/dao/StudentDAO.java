package Day13.com.student.dao;

import Day13.com.student.entity.Student;

import java.util.List;
import java.sql.SQLException;

public interface StudentDAO {

    // 添加学生
    boolean save(Student student) throws SQLException;

    // 查询所有学生
    List<Student> findAll() throws SQLException;

    // 根据学号查询学生
    Student findById(String id) throws SQLException;

    // 修改学生信息
    boolean update(Student student) throws SQLException;

    // 根据学号删除学生
    boolean delete(String id) throws SQLException;
}