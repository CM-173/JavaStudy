package Day13.com.student.dao;

import Day13.com.student.entity.Student;

import java.util.List;

public interface StudentDAO {

    // 添加学生
    boolean save(Student student) throws Exception;

    // 查询所有学生
    List<Student> findAll() throws Exception;

    // 根据学号查询学生
    Student findById(String id) throws Exception;

    // 修改学生信息
    boolean update(Student student) throws Exception;

    // 根据学号删除学生
    boolean delete(String id) throws Exception;
}