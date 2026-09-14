## 一、static 关键字

### 作用

static 修饰的成员属于类，而不是某一个对象。

特点：

- 所有对象共享一份数据
- 可以通过类名直接调用
- 随着类加载而创建


例如：

```java
public class Person {

    static String school = "南阳师范学院";

}
调用：
Person.school;
使用场景
适合存放所有对象共同拥有的数据：
例如：
- 学校名称
- 公司名称
- 系统配置
二、this 关键字
含义
this 表示当前对象。
例如：
public void setName(String name){

    this.name = name;

}
其中：
this.name
表示对象自己的成员变量。
name
表示方法传入的参数。
作用：
解决成员变量和参数同名的问题。
三、封装
什么是封装？
把对象的属性隐藏起来，通过方法进行访问。
例如：
以前：
student.age = -10;
任何地方都可以修改。
封装后：
private int age;
只能通过：
setAge()
getAge()
进行操作。
好处
1. 保护数据安全
2. 可以加入数据校验
例如：
public void setAge(int age){

    if(age < 0 || age > 150){

        System.out.println("年龄不合法");

        return;
    }

    this.age = age;

}
四、构造方法
作用
创建对象时自动执行。
例如：
Student s = new Student();
执行：
public Student(){

}
有参构造
public Student(String name,int age){

    this.name = name;

    this.age = age;

}
创建：
Student s = new Student("张三",20);
注意
如果自己写了构造方法：
public Student(String name){

}
Java不会自动生成无参构造。
所以通常保留：
public Student(){

}
五、方法重载 Overload
定义
同一个类：
方法名相同，但是参数列表不同。
例如：
public Student(){

}


public Student(String name){

}


public Student(String name,int age){

}
注意
只改变返回值不算重载。
错误：
public int add(){

}


public String add(){

}
六、继承 extends
作用
减少代码重复，提高代码复用。
例如：
父类：
public class Person {

    String name;

    int age;

}
子类：
public class Student extends Person{

    String id;

}
关系：
        Person

          |

       Student
Student拥有：
Person中的属性和方法。
七、super 关键字
含义
super 表示父类。
调用父类构造方法
public Student(String name,int age,String id){

    super(name,age);

    this.id=id;

}
作用：
调用：
Person(name,age)
初始化父类属性。
调用父类方法
super.eat();
调用父类的 eat 方法。
八、方法重写 Override
定义
子类重新定义父类已有的方法。
父类：
public void introduce(){

    System.out.println("我是人");

}
子类：
@Override
public void introduce(){

    System.out.println("我是学生");

}
调用子类对象：
输出：
我是学生
九、多态
定义
父类引用指向子类对象。
格式：
父类 变量 = new 子类();
例如：
Person p = new Student();
多态特点
1. 编译看左边
决定能调用哪些方法。
例如：
p.study();
如果 Person 没有 study：
无法通过编译。
2. 运行看右边
决定实际调用哪个方法。
例如：
p.introduce();
如果 Student 重写了 introduce：
调用 Student 的方法。
十、Day04 实战内容
完成：
Person父类
包含：
- name
- age
- school
- eat方法
Student子类
继承Person：
新增：
- stuId
- study方法
Animal和Cat练习
学习：
- extends
- Override
- 多态
Day04知识点总结
本日学习：
✅ static 类变量
✅ this 当前对象
✅ private 封装
✅ getter/setter
✅ 构造方法
✅ 方法重载
✅ extends 继承
✅ super 调用父类
✅ Override 方法重写
✅ 多态
Day04心得
Java面向对象核心思想：
将现实中的对象抽象成代码中的类。
通过：
封装保护数据，
继承减少重复，
多态提高扩展性。
这些思想也是后续学习：
- Spring
- Spring Boot
- 后端开发
的重要基础。