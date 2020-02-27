package service;

import org.mindrot.jbcrypt.BCrypt;
import user.User;
import user.MariaDbConstant;

import java.sql.*;

public class UserService {
    public static UserService instance = new UserService();

    private UserService() {
    }

    public void create(User user) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "INSERT INTO user(name,username,password) VALUES (?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.bCryptPassword());
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public User findUserById(int id) {
        User user = new User();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "SELECT password FROM user WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    public void update(int id, User user) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "UPDATE user SET name = ?,username = ?,password = ? WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.bCryptPassword());
                preparedStatement.setInt(4, id);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "DELETE FROM user WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean login(String username, String password) {
        User user = new User();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "SELECT password FROM user WHERE username = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user.setPassword(resultSet.getString("password"));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return BCrypt.checkpw(password, user.getPassword());
    }
}
