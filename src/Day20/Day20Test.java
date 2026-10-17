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

            // Day21：测试注解查询
            System.out.println("===== @Select 注解查询 =====");
            Student testStudent = mapper.findById("T001");

            if (testStudent != null) {
                System.out.println("学号：" + testStudent.getId());
                System.out.println("姓名：" + testStudent.getName());
                System.out.println("年龄：" + testStudent.getAge());
            } else {
                System.out.println("没有找到该学生");
            }

            // 测试新增
            Student test = new Student("T100", "注解测试学生", 20);
            int insertResult = mapper.insert(test);
            System.out.println("新增结果：" + insertResult);

// 测试修改
            test.setName("修改后的测试学生");
            test.setAge(21);
            int updateResult = mapper.update(test);
            System.out.println("修改结果：" + updateResult);

// 查询验证
            Student result = mapper.findById("T100");
            System.out.println("查询结果：" + result);

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

            // 测试删除
            int deleteResult = mapper.delete("T100");
            System.out.println("删除结果：" + deleteResult);

            // 再次查询，验证是否删除成功
            Student deletedStudent = mapper.findById("T100");
            System.out.println("删除后查询结果：" + deletedStudent);

            System.out.println("===== 多条件查询测试 =====");

            List<Student> list = mapper.search("张三", 20);

            System.out.println("查询结果数量：" + list.size());

            for (Student s : list) {
                System.out.println(s);
            }

            System.out.println("===== 查询优先级测试 =====");

            // 学号和姓名都传入，验证学号优先
            List<Student> priorityList =
                    mapper.searchByPriority("T001", "张三");

            System.out.println("查询结果数量：" + priorityList.size());

            for (Student s : priorityList) {
                System.out.println(s);
            }

            System.out.println("===== 动态更新测试 =====");

            int updateCount = mapper.updateSelective("T001", "动态更新测试", null);
            System.out.println("更新结果：" + updateCount);

            Student updatedStudent = mapper.findById("T001");
            System.out.println("更新后的学生：" + updatedStudent);

// 提交事务，让更新持久保存到数据库
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println("测试失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}