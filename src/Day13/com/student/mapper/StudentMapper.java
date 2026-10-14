package Day13.com.student.mapper;

import Day13.com.student.entity.Class;
import Day13.com.student.entity.Student;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface StudentMapper {

    Student findById(String id);

    List<Student> findAll();

    int insert(Student student);

    int update(Student student);

    int delete(String id);

    Student findByIdWithClass(String id);

    Class findClassWithStudents(Integer id);

    List<Student> search(
            @Param("name") String name,
            @Param("age") Integer age
    );
}