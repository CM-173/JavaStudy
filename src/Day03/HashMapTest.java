package Day03;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        Student s1 = new Student("张三", 20, "001");
        Student s2 = new Student("李四", 21, "002");
        Student s3 = new Student("王五", 22, "003");
        HashMap<String, Student> hashMap = new HashMap<>();
        for (Student student : new Student[]{s1, s2, s3}) {
            hashMap.put(student.getId(), student);
        }
        //使用 entrySet() 遍历
        for (HashMap.Entry<String, Student> entry : hashMap.entrySet()) {
            String id = entry.getKey();
            Student student = entry.getValue();
            System.out.println(id + ":" + student.getName() + ":" + student.getAge() + ":" + student.getId());
        }
        for (Student student : hashMap.values()) {
            System.out.println(student.getName() + ":" + student.getAge() + ":" + student.getId());
        }
        //根据学号查询
        String ID = "002";

        System.out.println("姓名：" + hashMap.get(ID).getName());
        System.out.println("年龄：" + hashMap.get(ID).getAge());
        System.out.println("学号：" + hashMap.get(ID).getId());

        //修改
        int newAge = 22;

        Student student = hashMap.get(ID);
        student.setAge(newAge);

        System.out.println("姓名：" + hashMap.get(ID).getName());
        System.out.println("年龄：" + hashMap.get(ID).getAge());
        System.out.println("学号：" + hashMap.get(ID).getId());

        //删除
        String newId = "003";
        hashMap.remove(newId);
        for (String key : hashMap.keySet()) {
            System.out.println("姓名：" + hashMap.get(key).getName());
            System.out.println("年龄：" + hashMap.get(key).getAge());
            System.out.println("学号：" + hashMap.get(key).getId());
        }
        Student s4 = new Student("赵六", 20, "002");
        if (hashMap.containsKey(s4.getId())) {
            System.out.println("学号已经存在，不能添加！");
        } else {
            hashMap.put(s4.getId(), s4);
            System.out.println("添加成功。");
        }
        Student s5 = new Student("赵六", 20, "004");
        if (hashMap.containsKey(s5.getId())) {
            System.out.println("学号已经存在，不能添加！");
        } else {
            hashMap.put(s5.getId(), s5);
            System.out.println("添加成功。");
        }




























        /*// 创建HashMap
        HashMap<String,String> students = new HashMap<>();


        // 添加数据
        students.put("001","张三");
        students.put("002","李四");
        students.put("003","王五");


        // 根据学号查询学生
        String name = students.get("001");

        System.out.println(name);

        //根据学号进行修改
        students.put("001","张三丰");
        System.out.println("修改后的：");
        String name2 = students.get("001");
        System.out.println(name2);

        System.out.println("删除后的：");
        *//*String ID2 = students.get("002");
        System.out.println(ID2);*//*
        students.remove("002");
        for (String key : students.keySet()){
            System.out.println(key + ":" + students.get(key));
            *//*String KEY = students.get(key);
            System.out.println(KEY + ":" + students.get(KEY));*//*
        }

*/
        /*HashMap<String,Student> studentHashMap = new HashMap<>();

        Student value = new Student("张三",18,"0607230116");
        studentHashMap.put("001",value);
        System.out.println(studentHashMap.get("001").getName());
        System.out.println(studentHashMap.get("001").getAge());
        System.out.println(studentHashMap.get("001").getId());
        //ArrayList变成：HashMap可以直接根据录入的key进行查询，不用根据顺序查询*/
    }
}
