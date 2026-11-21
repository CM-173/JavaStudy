package com.example.day31springbootnew.service;

import com.example.day31springbootnew.entity.Student;
import com.example.day31springbootnew.mapper.StudentMapper;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public String addStudent(Student student) {
        Student existing = studentMapper.findByName(student.getName());
        if (existing != null) {
            return "该学生已存在";
        }
        studentMapper.insert(student);
        return "新增学生成功：" + student.getName();
    }

    public String getStudents() {
        List<Student> list = studentMapper.findAll();
        if (list.isEmpty()) {
            return "学生列表：暂无数据";
        }
        StringBuilder sb = new StringBuilder("学生列表：");
        for (Student s : list) {
            sb.append(s.getName()).append(", ");
        }
        String result = sb.toString();
        if (result.endsWith(", ")) {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    public Student findByName(String name) {
        return studentMapper.findByName(name);
    }

    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    public String updateStudent(Integer id, Student student) {
        Student existing = studentMapper.findById(id);
        if (existing == null) {
            return "该学生不存在";
        }
        student.setId(id);
        studentMapper.update(student);
        return "修改成功";
    }

    public String deleteStudent(Integer id) {
        Student student = studentMapper.findById(id);
        if (student != null) {
            studentMapper.delete(id);
            return "删除成功";
        }
        return "该学生不存在";
    }
}
