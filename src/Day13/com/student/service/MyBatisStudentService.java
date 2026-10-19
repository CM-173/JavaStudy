
package Day13.com.student.service;

import Day13.com.student.entity.Student;
import Day13.com.student.mapper.StudentMapper;

import java.util.List;

public class MyBatisStudentService {

    private final StudentMapper studentMapper;

    public MyBatisStudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    // 根据学号查询学生
    public Student findStudentById(String id) {
        checkId(id);
        return studentMapper.findById(id.trim());
    }

    // 查询所有学生
    public List<Student> findAllStudents() {
        return studentMapper.findAll();
    }

    // 新增学生
    public boolean addStudent(Student student) {
        checkStudent(student);

        if (student.getAge() < 0) {
            throw new IllegalArgumentException("年龄不能小于 0！");
        }

        return studentMapper.insert(student) > 0;
    }

    // 修改学生
    public boolean updateStudent(Student student) {
        checkStudent(student);

        if (student.getAge() < 0) {
            throw new IllegalArgumentException("年龄不能小于 0！");
        }

        return studentMapper.update(student) > 0;
    }

    // 删除学生
    public boolean deleteStudent(String id) {
        checkId(id);
        return studentMapper.delete(id.trim()) > 0;
    }

    // 检查学生信息
    private void checkStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("学生信息不能为空！");
        }

        checkId(student.getId());

        if (student.getName() == null
                || student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("姓名不能为空！");
        }
    }

    // 检查学号
    private void checkId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("学号不能为空！");
        }
    }
}
