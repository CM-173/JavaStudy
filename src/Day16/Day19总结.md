# Day19 MyBatis 高级用法总结

## 一、ResultMap

### 1. 为什么需要 ResultMap？

MyBatis 可以通过 `resultType` 自动映射 Java 对象。

例如：

数据库字段：

```text
id
name
age

Java 属性：
private String id;
private String name;
private int age;

字段名一致时可以自动映射。
但是如果数据库字段和 Java 属性名称不一致：
数据库：
student_name

Java：
private String name;

MyBatis 无法自动识别。
这时需要使用 ResultMap。
2. ResultMap 使用方式
<resultMap id="studentMap"
           type="Student">

    <id property="id"
        column="student_id"/>

    <result property="name"
            column="student_name"/>

    <result property="age"
            column="student_age"/>

</resultMap>

对应关系：
column（数据库字段）

        ↓

property（Java属性）

3. id 和 result 区别
主键：
<id>

普通字段：
<result>

例如：
<id property="id" column="id"/>

<result property="name" column="name"/>

二、驼峰命名自动映射
1. 问题
数据库喜欢：
create_time
user_name
student_age

Java喜欢：
createTime
userName
studentAge

名称不一致。
2. 解决方法
在 mybatis-config.xml：
<settings>

    <setting
        name="mapUnderscoreToCamelCase"
        value="true"/>

</settings>

开启后：
自动转换：
create_time

↓

createTime

三、动态 SQL
动态 SQL 是 MyBatis 非常重要的功能。
作用：
根据不同条件自动生成不同 SQL。
1. if 标签
作用：
条件判断。
例：
<if test="name != null">

    AND name=#{name}

</if>

含义：
如果 name 不为空：
添加姓名查询条件。
2. where 标签
作用：
自动处理 WHERE。
例如：
<where>

    <if test="name != null">

        AND name=#{name}

    </if>

</where>

MyBatis 会自动处理：
- WHERE
- 多余 AND
避免：
WHERE AND name='张三'

这种错误。
3. choose 标签
类似 Java：
if(){

}else if(){

}else{

}

MyBatis：
<choose>

    <when test="id != null">

    </when>

    <when test="name != null">

    </when>

    <otherwise>

    </otherwise>

</choose>

适合：
有优先级的查询条件。
例如：
优先学号查询：
没有学号再姓名查询。
4. set 标签
用于动态 UPDATE。
例如：
<update id="update">

UPDATE student

<set>

    <if test="name != null">

        name=#{name},

    </if>


    <if test="age != null">

        age=#{age},

    </if>

</set>

WHERE id=#{id}

</update>

作用：
自动去除最后多余逗号。
四、MyBatis 分页查询
1. MySQL LIMIT
格式：
LIMIT 开始位置,数量

例如：
LIMIT 0,10

表示：
查询10条数据。
第二页：
LIMIT 10,10

跳过10条，查询10条。
2. 分页公式
开始位置 = (当前页 - 1) × 每页数量

例如：
第5页，每页10条：
(5-1) × 10 = 40

SQL：
LIMIT 40,10

五、MyBatis 多参数绑定
问题：
Mapper：
List<Student> findPage(
    int start,
    int size
);

XML：
LIMIT #{start},#{size}

MyBatis 不一定知道参数名称。
解决：
使用 @Param：
List<Student> findPage(
    @Param("start") int start,
    @Param("size") int size
);

这样：
start

对应

第一个参数


size

对应

第二个参数

六、Day19 综合练习
完成：
学生动态查询：
接口：
List<Student> search(
    @Param("name") String name,
    @Param("age") Integer age
);

XML：
<select id="search">

SELECT *
FROM student

<where>

    <if test="name != null">

        AND name=#{name}

    </if>

    <if test="age != null">

        AND age=#{age}

    </if>

</where>

</select>

实现：
- 姓名查询
- 年龄查询
- 多条件查询
- 无条件查询全部
Day19 学习总结
今天学习了 MyBatis 高级功能：
1. ResultMap
2. 驼峰自动映射
3. 动态 SQL
4. if
5. where
6. choose
7. set
8. 分页查询 LIMIT
9. @Param 参数绑定
