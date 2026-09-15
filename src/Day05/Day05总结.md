# Day05 Java项目结构总结

## 一、Day05学习目标

Day05主要学习：

- package（包）
- import（导包）
- 项目分层思想
- entity层
- service层
- HashMap在项目中的使用

目标：

从以前的单文件练习代码，升级成更接近企业项目的代码结构。

---

# 二、package（包）

## 作用

package用于管理Java类，让项目结构更加清晰。

例如：

```java
package Day05.com.student.entity;
对应文件路径：
Day05
 └── com
     └── student
          └── entity
               └── Student.java
注意：
package必须和文件所在目录对应。
三、import（导包）
作用
当一个类需要使用其他包中的类时，需要使用import。
例如：
StudentService中需要使用Student：
import Day05.com.student.entity.Student;
表示：
引入entity包中的Student类。
四、项目分层思想
以前写学生管理系统：
Student.java

StudentManager.java

Main.java
所有代码混在一起。
现在改成：
Main

 ↓

Service

 ↓

Entity
每一层负责不同工作。
五、Entity层
作用
Entity负责保存数据。
例如：
Student.java：
private String name;

private int age;

private String id;
表示学生对象拥有：
- 姓名
- 年龄
- 学号
Entity只负责数据。
不负责：
- 菜单
- 输入
- 业务逻辑
六、Service层
作用
Service负责业务处理。
例如：
StudentService：
addStudent()

deleteStudent()

updateStudent()

findStudent()
负责：
- 添加学生
- 删除学生
- 修改学生
- 查询学生
七、HashMap存储学生数据
Service中：
private HashMap<String,Student> students = new HashMap<>();
设计：
key        value

学号        学生对象
例如：
students.put(student.getId(),student);
相当于：
001 ---> 张三对象
查询：
students.get("001");
可以直接找到学生。
八、HashMap修改数据
HashMap特点：
如果key已经存在：
put会覆盖旧数据。
例如：
原：
001 张三
执行：
students.put("001",李四);
结果：
001 李四
所以修改数据：
直接put即可。
九、学生管理系统结构
Day05最终结构：
Day05

├── Main.java

└── com.student

      ├── entity

      │     └── Student.java

      │

      └── service

            └── StudentService.java

执行流程：
用户输入

 ↓

Main

 ↓

StudentService

 ↓

HashMap

 ↓

Student对象
十、Day05完成内容
✅ package包管理
✅ import导入类
✅ 项目结构划分
✅ entity实体类
✅ service业务类
✅ HashMap保存数据
✅ 添加学生
✅ 查询学生
✅ 修改学生
✅ 删除学生
✅ 菜单调用Service
十一、Day05心得
以前写代码：
重点是：
“功能能不能实现”
现在开始：
考虑：
“代码应该怎么组织”
企业项目不会把所有代码写在一个类里面。
通常会分：
- entity（数据）
- service（业务）
- controller（控制）
- mapper/dao（数据访问）
Day05学习的分层思想，是以后学习：
- Spring
- Spring Boot
- MyBatis
的重要基础。
Day05总结一句话
封装数据用Entity，
处理业务用Service，
启动程序用Main。
让不同代码各司其职，提高项目可维护性。