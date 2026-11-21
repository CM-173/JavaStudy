package com.example.day31springbootnew.mapper;

import com.example.day31springbootnew.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    Student findByName(String name);

    int insert(Student student);

    List<Student> findAll();
}
