package DataBaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConfiguration {



    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/bank";
    private static final String USER = "root";
    private static final String PASSWORD = "cacatul12";
    private static Connection connection;

    private DatabaseConfiguration() {
    }

    public static Connection getDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeDatabaseConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}