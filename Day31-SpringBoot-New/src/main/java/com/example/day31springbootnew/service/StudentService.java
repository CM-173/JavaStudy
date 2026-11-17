package com.example.day31springbootnew.service;

import com.example.day31springbootnew.entity.Student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<Student>();

    public String getStudentName() {
        return "张三";
    }

    public String addStudent(Student student) {
        for (Student s : students) {
            if (s.getName().equals(student.getName())) {
                return "该学生已存在";
            }
        }
        students.add(student);
        return "新增学生成功：" + student.getName();
    }

    public String getStudents() {
        if (students.isEmpty()) {
            return "学生列表：暂无数据";
        }
        StringBuilder sb = new StringBuilder("学生列表：");
        for (Student s : students) {
            sb.append(s.getName()).append(", ");
        }
        String result = sb.toString();
        if (result.endsWith(", ")) {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    public Student findByName(String name) {
        for (Student s : students) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
}
