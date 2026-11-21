package com.example.day31springbootnew.controller;

import com.example.day31springbootnew.service.StudentService;
import org.springframework.web.bind.annotation.*;
import com.example.day31springbootnew.entity.Student;
import jakarta.validation.Valid;
/*import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;*/

@RestController
public class HelloController {

    private final StudentService studentService;

    public HelloController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "你好，Spring Boot！";
    }

    @GetMapping("/students")
    public String getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/students")
    public String addStudent(@Valid @RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/students")
    public String updateStudent() {
        return "学生信息修改成功";
    }

    @DeleteMapping("/students")
    public String deleteStudent() {
        return "学生删除成功";
    }

    @GetMapping("/students/{id}")
    public String selectStudent(@PathVariable Integer id) {
        return "正在查询学生：" + id;
    }

    @GetMapping("/student")
    public String getStudent(@RequestParam Integer id) {
        return "查询学生：" + id;
    }
}