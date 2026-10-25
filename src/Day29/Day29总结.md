# Day29：统一异常处理的基本思想

## 一、今日学习目标

1. 理解统一异常处理的意义。
2. 区分 Controller、Service、Mapper 的职责。
3. 使用普通 Java 模拟统一异常处理。
4. 理解 `BusinessException` 和 `RuntimeException` 的继承关系。
5. 掌握多个 `catch` 分支的正确顺序。

## 二、今日新增文件

1. `src/Day29/handler/Day29ExceptionHandler.java`
2. `src/Day29/Day29Test.java`
3. `src/Day29/Day29总结.md`

## 三、核心知识

### 1. 为什么需要统一异常处理？

如果多个 Controller 都重复编写相同的 `try-catch`，代码会变得冗余，修改和维护也更加麻烦。

统一异常处理的思路是将重复的异常处理逻辑集中到一个位置，便于维护错误反馈方式。

### 2. 各层的基本职责

- Controller：接收请求、调用业务层、组织响应。
- Service：处理业务规则、参数校验和业务流程。
- Mapper：负责数据库查询和修改。
- 统一异常处理器：集中处理异常并提供适当的错误反馈。

### 3. 普通 Java 模拟统一异常处理

通过 `Runnable` 接收一段需要执行的操作：

```java
public void execute(Runnable action) {
    try {
        action.run();
    } catch (BusinessException e) {
        handleBusinessException(e);
    } catch (RuntimeException e) {
        handleSystemException(e);
    }
}
```

`action.run()` 执行传入的操作。发生异常时，由对应的 `catch` 分支处理。

本日实现的是普通 Java 模拟版，并非 Spring Boot 的真正全局异常处理器。

### 4. 异常捕获顺序

`BusinessException` 继承 `RuntimeException`。

正确顺序：

```java
catch (BusinessException e) {
    handleBusinessException(e);
} catch (RuntimeException e) {
    handleSystemException(e);
}
```

先捕获子类，再捕获父类。如果先捕获父类，后面的子类分支就无法执行，编译器会报错。

### 5. 系统异常不能简单吞掉

真实项目除了给用户返回简洁的错误信息，通常还需要记录详细日志，以便排查问题。不要把数据库密码、敏感 SQL 或完整堆栈信息直接暴露给用户。

## 四、今日测试结果

### 测试一：正常执行

实际输出：

`新增学生成功！`

结果：通过。

### 测试二：业务异常

实际输出：

`业务处理失败：学号已经存在！`

结果：通过。

### 测试三：系统异常

实际输出：

`系统处理失败，请检查程序或数据库。`

结果：通过。

### 最后检查题

答案：B，正确。

原因：`BusinessException` 是 `RuntimeException` 的子类，捕获父类后再捕获子类会导致编译错误。

## 五、今日重点

1. 集中处理重复逻辑可以降低维护成本。
2. 业务异常与系统异常需要区别处理。
3. 异常捕获顺序受继承关系影响。
4. 真实项目还需要合理的日志记录和异常响应设计。
5. Spring Boot 可以通过 `@RestControllerAdvice` 和 `@ExceptionHandler` 实现真正的全局异常处理。

## 六、下一步

继续学习 Spring Boot 项目中的统一异常处理，为后续 REST API 和标准化响应做准备。
