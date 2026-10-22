# Day28：自定义业务异常与参数校验

## 一、今日学习目标

1. 理解业务异常和系统异常的区别。
2. 学习创建自定义运行时异常。
3. 在 Service 层进行基础参数校验。
4. 在 Controller 层区分业务错误与其他运行时异常。

## 二、今日新增文件

1. `src/Day28/exception/BusinessException.java`
2. `src/Day28/service/Day28StudentService.java`
3. `src/Day28/controller/Day28StudentController.java`
4. `src/Day28/Day28Test.java`

本日总结文件：

`src/Day28/Day28总结.md`

## 三、核心知识

### 1. 自定义业务异常

通过继承 `RuntimeException` 创建项目自己的业务异常类。

```java
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
```

调用时可以传入具体错误信息，例如：

```java
throw new BusinessException("学号已经存在！");
```

### 2. 参数校验

在 Service 层执行数据库操作前，先检查学生对象和学号是否有效。

例如：

- 学生对象不能为 `null`。
- 学号不能为 `null`。
- 学号去除首尾空白后不能是空字符串。

### 3. 异常捕获顺序

`BusinessException` 继承 `RuntimeException`。

捕获多个存在父子关系的异常时，应先捕获子类，再捕获父类。

正确顺序：

```java
catch (BusinessException e) {
    System.out.println("业务处理失败：" + e.getMessage());
} catch (RuntimeException e) {
    System.out.println("系统处理失败，请检查程序或数据库。");
}
```

如果先捕获 `RuntimeException`，再捕获 `BusinessException`，编译器会报错，因为后面的子类捕获分支不可达。

### 4. 分层职责

- Controller：接收参数、调用 Service、反馈处理结果。
- Service：执行参数校验、组织业务流程、管理事务。
- Mapper：执行数据库查询和修改。
- 工具类：提供数据库会话或事务相关的通用功能。

## 四、今日测试结果

### 测试一：正常新增

实际输出：

`新增学生成功！`

结果：通过。

### 测试二：重复学号

实际输出：

`业务处理失败：学号已经存在！`

结果：通过。

### 测试三：空学号

实际输出：

`业务处理失败：学号不能为空！`

结果：通过。

### 检查题

1. 学号重复时使用明确的业务异常表达错误：答对。
2. `RuntimeException` 的捕获分支不能放在 `BusinessException` 前面：答对。

## 五、今日重点

1. 自定义异常可以更清楚地表达业务错误。
2. 参数校验应该在业务处理的适当位置进行。
3. 异常捕获顺序受继承关系影响。
4. 不应把所有异常都当成同一种错误。
5. 生产项目还需要考虑系统异常日志、统一异常处理和更完善的输入校验。
6. 业务层预先查询不能完全避免并发重复新增，数据库约束仍然重要。

## 六、下一步

继续学习 Java 后端项目中的业务处理与异常管理，为后续 Spring Boot 的统一异常处理打基础。