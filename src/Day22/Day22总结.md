# Day22 总结：MyBatis 动态 SQL 实战

## 一、使用 @Param 绑定多个参数

当 Mapper 方法包含多个参数时，可以使用 `@Param` 指定参数名称。

```java
List<Student> search(
        @Param("name") String name,
        @Param("age") Integer age
);
```

XML 中可以通过 `#{name}` 和 `#{age}` 获取对应参数。

## 二、使用 <if> 和 <where> 动态查询

```xml
<select id="search"
        resultType="Day13.com.student.entity.Student">
    SELECT id, name, age
    FROM student
    <where>
        <if test="name != null and name != ''">
            AND name = #{name}
        </if>
        <if test="age != null">
            AND age = #{age}
        </if>
    </where>
</select>
```

- `<if>`：条件成立时才拼接对应 SQL。
- `<where>`：按需生成 WHERE，并处理开头多余的 AND 或 OR。
- 当姓名和年龄都没有传入时，查询会返回所有学生，因此实际项目中应根据业务决定是否允许。

## 三、使用 <choose>、<when> 和 <otherwise>

```xml
<choose>
    <when test="id != null and id != ''">
        id = #{id}
    </when>
    <when test="name != null and name != ''">
        name = #{name}
    </when>
    <otherwise>
        age >= 0
    </otherwise>
</choose>
```

- `<choose>`：开始选择分支。
- `<when>`：从上到下检查条件，执行第一个满足条件的分支。
- `<otherwise>`：前面的条件都不成立时执行。
- 多个条件同时满足时，优先执行排在前面的 `<when>`。

## 四、使用 <set> 动态更新

Mapper 方法：

```java
int updateSelective(
        @Param("id") String id,
        @Param("name") String name,
        @Param("age") Integer age
);
```

XML：

```xml
<update id="updateSelective">
    UPDATE student
    <set>
        <if test="name != null and name != ''">
            name = #{name},
        </if>
        <if test="age != null">
            age = #{age},
        </if>
    </set>
    WHERE id = #{id}
</update>
```

- `<set>`：自动生成 SET，并处理末尾多余的逗号。
- `Integer` 可以使用 `null` 表示不修改年龄；基本类型 `int` 不能为 `null`。
- 如果姓名和年龄都没有提供，有可能生成不完整的 UPDATE SQL，应在 Service 层进行参数校验。
- 本例中姓名为空字符串也会被视为不更新，因此不能通过这种写法将姓名修改为空字符串。

## 五、事务提交

```java
sqlSession.commit();
```

当使用手动提交事务的方式时，需要调用 `commit()` 提交修改。否则，事务可能在关闭会话时回滚。

注意：提交会话中的事务可能同时提交其他尚未提交的操作；应根据业务决定提交时机。

## 六、今天遇到的问题

### 1. SQL 结果与打印结果不一致

错误代码：

```java
for (Student s : list) {
    System.out.println(student);
}
```

正确代码：

```java
for (Student s : list) {
    System.out.println(s);
}
```

原因：循环变量 `s` 与外部变量 `student` 是不同的变量。

### 2. 更新成功但数据库没有持久保存

检查事务是否提交，以及数据库客户端是否连接到正确的数据库、是否刷新了数据。

## 七、今日重点

1. `@Param` 用于明确多个参数的名称。
2. `<if>` 和 `<where>` 用于动态查询。
3. `<choose>` 和 `<when>` 用于按优先级选择条件。
4. `<set>` 用于动态更新。
5. `sqlSession.commit()` 用于提交事务。
6. 数据库更新后，应查询验证结果，并确认事务已经提交。
