package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;
import static jm.task.core.jdbc.util.Util.getConnectionJDBS;

public class UserDaoJDBCImpl implements UserDao {
    private String createUsersTable = "CREATE TABLE IF NOT EXISTS `users` ("
                                   + "`id` BIGINT NOT NULL AUTO_INCREMENT,"
                                   + "`name` VARCHAR(45) NOT NULL,"
                                   + "`lastName` VARCHAR(45) NOT NULL,"
                                   + "`age` TINYINT NOT NULL,"
                                   + "PRIMARY KEY (`id`))";
    private String dropUsersTable = "DROP TABLE IF EXISTS `users`";
    private String saveUser = "INSERT INTO users(name, lastName, age) VALUES(?, ?, ?)";
    private String removeUserById = "DELETE FROM users WHERE id = ";
    private String getAllUsers = "select * from users";
    private String cleanUsersTable = "TRUNCATE TABLE users";
    private PreparedStatement preparedstatement = null;
    private Connection connection = null;
    private Statement statement = null;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement = getConnectionJDBS().createStatement();
            statement.executeUpdate(createUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void dropUsersTable() {
        try {
            statement = getConnectionJDBS().createStatement();
            statement.executeUpdate(dropUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            preparedstatement = getConnectionJDBS().prepareStatement(saveUser);
            preparedstatement.setString(1, name);
            preparedstatement.setString(2, lastName);
            preparedstatement.setByte(3, age);
            preparedstatement.executeUpdate();
            System.out.println("User с именем – "+ name +" добавлен в базу данных ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedstatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeUserById(long id) {
        try {
            statement = getConnectionJDBS().createStatement();
            statement.execute(removeUserById + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            statement = getConnectionJDBS().createStatement();
            ResultSet resultSet = statement.executeQuery(getAllUsers);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            statement = getConnectionJDBS().createStatement();
            statement.executeUpdate(cleanUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}