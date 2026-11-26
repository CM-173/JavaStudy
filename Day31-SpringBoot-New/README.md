# 学生管理系统后端

## 项目简介

基于 Spring Boot + MyBatis + MySQL 的学生信息管理后端，实现学生信息的增删改查、
参数校验、统一异常处理和事务管理。

> 说明：本项目为个人学习项目，用于系统学习 Java 后端开发。

## 技术栈

- Java 21
- Spring Boot 4.1.2
- MyBatis 4.0.0
- MySQL 5.6
- Maven 3.9.10

## 功能

- 学生信息增删改查（CRUD）
- 分页查询
- 条件搜索（姓名模糊、年龄范围）
- 班级关联查询（LEFT JOIN）
- 参数校验（姓名非空、年龄 1-150）
- 统一返回结构（code / message / data）
- 全局异常处理（业务异常、校验异常、系统异常）
- 事务管理（新增学生 + 记录日志）
- 操作日志记录（SLF4J）
- 接口文档（SpringDoc OpenAPI）

## 接口列表

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | /students | 查询所有学生 |
| GET | /students/{id} | 按 ID 查询 |
| GET | /students/page | 分页查询 |
| GET | /students/search | 条件搜索（姓名/年龄范围） |
| GET | /students/with-class | 查询所有学生（含班级名） |
| POST | /students | 新增学生 |
| PUT | /students/{id} | 修改学生 |
| DELETE | /students/{id} | 删除学生 |
| POST | /students/with-log | 新增学生并记录日志（事务） |

## 运行方式

1. 创建数据库并建表（SQL 见下方）
2. 修改 `application.properties` 中的数据库密码
3. 运行 `mvn spring-boot:run`
4. 访问 `http://localhost:8081/students`

## 接口文档

启动项目后访问 `http://localhost:8081/swagger-ui.html`

可以看到所有接口的详细说明，并直接在页面上测试。

## 建表 SQL
...

### 建表 SQL

```sql
CREATE DATABASE student_db DEFAULT CHARACTER SET utf8mb4;
USE student_db;

CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE student_log (
    id INT PRIMARY KEY AUTO_INCREMENT,
    message VARCHAR(200) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

```

## 项目结构

```
src/main/java/com/example/day31springbootnew/
├── controller/     控制层，处理 HTTP 请求
├── service/        业务层，业务逻辑和事务
├── mapper/         数据访问层，MyBatis 接口
├── entity/         实体类
├── common/         统一返回结构
└── exception/      业务异常和全局异常处理
```

## 学习记录

本项目为 Day31-Day40 的学习成果，按天逐步完成： Spring Boot 基础 → 三层架构 → 参数校验 → Service 业务逻辑 → MyBatis 整合 → CRUD → 事务 → 统一返回