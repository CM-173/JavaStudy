package com.example.day31springbootnew.mapper;

import com.example.day31springbootnew.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    Student findByName(String name);

    int insert(Student student);

    List<Student> findAll();

    Student findById(Integer id);

    int update(Student student);

    int delete(Integer id);

    List<Student> findPage(@Param("offset") Integer offset, @Param("size") Integer size);

    Long count();

    List<Student> search(@Param("name") String name,
                         @Param("minAge") Integer minAge,
                         @Param("maxAge") Integer maxAge,
                         @Param("offset") Integer offset,
                         @Param("size") Integer size);

    Long countByCondition(@Param("name") String name,
                          @Param("minAge") Integer minAge,
                          @Param("maxAge") Integer maxAge);

    List<Student> findAllWithClassName();
}
