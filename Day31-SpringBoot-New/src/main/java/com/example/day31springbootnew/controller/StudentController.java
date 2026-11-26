package com.example.day31springbootnew.controller;

import com.example.day31springbootnew.common.PageResult;
import com.example.day31springbootnew.common.Result;
import com.example.day31springbootnew.service.StudentService;
import org.springframework.web.bind.annotation.*;
import com.example.day31springbootnew.entity.Student;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@Tag(name = "学生管理", description = "学生信息增删改查")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "查询所有学生")
    @GetMapping("/students")
    public Result<List<Student>> getStudents() {
        return Result.success(studentService.getStudents());
    }

    @Operation(summary = "新增学生")
    @PostMapping("/students")
    public Result<Void> addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return Result.success(null);
    }

    @Operation(summary = "修改学生")
    @PutMapping("/students/{id}")
    public Result<Void> updateStudent(@PathVariable Integer id, @Valid @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return Result.success(null);
    }

    @Operation(summary = "删除学生")
    @DeleteMapping("/students/{id}")
    public Result<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return Result.success(null);
    }

    @Operation(summary = "按 ID 查询学生")
    @GetMapping("/students/{id}")
    public Result<Student> selectStudent(@PathVariable Integer id) {
        return Result.success(studentService.findById(id));
    }

    @Operation(summary = "新增学生并记录日志")
    @PostMapping("/students/with-log")
    public Result<Void> addStudentWithLog(@RequestBody Student student) {
        studentService.addStudentWithLog(student);
        return Result.success(null);
    }

    @Operation(summary = "分页查询学生")
    @GetMapping("/students/page")
    public Result<PageResult<Student>> getStudentsByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(studentService.getStudentsByPage(page, size));
    }

    @Operation(summary = "条件搜索学生（姓名、年龄范围）")
    @GetMapping("/students/search")
    public Result<PageResult<Student>> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(studentService.searchStudents(name, minAge, maxAge, page, size));
    }

    @Operation(summary = "查询所有学生（含班级名）")
    @GetMapping("/students/with-class")
    public Result<List<Student>> getStudentsWithClass() {
        return Result.success(studentService.getStudentsWithClass());
    }
}