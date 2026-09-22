# Day10 学习总结

## 一、什么是泛型

泛型（Generic）就是把类型作为参数传递。

作用：

1. 限制数据类型
2. 减少类型转换
3. 提高代码安全性


例如：

ArrayList<String>

表示：

这个集合只能存 String 类型。


---

# 二、ArrayList泛型


没有泛型：

ArrayList list = new ArrayList();


可以存任何类型：

list.add("张三");
list.add(20);


取数据时需要强制转换。


使用泛型：

ArrayList<String> names = new ArrayList<>();



只能存 String：

names.add("张三");


如果：

names.add(20);


会出现类型错误。



---

# 三、HashMap泛型


HashMap本身也是泛型类。


格式：

HashMap<K,V>


K：

Key类型


V：

Value类型



项目中：

HashMap<String, Student>


表示：

String作为学号

Student作为学生对象



例如：

001 → Student对象



---

# 四、自定义泛型类


定义：

public class Box<T>{


    private T value;


}



T表示一个未知类型。



使用：

Box<String>


表示：

T就是String



Box<Integer>


表示：

T就是Integer



---

# 五、泛型方法


普通方法：

public void print(String str)



只能接收String。



泛型方法：

public static <T> void print(T value)



可以接收不同类型。


例如：

print("Java");

print(123);

print(3.14);



---

# 六、Day10重点


泛型核心：

提前规定类型。


常见形式：

ArrayList<E>

HashMap<K,V>

<T>



以后开发中：

集合、MyBatis、Spring都会大量使用泛型。



---

# 七、项目联系


学生管理系统中：


HashMap<String, Student>


表示：

学生ID

对应

学生对象



泛型保证：

ID一定是String

学生数据一定是Student


降低错误。

