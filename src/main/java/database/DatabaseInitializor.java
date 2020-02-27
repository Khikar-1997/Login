package database;

import user.MariaDbConstant;

import java.sql.*;

public class DatabaseInitializor {
    public static DatabaseInitializor instance = new DatabaseInitializor();

    private DatabaseInitializor() {
    }

    public void createDatabase() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String queryDatabase = "CREATE DATABASE user";
            PreparedStatement preparedStatement = conn.prepareStatement(queryDatabase);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void createTable() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            String query = "USE user;" +
                    "CREATE TABLE user" +
                    "(" +
                    "id INT AUTO_INCREMENT NOT NULL ," +
                    "name VARCHAR(255) NOT NULL ," +
                    "username VARCHAR(255) NOT NULL ," +
                    "password VARCHAR(255) NOT NULL, " +
                    "PRIMARY KEY (id)" +
                    ");";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            conn.commit();
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }
}
