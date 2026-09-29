package Day12.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectTest {
    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db",
                "root",
                "123456"
        );
        //创建执行SQL对象
        Statement statement = connection.createStatement();
        //执行查询
        ResultSet resultSet = statement.executeQuery("select * from student");
        //读取结果
        while (resultSet.next()) {
            String id = resultSet.getString("id");

            String name = resultSet.getString("name");

            int age = resultSet.getInt("age");

            System.out.println(id + " " + name + " " + age);
        }

        //关闭资源
        resultSet.close();
        statement.close();
        connection.close();

    }
}
