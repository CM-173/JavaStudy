# Day09 学习总结

## 一、DAOFactory

DAOFactory 是 DAO 工厂。

作用：
统一负责创建 DAO 对象。

以前：

StudentService 直接 new StudentDAOImpl();

现在：

StudentService 使用 StudentDAO，
DAO 对象由 DAOFactory 创建。

---

## 二、单例思想

单例的核心：

一个类在整个程序中尽量只创建一个对象。

例如：

private static StudentDAO studentDAO = new StudentDAOImpl();

以后调用：

DAOFactory.getStudentDAO();

直接返回已经创建好的 DAO。

---

## 三、构造方法注入

StudentService 不再自己创建 DAO。

以前：

StudentService 自己 new DAO。

现在：

Main 创建 DAO，然后传给 StudentService。

例如：

StudentDAO dao = DAOFactory.getStudentDAO();

StudentService service =
new StudentService(dao);

---

## 四、项目结构

Main
↓
DAOFactory
↓
StudentDAOImpl
↓
student.txt

StudentService
↓
StudentDAO
↓
StudentDAOImpl

---

## 五、Day09 最重要的思想

Main：
负责组装对象。

Service：
负责业务逻辑。

DAO：
负责数据操作。

DAOFactory：
负责创建 DAO。

通过接口降低代码之间的耦合。