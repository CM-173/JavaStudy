# Day11 Stream流学习总结


## 一、Stream是什么

Stream是Java8提供的数据处理方式。

作用：

对集合中的数据进行：

- 过滤
- 转换
- 排序
- 收集


常用于：

- 查询数据处理
- 集合操作
- 后端接口返回数据处理



---

# 二、stream()

作用：

把集合转换成Stream流。


例如：

ArrayList<Student> students;


students.stream();



之后可以继续处理数据。



---

# 三、forEach()

遍历数据。


例如：

students.stream()
.forEach(student -> {
System.out.println(student);
});



等价于：

for(Student student : students){

}



---

# 四、filter()

作用：

过滤数据。


例如：

查询年龄大于18岁的学生：


students.stream()
.filter(student -> student.getAge() > 18)
.forEach(student -> {
System.out.println(student.getName());
});



流程：

Student

↓

判断年龄

↓

符合条件留下



---

# 五、map()

作用：

转换数据。


例如：

Student对象转换成姓名：


students.stream()
.map(student -> student.getName())



转换：

Student

↓

String



常用于：

对象转换DTO。



---

# 六、collect()

作用：

把处理后的数据重新收集。


例如：


List<String> names =
students.stream()
.map(student -> student.getName())
.collect(Collectors.toList());



结果：

List<String>



---

# 七、Stream完整流程


集合

↓

stream()

↓

filter()

↓

map()

↓

collect()



---

# 八、项目联系


学生管理系统：


以前：

HashMap查询全部

然后for循环处理。


以后：

可以使用Stream：


查询学生

↓

过滤

↓

转换DTO

↓

返回给前端



---

# Day11重点

掌握：

stream()

filter()

map()

collect()



Stream核心思想：

不要直接修改原数据，

通过流水线处理数据。

