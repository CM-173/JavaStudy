package com.example.day31springbootnew.service;

import com.example.day31springbootnew.entity.User;
import com.example.day31springbootnew.exception.BusinessException;
import com.example.day31springbootnew.mapper.UserMapper;
import com.example.day31springbootnew.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final UserMapper userMapper;

    public AuthService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public String login(String username, String password) {
        log.info("登录请求：username={}", username);

        User user = userMapper.findByUsername(username);
        if (user == null) {
            log.warn("登录失败，用户不存在：{}", username);
            throw new BusinessException("用户名或密码错误");
        }

        if (!user.getPassword().equals(password)) {
            log.warn("登录失败，密码错误：{}", username);
            throw new BusinessException("用户名或密码错误");
        }

        String token = JwtUtil.generateToken(user.getId(), user.getUsername(), user.getRole());
        log.info("登录成功：username={}, role={}", username, user.getRole());
        return token;
    }
}