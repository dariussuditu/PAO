package DataBaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Try to get the database connection
            connection = DatabaseConfiguration.getDatabaseConnection();
            if (connection != null && !connection.isClosed()) {
                System.out.println("Database connection established successfully.");
            } else {
                System.out.println("Failed to establish database connection.");
            }
        } catch (Exception e) {
            // Print any exceptions that occur
            e.printStackTrace();
        } finally {
            // Ensure the database connection is closed
            if (connection != null) {
                try {
                    DatabaseConfiguration.closeDatabaseConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }




        CreateTables createTables = new CreateTables();
        createTables.createTablePersoana();
        createTables.createTableContBancar();
        createTables.createTableTranzactie();
        createTables.createTableCard();
    }
}
