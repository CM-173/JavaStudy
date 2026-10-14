package Day20;

import Day13.com.student.entity.Student;
import Day13.com.student.mapper.StudentMapper;
import Day13.com.student.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Day20Test {
    public static void main(String[] args) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

            // 一对一查询：查询学生及其班级
            System.out.println("===== 一对一查询 =====");
            Student student = mapper.findByIdWithClass("T001");

            if (student != null) {
                System.out.println("学生信息：" + student);
                System.out.println("所属班级：" + student.getClazz());
            } else {
                System.out.println("没有找到该学生");
            }

            // 一对多查询：查询班级及其所有学生
            System.out.println("\n===== 一对多查询 =====");
            Day13.com.student.entity.Class clazz =
                    mapper.findClassWithStudents(1);

            if (clazz != null) {
                System.out.println("班级名称：" + clazz.getClassName());
                System.out.println("学生列表：");

                List<Student> students = clazz.getStudents();

                if (students == null || students.isEmpty()) {
                    System.out.println("该班级暂无学生");
                } else {
                    for (Student s : students) {
                        System.out.println(
                                "学号：" + s.getId()
                                        + "，姓名：" + s.getName()
                                        + "，年龄：" + s.getAge()
                        );
                    }
                }
            } else {
                System.out.println("没有找到该班级");
            }
        } catch (Exception e) {
            System.out.println("测试失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}