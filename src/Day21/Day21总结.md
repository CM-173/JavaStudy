1. 四种基础注解
   @Select("SELECT * FROM student WHERE id = #{id}")
   @Insert("INSERT INTO student(id,name,age) VALUES(#{id},#{name},#{age})")
   @Update("UPDATE student SET name=#{name}, age=#{age} WHERE id=#{id}")
   @Delete("DELETE FROM student WHERE id = #{id}")


2. 返回值
- 查询方法返回 Student 等实体对象。
- 新增、修改、删除方法通常返回 int，表示受影响的行数。
- 1 通常表示一行记录受影响，0 表示没有行受到影响。
3. 注解和 XML 的区别
   注解	XML
   SQL 写在 Java 接口上	SQL 写在映射文件中
   简单 SQL 比较方便	复杂动态 SQL 更清晰
   使用 @Select 等注解	使用 <select> 等标签
4. 避免重复映射
   同一个 Mapper 方法不要同时在注解和 XML 中重复定义 SQL，否则可能产生映射冲突。你今天已经实际处理了这个问题。
5. 多表查询回顾
- association：封装一个关联对象。
- collection：封装多个关联对象。