package com.example.day31springbootnew.service;

import com.example.day31springbootnew.entity.Student;
import com.example.day31springbootnew.exception.BusinessException;
import com.example.day31springbootnew.mapper.StudentLogMapper;
import com.example.day31springbootnew.mapper.StudentMapper;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentLogMapper studentLogMapper;

    public StudentService(StudentMapper studentMapper, StudentLogMapper studentLogMapper) {
        this.studentMapper = studentMapper;
        this.studentLogMapper = studentLogMapper;
    }

    //增
    public void addStudent(Student student) {
        Student existing = studentMapper.findByName(student.getName());
        if (existing != null) {
            throw new BusinessException("该学生已存在");
        }
        studentMapper.insert(student);
    }

    //查
    public List<Student> getStudents() {
        List<Student> list = studentMapper.findAll();
        return list;
    }

    //改
    public void updateStudent(Integer id, Student student) {
        Student existing = studentMapper.findById(id);
        if (existing == null) {
            throw new BusinessException("该学生不存在");
        }
        student.setId(id);
        studentMapper.update(student);
    }

    //删
    public void deleteStudent(Integer id) {
        Student student = studentMapper.findById(id);
        if (student == null) {
            throw new BusinessException("该学生不存在");
        }
        studentMapper.delete(id);
    }

    public Student findById(Integer id) {
        Student student = studentMapper.findById(id);
        if (student == null) {
            throw new BusinessException("该学生不存在");
        }
        return student;
    }

    @Transactional
    public void addStudentWithLog(Student student) {
        studentMapper.insert(student);
        studentLogMapper.insert("新增学生：" + student.getName());
    }

    public Student findByName(String name) {
        return studentMapper.findByName(name);
    }
}
