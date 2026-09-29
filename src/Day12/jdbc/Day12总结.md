一、JDBC连接数据库
流程：
Java程序
|
| DriverManager
↓
MySQL驱动 jar
|
↓
MySQL数据库

代码：
Connection connection =
DriverManager.getConnection(
url,
username,
password
);

作用：
建立 Java 和 MySQL 的连接。
二、JDBC核心对象
1. Connection
   负责：
   连接数据库

例如：
Connection connection;

2. Statement
   负责：
   发送SQL语句

例如：
Statement statement =
connection.createStatement();

3. ResultSet
   负责：
   接收查询结果

例如：
ResultSet resultSet =
statement.executeQuery(sql);

三、JDBC四大操作 CRUD
1. Create 新增
   SQL：
   insert into student values(...)

Java：
executeUpdate()

返回：
影响行数

2. Read 查询
   SQL：
   select * from student

Java：
executeQuery()

返回：
ResultSet

读取：
while(resultSet.next()){

}

3. Update 修改
   SQL：
   update student
   set age=22
   where id='004'

Java：
executeUpdate()

4. Delete 删除
   SQL：
   delete from student
   where id='004'

Java：
executeUpdate()

四、今天遇到的问题
1. No suitable driver
   原因：
   没有加载 MySQL 驱动。
   解决：
   添加：
   mysql-connector-java-5.1.49.jar

2. Access denied
   原因：
   数据库密码错误。
   你的：
   root
   123456

连接成功。