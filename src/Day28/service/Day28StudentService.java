package Day28.service;

import Day13.com.student.entity.Student;
import Day13.com.student.mapper.StudentMapper;
import Day13.com.student.util.MyBatisTransactionManager;
import Day13.com.student.util.MyBatisUtil;
import Day28.exception.BusinessException;
import org.apache.ibatis.session.SqlSession;

public class Day28StudentService {

    public void addStudent(Student student) {

        // 1. 检查学生对象
        if (student == null) {
            throw new BusinessException("学生信息不能为空！");
        }

        // 2. 检查学号
        if (student.getId() == null || student.getId().trim().isEmpty()) {
            throw new BusinessException("学号不能为空！");
        }

        // 3. 获取数据库会话
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {

            StudentMapper mapper =
                    sqlSession.getMapper(StudentMapper.class);

            try {
                // 4. 检查学号是否已存在
                Student existingStudent =
                        mapper.findById(student.getId());

                if (existingStudent != null) {
                    throw new BusinessException("学号已经存在！");
                }

                // 5. 执行新增
                int rows = mapper.insert(student);

                if (rows != 1) {
                    throw new IllegalStateException("新增学生失败！");
                }

                // 6. 新增成功后提交
                MyBatisTransactionManager.commit(sqlSession);

            } catch (RuntimeException e) {

                // 7. 失败时尝试回滚
                MyBatisTransactionManager.rollback(sqlSession);

                // 8. 将异常交给调用方
                throw e;
            }
        }
    }
}
