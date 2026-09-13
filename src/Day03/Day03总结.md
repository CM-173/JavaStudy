# Day03 Java 集合复习总结

日期：2026/9/13

## 今日学习内容

今天主要复习 Java 集合框架中的：

- HashMap
- HashMap 遍历方式
- Iterator 迭代器
- ArrayList 与 HashMap 删除区别

---

# 一、HashMap 基础

## 1. HashMap特点

HashMap 存储数据的形式：

```
Key → Value
```

例如：

```java
HashMap<String, Student> students = new HashMap<>();
```

表示：

```
String 类型作为 Key
Student 类型作为 Value
```

例：

```
001 → Student(张三,20,001)
002 → Student(李四,21,002)
003 → Student(王五,22,003)
```

---

# 二、HashMap 常用方法

## 1. 添加数据 put()

```java
students.put("001",student);
```

作用：

向 HashMap 中添加数据。

如果 Key 已经存在：

会覆盖原来的 Value。

---

## 2. 查询数据 get()

```java
Student student = students.get("001");
```

作用：

根据 Key 获取 Value。

例如：

```
输入学号001

找到：

张三
```

---

## 3. 判断 Key 是否存在 containsKey()

```java
students.containsKey("001");
```

返回：

```
true
存在

false
不存在
```

常用于：

防止重复添加。

例如：

```java
if(students.containsKey(id)){
    System.out.println("学号已存在");
}
```

---

## 4. 删除 remove()

```java
students.remove("001");
```

根据 Key 删除对应 Value。

---

# 三、HashMap 遍历方式

## 1. keySet()

只获取 Key。

代码：

```java
for(String key : hashMap.keySet()){

    Student student = hashMap.get(key);

}
```

理解：

```
keySet()

获取所有Key

001
002
003
```

---

## 2. values()

只获取 Value。

代码：

```java
for(Student student : hashMap.values()){

    System.out.println(student.getName());

}
```

理解：

```
values()

获取所有Student对象
```

适合：

只需要对象，不需要 Key。

---

## 3. entrySet()

同时获取 Key 和 Value。


代码：

```java
for(HashMap.Entry<String,Student> entry : hashMap.entrySet()){

    String id = entry.getKey();

    Student student = entry.getValue();

}
```

理解：

Entry = 一组 Key + Value


例如：

```
Entry

Key:
001

Value:
Student(张三)
```

---

# 四、HashMap修改数据注意事项

修改普通属性：

可以直接：

```java
Student student = hashMap.get(id);

student.setAge(22);
```

因为：

拿到的是对象引用。

---

修改 HashMap 的 Key 时：

不能直接：

```java
student.setId(newId);
```

原因：

会导致：

```
HashMap Key:
002

Student里面id:
005
```

数据不一致。

正确方式：

步骤：

1. 删除旧Key

```java
hashMap.remove(oldId);
```

2. 修改对象

```java
student.setId(newId);
```

3. 放入新Key

```java
hashMap.put(newId,student);
```

---

# 五、Iterator 迭代器

## 1. Iterator作用

Iterator 是集合遍历工具。

主要用于：

- 遍历集合
- 遍历时安全删除元素

---

## 2. 创建Iterator


```java
Iterator<String> iterator = list.iterator();
```

---

## 3. 常用方法


### hasNext()

判断是否还有元素。

```java
iterator.hasNext();
```

返回：

```
true 有元素

false 没有元素
```


---

### next()

获取下一个元素。

```java
iterator.next();
```

---

### remove()

删除当前元素。

```java
iterator.remove();
```

---

# 六、Iterator删除ArrayList数据


错误方式：

```java
for(Student student : students){

    students.remove(student);

}
```

原因：

遍历过程中修改集合。


---

正确方式：

```java
Iterator<Student> iterator = students.iterator();

while(iterator.hasNext()){

    Student student = iterator.next();

    if(student.getId().equals(id)){

        iterator.remove();

    }

}
```

流程：

```
ArrayList

Student
Student
Student

↓

iterator()

↓

一个一个取出

↓

找到目标

↓

iterator.remove()
```

---

# 七、HashMap和ArrayList删除区别


## HashMap

有Key：

```
001 → 张三
```

直接：

```java
remove("001");
```


---

## ArrayList

没有Key：

```
张三
李四
王五
```

需要：

遍历寻找

↓

找到对象

↓

删除


---

# 八、今日完成项目升级

学生管理系统升级：

实现：

- 添加学生
- 查询学生
- 修改学生
- 删除学生
- 防止重复学号

使用：

```
HashMap<String,Student>
```

并练习：

```
ArrayList + Iterator删除
```

---

# 今日重点记忆

## HashMap

```
put()
get()
remove()
containsKey()

keySet()
values()
entrySet()
```

## Iterator

```
iterator()

hasNext()

next()

remove()
```

---

# 今日总结

Day03重新掌握了Java集合核心知识。

理解了：

1. HashMap通过Key快速查找数据。

2. ArrayList适合存储有顺序的数据。

3. HashMap修改Key需要重新put。

4. Iterator可以安全删除集合元素。

这些内容是后续学习：

- Spring
- Spring Boot
- MyBatis

非常重要的基础。