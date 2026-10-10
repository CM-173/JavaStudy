package Day13.com.student.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    public static void main(String[] args) {

        String sql = "UPDATE student SET age = age + 1 WHERE id = ?";

        try (Connection connection = JDBCUtil.getConnection()) {

            // 1. 关闭自动提交
            connection.setAutoCommit(false);

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                // 2. 执行第一条 SQL
                statement.setString(1, "T003");
                statement.executeUpdate();

                // 3. 执行第二条 SQL
                statement.setString(1, "T004");
                statement.executeUpdate();

                // 4. 全部成功，提交事务
                connection.commit();

                System.out.println("事务执行成功！");

            } catch (SQLException e) {

                // 5. 出错时，回滚事务
                connection.rollback();

                System.out.println("事务执行失败，已尝试回滚！");
                System.out.println("错误信息：" + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("数据库连接或事务处理失败！");
            System.out.println("错误信息：" + e.getMessage());
        }
    }
}