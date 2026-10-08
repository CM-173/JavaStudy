package Day13.com.student.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    private static final HikariDataSource DATA_SOURCE;
    static {
        try {
            Properties properties = new Properties();

            InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");

            properties.load(inputStream);

            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(
                    properties.getProperty("jdbc.url")
            );
            config.setUsername(
                    properties.getProperty("jdbc.username")
            );
            config.setPassword(
                    properties.getProperty("jdbc.password")
            );
            DATA_SOURCE = new HikariDataSource(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection()
            throws SQLException {
        return DATA_SOURCE.getConnection();
    }
}