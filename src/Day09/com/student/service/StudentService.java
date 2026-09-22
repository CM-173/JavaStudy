package Day09.com.student.service;

import Day09.com.student.dao.StudentDAO;
import Day09.com.student.factory.DAOFactory;
import Day09.com.student.entity.Student;
import Day09.com.student.exception.IdExistException;
import Day09.com.student.exception.StudentNotFoundException;

import java.util.HashMap;

public class StudentService {

    private StudentDAO studentDAO;


    public StudentService(StudentDAO studentDAO){

        this.studentDAO = studentDAO;

    }
    //添加学生
    public void addStudent(Student student) throws IdExistException {
        if (!studentDAO.save(student)) {
            throw new IdExistException("Id为：" + student.getId() + "的学生已存在");
        }
    }

    //展示学生信息
    public HashMap<String, Student> showStudents() {
        HashMap<String, Student> students = studentDAO.findAll();
        return students;
    }

    //删除学生信息
    public void deleteStudent(String id) throws StudentNotFoundException {
        if (!studentDAO.delete(id)) {
            throw new StudentNotFoundException("Id为：" + id + "的学生未查询到");
        }
    }

    //更新学生信息
    public void updateStudent(Student student) {
        studentDAO.update(student);
    }

    //查询单个学生
    public Student findStudent(String id) {
        return studentDAO.findById(id);
    }
}
