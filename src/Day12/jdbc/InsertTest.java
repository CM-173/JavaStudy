package Day12.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsertTest {
    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db",
                "root",
                "123456"
        );
        //创建执行SQL对象
        Statement statement = connection.createStatement();
        //执行新增
        String sql = "insert into student values('004','赵六',21)";
        //读取结果
        int result = statement.executeUpdate(sql);
        System.out.println("影响行数：" + result);
        //关闭资源
        statement.close();
        connection.close();

    }
}
