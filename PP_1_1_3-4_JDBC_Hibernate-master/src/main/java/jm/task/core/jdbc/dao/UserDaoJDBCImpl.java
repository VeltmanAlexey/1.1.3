package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();
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
    private Statement statement = null;
    private PreparedStatement preparedstatement = null;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement = util.getConnection().createStatement();
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
            statement = util.getConnection().createStatement();
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
            preparedstatement = util.getConnection().prepareStatement(saveUser);
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
            statement = util.getConnection().createStatement();
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
            statement = util.getConnection().createStatement();
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
            statement = util.getConnection().createStatement();
            statement.executeUpdate(cleanUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
