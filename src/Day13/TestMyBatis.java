package Day13;

import Day13.com.student.entity.Student;
import Day13.com.student.mapper.StudentMapper;
import Day13.com.student.util.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

public class TestMyBatis {

    public static void main(String[] args) {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        Student student = mapper.findById("001");

        System.out.println(student);

        sqlSession.close();

    }
}