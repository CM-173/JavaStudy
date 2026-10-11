package Day13.com.student.util;

import java.sql.Connection;
import java.sql.SQLException;


public class TransactionManager {


    private static final ThreadLocal<Connection> LOCAL =
            new ThreadLocal<>();


    // 获取当前线程连接
    public static Connection getConnection() throws SQLException {

        Connection connection = LOCAL.get();

        if (connection == null) {

            connection = JDBCUtil.getConnection();

            LOCAL.set(connection);

        }

        return connection;
    }


    // 开启事务
    public static void begin() throws SQLException {

        Connection connection = getConnection();

        connection.setAutoCommit(false);

    }


    // 提交事务
    public static void commit() throws SQLException {

        Connection connection = LOCAL.get();

        if (connection != null) {

            connection.commit();

        }

    }


    // 回滚事务
    public static void rollback() throws SQLException {

        Connection connection = LOCAL.get();

        if (connection != null) {

            connection.rollback();

        }

    }


    // 关闭连接
    public static void close() throws SQLException {

        Connection connection = LOCAL.get();

        if (connection != null) {

            connection.close();

            LOCAL.remove();

        }

    }

}