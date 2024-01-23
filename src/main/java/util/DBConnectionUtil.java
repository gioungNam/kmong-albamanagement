package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.DBConfig;

public class DBConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }

      public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            DBConfig.DB_URL,
            DBConfig.DB_USER,
            DBConfig.DB_PASSWORD
        );
    }
}
