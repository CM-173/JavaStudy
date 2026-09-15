package Day05.com.student.service;

import Day05.com.student.entity.Student;

import java.util.HashMap;

public class StudentService {

    private HashMap<String, Student> students = new HashMap<>();


    //check学生ID
    public boolean isExist(Student student) {
        if (students.containsKey(student.getId())){
            System.out.println(student.getId() + "存在");
            return true;
        }
        System.out.println(student.getId() + "不存在");
        return false;
    }
    //添加学生
    public void addStudent(Student student) {
        students.put(student.getId(), student);
        System.out.println("添加学生成功：" + student.getName());
    }
    //展示学生信息
    public void showStudents() {
        for (String key : students.keySet()) {
            Student student = students.get(key);
            System.out.println("姓名：" + student.getName());
            System.out.println("年龄：" + student.getAge());
            System.out.println("学号：" + student.getId());
        }
    }
    //删除学生信息
    public void deleteStudent(String id) {
        if (students.containsKey(id)){
            Student student = students.get(id);
            students.remove(id);
            System.out.println(student.getName() + "已删除");
            return;
        }
        System.out.println(id + "不存在");
    }

    //更新学生信息
    public void updateStudent(Student student) {
        students.put(student.getId(),student);
        System.out.println(student.getName() + "已更新");
    }

    //查询单个学生
    public void findStudent(String message){
        if (students.containsKey(message)){
            System.out.println("姓名：" + students.get(message).getName());
            System.out.println("年龄：" + students.get(message).getAge());
            System.out.println("学号：" + students.get(message).getId());
            return;
        }
        System.out.println(message + "不存在");
    }

}