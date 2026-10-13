package Day13.com.student.mapper;

import Day13.com.student.entity.Student;

import java.util.List;

public interface StudentMapper {

    Student findById(String id);

    List<Student> findAll();

    int insert(Student student);

    int update(Student student);

    int delete(String id);
}