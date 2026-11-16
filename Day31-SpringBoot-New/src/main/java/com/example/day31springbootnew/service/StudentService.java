package com.example.day31springbootnew.service;

import com.example.day31springbootnew.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public String getStudentName() {
        return "张三";
    }

    public String addStudent(Student student) {
        return "处理新增学生：" + student.getName();
    }

    public String getStudents() {
        return "学生列表查询成功";
    }
}
