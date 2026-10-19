# Day23 总结：MyBatis 实战与 Service 层

## 一、今日学习目标

1. 理解 Entity、Mapper、Service、Test 的职责。
2. 学习 Service 层调用 Mapper 的方式。
3. 使用 MyBatis 完成学生信息的 CRUD 操作。
4. 理解 SqlSession 的事务提交。
5. 通过测试验证数据库操作结果。

## 二、项目分层

### 1. Entity

存放实体类，例如 Student，表示学生的学号、姓名、年龄等数据。

### 2. Mapper

负责数据库访问。

本次使用 StudentMapper，包含查询、新增、修改、删除等方法。

### 3. Service

负责业务逻辑和参数校验。

本次新建 MyBatisStudentService，主要方法包括：

- findStudentById：根据学号查询学生
- findAllStudents：查询所有学生
- addStudent：新增学生
- updateStudent：修改学生
- deleteStudent：删除学生

### 4. Test

负责验证程序功能。

Day23Test 通过 MyBatisUtil 获取 SqlSession，再获取 StudentMapper，并将 Mapper 传给 Service。

## 三、构造方法注入

```java
private final StudentMapper studentMapper;

public MyBatisStudentService(StudentMapper studentMapper) {
    this.studentMapper = studentMapper;
}
```

通过构造方法把 Mapper 对象传入 Service。

这样 Service 不需要自己创建 Mapper 实现类，降低了代码之间的耦合。

当前是手动注入依赖，之后学习 Spring 时会接触框架自动管理依赖的方式。

## 四、Service 层参数校验

例如：

```java
if (id == null || id.trim().isEmpty()) {
        throw new IllegalArgumentException("学号不能为空！");
}
```

作用：

1. 避免无效参数进入数据库操作。
2. 将业务校验集中在 Service 层。
3. 减少重复代码。

本次还将重复校验提取为 checkId() 和 checkStudent() 私有方法。

## 五、MyBatis CRUD

### 1. 查询

```java
Student student = service.findStudentById("T001");
```

根据学号查询学生。

### 2. 新增

```java
boolean result = service.addStudent(student);
```

Mapper 的 insert 方法返回受影响的行数。大于 0 时，Service 返回 true。

### 3. 修改

```java
boolean result = service.updateStudent(student);
```

修改学生姓名和年龄。

### 4. 删除

```java
boolean result = service.deleteStudent("T2301");
```

删除指定学号的学生。

## 六、SqlSession 与事务提交

```java
sqlSession.commit();
```

MyBatis 通过 SqlSession 管理数据库会话。

当前项目使用：

```java
MyBatisUtil.getSqlSession()
```

底层调用 openSession()，默认不会自动提交事务。

因此，执行写操作后需要根据业务逻辑提交事务。SQL 返回受影响行数大于 0，并不等于事务已经永久保存。

本次测试分别提交新增、修改和删除操作。

需要注意：每次 commit() 都会提交当前 SqlSession 中尚未提交的事务，不只是某一条 SQL。

## 七、Day23 测试结果

1. 查询 T001 成功。
2. 新增 T2301 成功。
3. 修改 T2301 的姓名和年龄成功。
4. 修改后重新查询，数据符合预期。
5. 删除 T2301 成功。
6. 删除后查询返回 null。
7. 程序正常结束，退出代码为 0。

## 八、今日重点

1. Mapper 负责数据库访问，Service 负责业务逻辑。
2. Service 通过构造方法接收 Mapper。
3. CRUD 操作需要检查返回结果。
4. SqlSession 默认不会自动提交事务。
5. 测试数据应及时清理，避免污染数据库。

## 九、后续学习方向

下一步继续学习事务管理、业务层设计，并逐步过渡到 Spring 和 Spring Boot。