# Day32 Spring Boot 项目结构与依赖注入

## 一、今日学习目标

理解 Spring Boot 项目结构、Spring Bean、组件扫描和依赖注入，掌握 Controller 调用 Service 的基本方式。

## 二、核心知识点

### 1. 项目结构

- `controller`：接收请求，处理接口调用。
- `service`：处理业务逻辑。
- `mapper`：负责数据库访问。

### 2. @SpringBootApplication

Spring Boot 启动类上的注解，包含自动配置、组件扫描等功能。

默认从启动类所在包开始扫描，并递归扫描子包。

### 3. Spring Bean

Bean 是由 Spring 容器创建和管理的对象。

例如：

```java
@Service
public class StudentService {
    public String getStudentName() {
        return "张三";
    }
}
```

`@Service` 用于将 StudentService 标记为 Spring 管理的组件。

### 4. 依赖注入

依赖注入（DI）就是对象需要其他对象时，由 Spring 容器提供依赖，而不是自己手动创建。

构造方法注入示例：

```java
private final StudentService studentService;

public HelloController(StudentService studentService) {
    this.studentService = studentService;
}
```

### 5. Controller 调用 Service

Controller 负责接收请求，Service 负责处理业务逻辑。

Controller 不应该把所有业务代码都写在接口方法中。

## 三、今日实操

### 1. StudentService

创建 StudentService，并使用 `@Service`：

```java
@Service
public class StudentService {
    public String getStudentName() {
        return "张三";
    }
}
```

### 2. HelloController

通过构造方法注入 StudentService，提供 `/name` 接口。

### 3. TeacherController

独立创建 TeacherController，通过构造方法注入 StudentService：

```java
@RestController
public class TeacherController {

    private final StudentService studentService;

    public TeacherController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/teacher/student")
    public String hello() {
        return studentService.getStudentName();
    }
}
```

## 四、测试结果

访问：

`http://localhost:8081/name`

返回：

```text
张三
```

访问：

`http://localhost:8081/teacher/student`

返回：

```text
张三
```

两个接口均测试通过。

## 五、今日总结

今天掌握了 Spring Boot 的基本项目结构、组件扫描、Spring Bean 和构造方法依赖注入。

理解了 Controller 通过 Spring 容器获得 Service 对象，而不是使用 `new` 自己创建对象。

Day32 实操完成。