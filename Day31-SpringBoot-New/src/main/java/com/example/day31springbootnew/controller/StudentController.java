package com.example.day31springbootnew.controller;

import com.example.day31springbootnew.common.PageResult;
import com.example.day31springbootnew.common.Result;
import com.example.day31springbootnew.service.StudentService;
import org.springframework.web.bind.annotation.*;
import com.example.day31springbootnew.entity.Student;

import jakarta.validation.Valid;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public Result<List<Student>> getStudents() {
        return Result.success(studentService.getStudents());
    }

    @PostMapping("/students")
    public Result<Void> addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return Result.success(null);
    }

    @PutMapping("/students/{id}")
    public Result<Void> updateStudent(@PathVariable Integer id, @Valid @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return Result.success(null);
    }

    @DeleteMapping("/students/{id}")
    public Result<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return Result.success(null);
    }

    @GetMapping("/students/{id}")
    public Result<Student> selectStudent(@PathVariable Integer id) {
        return Result.success(studentService.findById(id));
    }

    @PostMapping("/students/with-log")
    public Result<Void> addStudentWithLog(@RequestBody Student student) {
        studentService.addStudentWithLog(student);
        return Result.success(null);
    }

    @GetMapping("/students/page")
    public Result<PageResult<Student>> getStudentsByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(studentService.getStudentsByPage(page, size));
    }
}