# Day24 总结：MyBatis 事务管理与异常处理

## 一、今日学习目标

1. 理解数据库事务的基本概念。
2. 掌握 MyBatis 的 commit() 和 rollback()。
3. 理解多条 SQL 共用一个事务的意义。
4. 学习 try-catch 与 try-with-resources。
5. 通过实操验证事务回滚效果。

## 二、什么是事务

事务是数据库中一组操作的执行单元。

当多个数据库操作需要一起成功时，可以将它们放在同一个事务中。

例如：
1. 新增第一名学生。
2. 新增第二名学生。
3. 如果业务全部成功，则提交事务。
4. 如果中途发生异常，则回滚尚未提交的操作。

事务的原子性可以简单理解为：一组操作要么作为整体提交，要么撤销当前事务中尚未提交的操作。

## 三、MyBatis 事务控制

### 1. 提交事务

```java
sqlSession.commit();
```

作用：提交当前 SqlSession 中当前事务的操作。

### 2. 回滚事务

```java
sqlSession.rollback();
```

作用：撤销当前事务中尚未提交的操作。

注意：已经提交的操作不能通过普通 rollback() 撤销。

### 3. 当前项目的事务设置

MyBatisUtil 使用：

```java
return factory.openSession();
```

openSession() 默认不会自动提交事务，因此写操作通常需要显式提交。

SQL 执行成功不等于事务已经提交。

## 四、多条 SQL 共用一个事务

Day24 综合实操中，程序执行了两次学生新增操作。

执行顺序：

1. 检查 T2402 和 T2403 是否已经存在。
2. 新增第一名学生。
3. 新增第二名学生。
4. 人为抛出异常，模拟业务失败。
5. 调用 sqlSession.rollback()。
6. 重新查询两名学生。

测试结果：
- 第一条新增操作被回滚。
- 第二条新增操作被回滚。
- 两名学生重新查询均返回 null。
- 控制台显示“测试通过：两条新增操作均已回滚！”

这验证了同一个事务中尚未提交的多条数据库操作可以一起回滚。

前提是数据库使用支持事务的存储引擎，例如 MySQL InnoDB，并且这些操作确实处于同一个事务中。

## 五、异常处理

常见结构：

```java
try {
    // 执行业务操作
    sqlSession.commit();
} catch (Exception e) {
    sqlSession.rollback();
    throw e;
}
```

说明：
- try：执行正常业务。
- commit()：全部业务成功后提交。
- catch：捕获异常。
- rollback()：尝试撤销尚未提交的操作。
- throw e：继续将异常交给上层处理。

注意：实际项目还需要考虑回滚本身可能失败的情况，并合理记录异常。

## 六、try-with-resources

示例：

```java
try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
    // 数据库操作
}
```

作用：代码退出 try 代码块时自动关闭 SqlSession。

关闭 SqlSession 不等于提交事务，不能用关闭操作代替 commit()。

## 七、项目分层理解

- Entity：保存学生等实体数据。
- Mapper：负责数据库访问。
- Service：组织业务逻辑和参数校验。
- Test：验证程序功能。

如果多条 SQL 必须共同成功，应为整个业务明确事务边界，而不是每执行一条 SQL 就提交一次。

## 八、今日重点

1. commit() 用于提交事务。
2. rollback() 用于撤销尚未提交的操作。
3. 多条需要共同成功的 SQL 应共用明确的事务边界。
4. 异常发生后需要正确处理失败状态，不能只打印错误。
5. try-with-resources 可以自动关闭 SqlSession。
6. 已提交的事务不能通过普通 rollback() 撤销。

## 九、后续学习方向

继续学习 Service 层设计、事务管理的封装，以及 Spring 中的声明式事务管理，为后续学习 Spring Boot 做准备。