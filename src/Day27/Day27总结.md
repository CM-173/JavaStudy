# Day27：项目分层与业务逻辑设计

## 一、今日学习目标

1. 理解 Controller、Service、Mapper 的职责。
2. 理解各层之间的调用关系。
3. 掌握将业务判断放在 Service 层的基本设计方法。
4. 完成学生新增功能的分层实操。

## 二、项目分层的职责

### 1. Controller 控制层

负责接收请求参数、调用 Service，并向调用方返回结果。

今天通过 `Day27StudentController` 模拟接收新增学生请求。

Controller 不直接编写 SQL，也不承担复杂的业务判断。

### 2. Service 业务层

负责处理业务规则、组织业务流程，以及在需要时管理事务。

今天创建了 `Day27StudentService`，负责：

- 检查学生对象是否为空。
- 查询学号是否已经存在。
- 学号重复时拒绝新增。
- 学号不存在时调用 Mapper 新增学生。
- 新增成功后提交事务。
- 发生运行时异常时尝试回滚，并继续抛出异常。

### 3. Mapper 数据访问层

负责执行数据库操作。

今天复用了已有的 `StudentMapper`，完成学号查询和学生新增，没有重复编写 SQL。

### 4. 工具类

- `MyBatisUtil`：获取 `SqlSession`。
- `MyBatisTransactionManager`：封装事务提交和回滚操作。

工具类不负责决定学生新增的业务规则。

## 三、今日新增文件

1. `src/Day27/service/Day27StudentService.java`
2. `src/Day27/controller/Day27StudentController.java`
3. `src/Day27/Day27Test.java`

本日总结文件：

`src/Day27/Day27总结.md`

## 四、今日实操内容

通过 Controller 调用 Service，Service 调用 Mapper，完成学生新增功能。

测试第一次新增：

- 学号：T2701
- 姓名：Day27分层测试
- 年龄：20

实际输出：

`新增学生成功！`

测试再次新增相同学号。

实际输出：

`新增学生失败：学号已经存在！`

测试结果：通过。

注意：如果 T2701 是本次测试新增的记录，它会保留在数据库中；本次测试没有清理这条记录。

## 五、今日重点

1. Controller 接收请求，Service 处理业务，Mapper 操作数据库。
2. 业务规则变化时，优先检查 Service 层。
3. SQL 查询或更新方式发生变化时，通常需要检查 Mapper 或 XML。
4. Service 可以调用 Mapper，但不应承担所有 SQL 实现细节。
5. 事务应覆盖需要一起成功或失败的数据库操作。
6. 数据库主键约束仍然重要，业务层先查询不能完全避免并发情况下的重复新增。

## 六、今日不足与下一步

今天采用手动创建对象的方式练习分层，尚未使用 Spring 依赖注入，也没有创建真正的 Web Controller。

后续学习 Spring Boot 时，将进一步学习依赖注入、请求映射、参数校验和统一异常处理。