package com.example.day31springbootnew.mapper;

import com.example.day31springbootnew.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
}