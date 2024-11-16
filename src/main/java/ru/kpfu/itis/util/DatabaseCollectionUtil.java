package ru.kpfu.itis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseCollectionUtil {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/yourdb",
                        "yourusername",
                        "yourpassword"
                );
            } catch (SQLException e) {
                throw new RuntimeException("Failed to connect to the database", e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("PostgreSQL JDBC Driver not found", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close the database connection", e);
            } finally {
                connection = null;
            }
        }
    }
}
