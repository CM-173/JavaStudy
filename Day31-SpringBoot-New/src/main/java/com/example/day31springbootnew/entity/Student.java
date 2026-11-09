package com.example.day31springbootnew.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class Student {
    private Integer id;

    @NotBlank(message = "学生姓名不能为空")
    private String name;

    @NotNull(message = "学生年龄不能为空")
    @Min(value = 1, message = "年龄不能小于1")
    @Max(value = 150, message = "年龄不能大于150")
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
