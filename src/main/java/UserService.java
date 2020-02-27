import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class UserService {
    public static UserService instance = new UserService();

    private UserService() {
    }

    public void create(User user) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "INSERT INTO user(name,surname,password) VALUES (?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            System.out.println("!!!!!!");
        }
    }

    public User findUserById(int id) {
        User user = new User();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "SELECT * FROM user WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user.setName(((ResultSet) resultSet).getString("name"));
                    user.setUsername(resultSet.getString("surname"));
                }
                resultSet.close();
                return user;
            }
        } catch (SQLException ex) {
            System.out.println("!!!!!");
        }
        return null;
    }

    public void update(int id, User user) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "UPDATE user SET name = ?,surname = ? WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setInt(3, id);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            System.out.println("!!!!!");
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
            System.out.println("!!!!!");
        }
    }

    public boolean login(String username, String password) {
        User user = new User();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {
            if (conn != null) {
                String query = "SELECT password FROM user WHERE surname = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                }
                resultSet.close();
            }
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("!!!!!!");
        }
        return false;
    }
}
