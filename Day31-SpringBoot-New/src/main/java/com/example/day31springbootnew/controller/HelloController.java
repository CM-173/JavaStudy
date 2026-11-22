package com.example.day31springbootnew.controller;

import com.example.day31springbootnew.service.StudentService;
import org.springframework.web.bind.annotation.*;
import com.example.day31springbootnew.entity.Student;
import jakarta.validation.Valid;

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

    @PutMapping("/students/{id}")
    public String updateStudent(@PathVariable Integer id, @Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/students/{id}")
    public Student selectStudent(@PathVariable Integer id) {
        return studentService.findById(id);
    }

    @PostMapping("/students/with-log")
    public String addStudentWithLog(@RequestBody Student student) {
        return studentService.addStudentWithLog(student);
    }
}