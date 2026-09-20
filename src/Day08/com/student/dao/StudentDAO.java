package Day08.com.student.dao;

import Day08.com.student.entity.Student;

import java.io.IOException;
import java.util.HashMap;

public interface StudentDAO {
    //添加
    boolean save(Student student);

    //查询全部
    HashMap<String, Student> findAll();

    //根据id查询
    Student findById(String id);

    //删除
    boolean delete(String id);

    //修改
    boolean update(Student student);
}