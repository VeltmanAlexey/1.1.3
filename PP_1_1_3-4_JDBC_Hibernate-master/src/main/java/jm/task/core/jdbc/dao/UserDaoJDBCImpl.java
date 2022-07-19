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
            connection = getConnectionJDBS();
            statement = connection.createStatement();
            statement.executeUpdate(createUsersTable);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void dropUsersTable() {
        try {
            connection = getConnectionJDBS();
            statement = connection.createStatement();
            statement.executeUpdate(dropUsersTable);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection = getConnectionJDBS();
            preparedstatement = connection.prepareStatement(saveUser);
            preparedstatement.setString(1, name);
            preparedstatement.setString(2, lastName);
            preparedstatement.setByte(3, age);
            preparedstatement.executeUpdate();
            System.out.println("User с именем – "+ name +" добавлен в базу данных ");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                preparedstatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeUserById(long id) {
        try {
            connection = getConnectionJDBS();
            statement = connection.createStatement();
            statement.execute(removeUserById + id);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            connection = getConnectionJDBS();
            statement = connection.createStatement();
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
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            connection = getConnectionJDBS();
            statement = connection.createStatement();
            statement.executeUpdate(cleanUsersTable);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}