
package Day27.service;

import Day13.com.student.entity.Student;
import Day13.com.student.mapper.StudentMapper;
import Day13.com.student.util.MyBatisTransactionManager;
import Day13.com.student.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class Day27StudentService {

    // 新增学生：检查学号，再执行新增
    public void addStudent(Student student) {

        // 1. 检查传入的学生对象
        if (student == null) {
            throw new IllegalArgumentException("学生信息不能为空！");
        }

        // 2. 获取数据库会话
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {

            StudentMapper mapper =
                    sqlSession.getMapper(StudentMapper.class);

            try {
                // 3. 检查学号是否已经存在
                Student existingStudent =
                        mapper.findById(student.getId());

                if (existingStudent != null) {
                    throw new IllegalArgumentException("学号已经存在！");
                }

                // 4. 学号不存在，执行新增
                int rows = mapper.insert(student);

                if (rows != 1) {
                    throw new IllegalStateException("新增学生失败！");
                }

                // 5. 新增成功，提交事务
                MyBatisTransactionManager.commit(sqlSession);

            } catch (RuntimeException e) {

                // 6. 业务执行异常，回滚事务
                MyBatisTransactionManager.rollback(sqlSession);

                // 7. 将异常交给调用方处理
                throw e;
            }
        }
    }
}
