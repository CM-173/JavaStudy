package Day12.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionTest {

    public static void main(String[] args) throws Exception {


        String url =
                "jdbc:mysql://localhost:3306/student_db";


        String username = "root";


        String password = "123456";


        Connection connection =
                DriverManager.getConnection(
                        url,
                        username,
                        password
                );


        System.out.println("数据库连接成功");


        connection.close();

    }
}