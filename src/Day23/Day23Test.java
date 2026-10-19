
package Day23;

import Day13.com.student.entity.Student;
import Day13.com.student.mapper.StudentMapper;
import Day13.com.student.service.MyBatisStudentService;
import Day13.com.student.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class Day23Test {

    public static void main(String[] args) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {

            StudentMapper mapper =
                    sqlSession.getMapper(StudentMapper.class);

            MyBatisStudentService service =
                    new MyBatisStudentService(mapper);

            // 1. 查询已有学生
            System.out.println("===== 查询学生 =====");

            Student existing = service.findStudentById("T001");

            if (existing != null) {
                System.out.println(existing);
            } else {
                System.out.println("没有找到 T001");
            }

            // 2. 新增测试学生
            System.out.println("===== 新增学生 =====");

            String testId = "T2301";
            Student newStudent =
                    new Student(testId, "Day23测试学生", 21);

            Student beforeInsert = service.findStudentById(testId);

            if (beforeInsert == null) {
                boolean insertResult = service.addStudent(newStudent);
                System.out.println("新增结果：" + insertResult);

                if (insertResult) {
                    sqlSession.commit();
                    System.out.println("新增事务已提交！");
                }
            } else {
                System.out.println(
                        testId + " 已存在，为避免覆盖数据，跳过新增。");
            }

            // 3. 查询测试学生
            System.out.println("===== 查询新增学生 =====");

            Student current = service.findStudentById(testId);

            if (current != null) {
                System.out.println(current);

                // 4. 修改测试学生
                System.out.println("===== 修改学生 =====");

                current.setName("Day23修改后的学生");
                current.setAge(22);

                boolean updateResult = service.updateStudent(current);
                System.out.println("修改结果：" + updateResult);

                if (updateResult) {
                    sqlSession.commit();
                    System.out.println("修改事务已提交！");
                }

                // 5. 再次查询，验证修改
                System.out.println("===== 验证修改结果 =====");

                Student updated =
                        service.findStudentById(testId);

                System.out.println(updated);

                // 6. 删除测试学生
                System.out.println("===== 删除测试学生 =====");

                boolean deleteResult =
                        service.deleteStudent(testId);

                System.out.println("删除结果：" + deleteResult);

                if (deleteResult) {
                    sqlSession.commit();
                    System.out.println("删除事务已提交！");
                }

                // 7. 验证删除
                System.out.println("===== 验证删除结果 =====");

                Student deleted =
                        service.findStudentById(testId);

                System.out.println("删除后查询结果：" + deleted);

            } else {
                System.out.println(
                        "测试学生不存在，跳过修改和删除测试。");
            }

        } catch (Exception e) {
            System.err.println("测试发生异常：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
