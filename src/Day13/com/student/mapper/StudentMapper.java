package Day13.com.student.mapper;

import Day13.com.student.entity.Class;
import Day13.com.student.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {

    @Select("SELECT id, name, age FROM student WHERE id = #{id}")
    Student findById(String id);

    List<Student> findAll();

    @Insert("INSERT INTO student(id, name, age, class_id) VALUES(#{id}, #{name}, #{age}, #{clazz.id})")
    int insert(Student student);

    @Update("UPDATE student SET name = #{name}, age = #{age} WHERE id = #{id}")
    int update(Student student);

    @Delete("DELETE FROM student WHERE id = #{id}")
    int delete(String id);

    Student findByIdWithClass(String id);

    Class findClassWithStudents(Integer id);

    List<Student> search(
            @Param("name") String name,
            @Param("age") Integer age
    );
}