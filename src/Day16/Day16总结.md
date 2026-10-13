# Day16 JDBC事务总结

## 1. 什么是事务

事务(Transaction)是一组数据库操作的集合。

特点：

- 要么全部成功
- 要么全部失败

避免出现数据只修改一部分的问题。

---

## 2. JDBC事务三个核心方法

### 关闭自动提交

```java
connection.setAutoCommit(false);