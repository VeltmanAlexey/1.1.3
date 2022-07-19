package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Nikita","Mocochynin", (byte) 19);
        userDaoJDBC.saveUser("Alexey","Veltman", (byte) 19);
        userDaoJDBC.saveUser("Kristina","Egorova", (byte) 18);
        userDaoJDBC.saveUser("Liza","Sudacova", (byte) 18);
        userDaoJDBC.removeUserById(4);

        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();

        userDaoJDBC.dropUsersTable();
       // Util util = new Util();
      //  util.getConnectionHibernate();
    }
}
