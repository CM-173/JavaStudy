package com.example.day31springbootnew.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.day31springbootnew.service.StudentService;

@RestController
public class TeacherController {
    private final StudentService studentService;

    public TeacherController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/teacher/student")
    public String hello() {
        return studentService.getStudentName();
    }
}
