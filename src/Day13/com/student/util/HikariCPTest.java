package Day13.com.student.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class HikariCPTest {

    public static void main(String[] args) {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/student_db");
        config.setUsername("root");
        config.setPassword("123456");

        HikariDataSource dataSource = new HikariDataSource(config);

        try (Connection connection = dataSource.getConnection()) {
            System.out.println("数据库连接成功！");
        } catch (Exception e) {
            System.out.println("数据库连接失败！");
            System.out.println("错误信息：" + e.getMessage());
        }

        dataSource.close();
    }
}