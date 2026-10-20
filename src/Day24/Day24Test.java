package Day24;

import Day13.com.student.entity.Student;
import Day13.com.student.mapper.StudentMapper;
import Day13.com.student.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class Day24Test {

    public static void main(String[] args) {

        String id1 = "T2402";
        String id2 = "T2403";

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {

            StudentMapper mapper =
                    sqlSession.getMapper(StudentMapper.class);

            // 先检查测试数据，避免影响已有记录
            if (mapper.findById(id1) != null
                    || mapper.findById(id2) != null) {
                System.out.println("测试学号已存在，请更换测试学号。");
                return;
            }

            try {
                System.out.println("===== 多条 SQL 事务回滚测试 =====");

                // 第一条 SQL：新增第一名学生
                Student student1 =
                        new Student(id1, "事务测试学生1", 20);

                int rows1 = mapper.insert(student1);
                System.out.println("第一条 SQL 影响行数：" + rows1);

                if (rows1 <= 0) {
                    throw new RuntimeException("第一名学生新增失败！");
                }

                // 第二条 SQL：新增第二名学生
                Student student2 =
                        new Student(id2, "事务测试学生2", 21);

                int rows2 = mapper.insert(student2);
                System.out.println("第二条 SQL 影响行数：" + rows2);

                if (rows2 <= 0) {
                    throw new RuntimeException("第二名学生新增失败！");
                }

                // 模拟后续业务处理失败
                throw new RuntimeException("模拟业务异常！");

            } catch (Exception e) {
                System.out.println("捕获异常：" + e.getMessage());

                // 两条新增都尚未提交，因此一起回滚
                sqlSession.rollback();
                System.out.println("当前事务已回滚。");
            }

            // 重新查询，验证两条新增是否都被撤销
            Student result1 = mapper.findById(id1);
            Student result2 = mapper.findById(id2);

            System.out.println("第一名学生查询结果：" + result1);
            System.out.println("第二名学生查询结果：" + result2);

            if (result1 == null && result2 == null) {
                System.out.println("测试通过：两条新增操作均已回滚！");
            } else {
                System.out.println("请检查事务配置和数据库状态。");
            }

        } catch (Exception e) {
            System.err.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}