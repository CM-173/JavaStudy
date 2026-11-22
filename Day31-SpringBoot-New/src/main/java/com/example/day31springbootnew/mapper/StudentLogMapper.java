package com.example.day31springbootnew.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentLogMapper {
    int insert(String message);
}