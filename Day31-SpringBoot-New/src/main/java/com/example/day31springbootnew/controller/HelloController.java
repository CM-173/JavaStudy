package com.example.day31springbootnew.controller;

import com.example.day31springbootnew.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    public HelloController(StudentService studentService) {
        this.studentService = studentService;
    }

    private final StudentService studentService;

    @GetMapping("/hello")
    public String hello() {
        return "你好，Spring Boot！";
    }

    @GetMapping("/name")
    public String name() {
        return studentService.getStudentName();
    }
}