# Day13 学习总结：JDBC 与分层设计

## 一、今日学习目标

将学生管理系统的数据存储方式从 HashMap / 文件逐步改为 MySQL 数据库。

## 二、项目结构

- `com.student.entity`：实体类，封装学生信息。
- `com.student.dao`：DAO 接口与实现类，负责数据库操作。
- `com.student.service`：业务逻辑层，负责业务校验并调用 DAO。
- `com.student.util`：数据库连接工具类。
- `TestStudentDAO`：测试数据库操作与业务逻辑。

本项目实际包名带有 `Day13` 前缀，例如 `Day13.com.student.dao`。

## 三、核心知识点

### 1. JDBC

通过 `DriverManager.getConnection()` 获取数据库连接。

### 2. PreparedStatement

使用 `?` 作为参数占位符，通过 `setString()`、`setInt()` 设置参数，避免直接拼接 SQL 参数。

### 3. ResultSet

通过 `next()` 逐条读取查询结果，再使用 `getString()`、`getInt()` 获取字段值。

### 4. try-with-resources

自动关闭数据库连接、SQL 语句和结果集，减少资源泄漏风险。

### 5. 分层设计

- DAO：负责数据库操作。
- Service：负责业务逻辑与参数校验。
- 测试类：负责调用方法并验证结果。

### 6. 构造方法注入

通过 `StudentService(StudentDAO studentDAO)` 注入 DAO 实现，使 Service 依赖接口而不是具体实现类。

### 7. 业务参数校验

添加学生之前，检查学生对象、学号、姓名是否为空，以及年龄是否小于 0。

## 四、已完成的测试

- 查询所有学生：成功。
- 添加学生：成功。
- 根据学号查询：成功。
- 修改学生：成功。
- 删除测试学生：成功。
- 空姓名校验：成功拦截。
- 正常学生通过 Service 添加：成功。

## 五、后续改进方向

- 将通用 `Exception` 改为更具体的 `SQLException` 等异常类型。
- 使用统一的异常处理机制。
- 增加学号重复检查和更完整的业务校验。
- 将数据库连接配置从代码中分离，避免提交真实密码。
- 学习 Maven 管理 JDBC 驱动依赖。
- 后续学习事务处理与单元测试。