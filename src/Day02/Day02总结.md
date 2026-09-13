# Day02 Java面向对象 + 学生管理系统总结


## 一、今天复习了什么

今天主要复习了 Java 面向对象编程（OOP）的基础知识，并通过完成学生管理系统，重新熟悉 Java 项目开发流程。

主要内容：

- 类（Class）
- 对象（Object）
- 构造方法
- this关键字
- 封装（private、getter、setter）
- ArrayList集合
- CRUD增删改查思想
- while循环菜单
- 多文件项目结构


---

# 二、类和对象


## 1. 类（Class）

类是对象的模板。

例如：

```java
public class Student {

    String name;

    int age;

}
```

Student就是一个学生类。

里面的：

- name
- age

叫做属性。

方法表示对象可以执行的行为：

```java
public void study(){

    System.out.println("学习");

}
```


---

## 2. 创建对象

通过new创建对象：

```java
Student s1 = new Student();
```

表示：

创建一个Student对象，并赋值给变量s1。


对象可以访问自己的属性和方法：

```java
s1.name="张三";

s1.study();
```


---

# 三、构造方法


以前创建对象：

```java
Student s1 = new Student();

s1.name="张三";

s1.age=20;
```

需要一步一步赋值。


使用构造方法：

```java
public Student(String name,int age){

    this.name=name;

    this.age=age;

}
```


创建对象：

```java
Student s1=new Student("张三",20);
```


作用：

在创建对象时直接初始化数据。


---

# 四、this关键字


this表示当前对象。


例如：

```java
public Student(String name){

    this.name=name;

}
```


左边：

```java
this.name
```

表示当前对象的成员变量。


右边：

```java
name
```

表示传入的参数。


作用：

解决成员变量和参数重名问题。


---

# 五、封装


封装：

把对象的数据隐藏起来，通过方法控制访问。


以前：

```java
int age;
```

任何地方都可以修改：

```java
student.age=-100;
```


不合理。


改成：

```java
private int age;
```


外部不能直接访问。


通过getter获取：

```java
public int getAge(){

    return age;

}
```


通过setter修改：

```java
public void setAge(int age){

    if(age>0){

        this.age=age;

    }

}
```


好处：

可以在修改数据时进行判断。


---

# 六、ArrayList集合


## 1. 什么是ArrayList

ArrayList是Java提供的动态数组。

普通数组：

```java
int[] arr=new int[3];
```

长度固定。


ArrayList：

可以动态增加和删除数据。


---

## 2. 创建ArrayList


```java
ArrayList<Student> students=new ArrayList<>();
```


表示：

创建一个只能存储Student对象的集合。


---

## 3. ArrayList常用方法


### 添加

```java
students.add(student);
```

向集合添加数据。


### 获取数量

```java
students.size();
```

获取集合长度。


### 获取元素

```java
students.get(0);
```

根据下标获取对象。


### 删除

```java
students.remove(0);
```

删除指定位置的数据。


### 遍历

增强for：

```java
for(Student student:students){

    System.out.println(student.getName());

}
```

表示：

一个一个取出集合中的对象。


---

# 七、CRUD思想


学生管理系统实际上就是简单的CRUD。


## C（Create）

创建数据：

添加学生。


```java
students.add(student);
```


---

## R（Read）

读取数据：

查看学生。


```java
for(Student student:students)
```


---

## U（Update）

更新数据：

修改学生信息。


流程：

1. 根据id找到学生
2. 修改对象属性


例如：

```java
student.setName(name);
```


---

## D（Delete）

删除数据：

根据id找到学生，然后删除。


---

# 八、while循环菜单


为了让程序执行完功能后返回菜单：

使用：

```java
while(true){

}
```


程序流程：

```
显示菜单

↓

用户选择

↓

执行功能

↓

返回菜单

↓

继续操作
```


退出：

```java
break;
```


跳出循环。


---

# 九、项目结构


Day02项目：

```
Day02

├── Main.java

├── Student.java

└── StudentManager.java
```


## Student.java

负责：

学生对象。


包含：

- 姓名
- 年龄
- 学号


---

## StudentManager.java

负责：

学生管理逻辑。


功能：

- 添加学生
- 查看学生
- 修改学生
- 删除学生


---

## Main.java

负责：

- 程序入口
- 菜单显示
- 用户输入


---

# 十、今天遇到的问题


## 1. ArrayList存储对象

ArrayList里面保存的是对象：

```java
ArrayList<Student>
```


不是：

```java
ArrayList<String>
```


所以删除和修改需要先找到对应对象。


---

## 2. 删除对象


不能：

```java
students.remove(id);
```


因为集合里面存的是Student对象。


需要：

根据id找到学生，再删除。


---

## 3. Scanner使用


不要重复创建Scanner。


错误：

```java
new Scanner(System.in);
```


应该：

```java
Scanner scanner=new Scanner(System.in);
```


创建一次，多次使用。


---

# 十一、Day02收获


完成Day02后重新掌握：

- Java面向对象思想
- 使用对象解决问题的方法
- 集合保存数据的方法
- 简单管理系统开发流程
- CRUD开发思想


这些知识是后续学习：

- MySQL
- MyBatis
- Spring
- Spring Boot

的基础。


---

# 十二、Day02项目完成情况


学生管理系统V1：

✅ 添加学生

✅ 查看学生

✅ 修改学生

✅ 删除学生

✅ 菜单循环


后续优化：

- 数据持久化（数据库）
- 输入优化
- 异常处理
- 更完善查询功能


---

# Day02结束

通过今天学习，重新恢复了Java项目开发基础。

下一步继续学习：

Java集合深化 → MySQL → JDBC → Spring Boot
