package Day07.com.student.service;

import Day07.com.student.dao.HashMapStudentDAO;
import Day07.com.student.entity.Student;
import Day07.com.student.exception.IdExistException;
import Day07.com.student.exception.StudentNotFoundException;

import java.util.HashMap;

public class StudentService {

    HashMapStudentDAO studentDAO = new HashMapStudentDAO();

    //check学生ID
    public boolean isExist(String id){
        Student student = studentDAO.findById(id);
        return student != null;
    }
    //添加学生
    public void addStudent(Student student) throws IdExistException {
        if (!studentDAO.save(student)){
            throw  new IdExistException("Id为：" + student.getId() + "的学生已存在");
        }
    }
    //展示学生信息
    public void showStudents() {
        HashMap<String, Student> students = studentDAO.findAll();
        for (String key : students.keySet()){
            Student student = students.get(key);
            System.out.println("姓名：" + student.getName());
            System.out.println("年龄：" + student.getAge());
            System.out.println("学号：" + student.getId());
        }

    }
    //删除学生信息
    public void deleteStudent(String id) throws StudentNotFoundException{
        if (!studentDAO.delete(id)){
            throw  new StudentNotFoundException("Id为：" + id + "的学生未查询到");
        }
    }

    //更新学生信息
    public void updateStudent(Student student) {
        if (studentDAO.update(student)){
            System.out.println("学生已更新");
        }else {
            System.out.println("学生未查询到");
            System.out.println("更新失败");
        }
    }

    //查询单个学生
    public void findStudent(String id){
        Student student = studentDAO.findById(id);
        if (student != null){
            System.out.println("姓名：" + student.getName());
            System.out.println("年龄：" + student.getAge());
            System.out.println("学号：" + student.getId());
        }else {
            System.out.println("没有找到该学生");
        }

    }

}
