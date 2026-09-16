# Day06总结

## 一、今日学习内容

Day06主要学习了：

- DAO层
- 三层架构思想
- CRUD操作拆分
- DAO接口化
- 接口与实现类
- 多态在项目中的使用


---

# 二、项目结构升级

之前：

Main

↓

StudentService

↓

Student


问题：

Service直接管理数据。

例如：

```java
HashMap<String,Student>
如果以后换成MySQL，需要修改大量代码。
升级后：
Main

↓

StudentService

↓

StudentDAO

↓

HashMap
职责更加清晰：
- Service负责业务逻辑
- DAO负责数据操作
- Entity负责数据模型
三、DAO层
DAO：
全称：
Data Access Object
中文：
数据访问对象
作用：
专门负责访问数据。
例如：
保存学生：
save(Student student)
查询学生：
findById(String id)
删除学生：
delete(String id)
修改学生：
update(Student student)
四、CRUD
CRUD代表数据库常见四种操作：
Create
创建数据
例如：
save()
Read
读取数据
例如：
findAll()

findById()
Update
修改数据
例如：
update()
Delete
删除数据
例如：
delete()
五、DAO接口化
以前：
public class StudentDAO {

}
Service直接依赖具体类。
问题：
如果以后换数据库：
HashMap
↓
MySQL
Service需要修改。
改成：
接口：
public interface StudentDAO {

    void save(Student student);

    HashMap<String,Student> findAll();

    Student findById(String id);

    boolean delete(String id);

    boolean update(Student student);

}
实现类：
public class HashMapStudentDAO implements StudentDAO{

}
六、为什么使用接口？
接口定义：
“需要有哪些功能”
实现类负责：
“具体怎么实现”
例如：
接口：
StudentDAO
实现：
HashMapStudentDAO
以后：
MySQLStudentDAO
Service不用改变。
实现了解耦。
七、多态
学习了：
StudentDAO dao = new HashMapStudentDAO();
左边：
接口类型
右边：
实现类对象
这就是多态。
含义：
同一个接口，可以有不同实现。
例如：
现在：
HashMapStudentDAO
以后：
MySQLStudentDAO
调用方式不用改变。
八、Service和DAO职责区别
DAO负责：
- 保存数据
- 查询数据
- 删除数据
- 修改数据
例如：
students.put()
Service负责：
- 业务判断
- 参数检查
- 调用DAO
例如：
判断：
学号是否重复

年龄是否合法

用户是否有权限
九、Day06项目流程
添加学生：
用户输入

↓

Main

↓

StudentService.addStudent()

↓

StudentDAO.save()

↓

HashMap保存
查询学生：
用户输入id

↓

Service

↓

DAO.findById()

↓

返回Student对象
十、今日重点理解
1. 分层思想
不要把所有代码写在一个类里面。
企业项目：
Controller

↓

Service

↓

DAO/Mapper

↓

Database
今天学习的是这个思想的基础。
2. 封装
DAO中的数据：
private HashMap<String,Student> students;
外部不能直接修改。
只能通过：
save()

delete()

update()
保证数据安全。
3. 解耦
接口让代码更加灵活。
以后更换数据来源：
不用修改业务代码。
十一、Day06完成情况
✅ 创建DAO层
✅ Service调用DAO
✅ 完成CRUD
✅ 理解三层架构
✅ DAO接口化
✅ 理解接口、实现类、多态
Day06一句话总结：
DAO负责数据，Service负责业务，接口负责解耦，多态让代码可以替换实现。

Day06开始，我已经从写简单Java程序进入了企业项目代码结构学习阶段。