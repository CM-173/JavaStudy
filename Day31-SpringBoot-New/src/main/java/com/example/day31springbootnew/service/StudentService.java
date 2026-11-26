package com.example.day31springbootnew.service;

import com.example.day31springbootnew.common.PageResult;
import com.example.day31springbootnew.entity.Student;
import com.example.day31springbootnew.exception.BusinessException;
import com.example.day31springbootnew.mapper.StudentLogMapper;
import com.example.day31springbootnew.mapper.StudentMapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentLogMapper studentLogMapper;

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentMapper studentMapper, StudentLogMapper studentLogMapper) {
        this.studentMapper = studentMapper;
        this.studentLogMapper = studentLogMapper;
    }

    //增
    public void addStudent(Student student) {
        log.info("新增学生请求：{}", student.getName());
        Student existing = studentMapper.findByName(student.getName());
        if (existing != null) {
            log.warn("学生已存在：{}", student.getName());
            throw new BusinessException("该学生已存在");
        }
        log.info("新增学生成功：{}", student.getName());
        studentMapper.insert(student);
    }

    //查
    public List<Student> getStudents() {
        List<Student> list = studentMapper.findAll();
        return list;
    }

    //改
    public void updateStudent(Integer id, Student student) {
        Student existing = studentMapper.findById(id);
        if (existing == null) {
            throw new BusinessException("该学生不存在");
        }
        log.info("修改学生：id={}", id);
        student.setId(id);
        studentMapper.update(student);
    }

    //删
    public void deleteStudent(Integer id) {
        Student student = studentMapper.findById(id);
        if (student == null) {
            log.warn("删除学生失败，学生不存在：id={}", id);
            throw new BusinessException("该学生不存在");
        }
        log.warn("删除学生：id={}", id);
        studentMapper.delete(id);
    }

    public Student findById(Integer id) {
        Student student = studentMapper.findById(id);
        if (student == null) {
            throw new BusinessException("该学生不存在");
        }
        return student;
    }

    @Transactional
    public void addStudentWithLog(Student student) {
        studentMapper.insert(student);
        studentLogMapper.insert("新增学生：" + student.getName());
    }

    public Student findByName(String name) {
        return studentMapper.findByName(name);
    }

    public PageResult<Student> getStudentsByPage(Integer page, Integer size) {
        // 1. 参数校验（防御性）
        if (page == null || page < 1) page = 1;
        if (size == null || size < 1) size = 10;
        if (size > 100) size = 100;

        // 2. 计算 offset
        int offset = (page - 1) * size;

        // 3. 查数据
        List<Student> list = studentMapper.findPage(offset, size);

        // 4. 查总数
        Long total = studentMapper.count();

        // 5. 组装返回
        log.debug("分页查询：page={}, size={}", page, size);
        return new PageResult<Student>(total, page, size, list);
    }

    public PageResult<Student> searchStudents(String name, Integer minAge, Integer maxAge, Integer page, Integer size) {
        if (page == null || page < 1) page = 1;
        if (size == null || size < 1) size = 10;
        if (size > 100) size = 100;
        int offset = (page - 1) * size;

        log.info("搜索学生：name={}, minAge={}, maxAge={}, page={}, size={}",
                name, minAge, maxAge, page, size);

        List<Student> list = studentMapper.search(name, minAge, maxAge, offset, size);
        Long total = studentMapper.countByCondition(name, minAge, maxAge);

        return new PageResult<Student>(total, page, size, list);
    }

    public List<Student> getStudentsWithClass() {
        return studentMapper.findAllWithClassName();
    }
}
