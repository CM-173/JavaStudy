package com.example.day31springbootnew.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    // 密钥（至少 32 字节）
    private static final String SECRET = "nailStudioSecretKeyForJwtToken2026Learning";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // 过期时间：2 小时
    private static final long EXPIRATION = 2 * 60 * 60 * 1000L;

    /**
     * 生成 token
     */
    public static String generateToken(Integer userId, String username, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION);

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("username", username)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(KEY)
                .compact();
    }

    /**
     * 解析 token（失败会抛异常）
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 验证 token 是否有效
     */
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从 token 读取用户 ID
     */
    public static Integer getUserId(String token) {
        return Integer.valueOf(parseToken(token).getSubject());
    }

    /**
     * 从 token 读取用户名
     */
    public static String getUsername(String token) {
        return parseToken(token).get("username", String.class);
    }

    /**
     * 从 token 读取角色
     */
    public static String getRole(String token) {
        return parseToken(token).get("role", String.class);
    }
}