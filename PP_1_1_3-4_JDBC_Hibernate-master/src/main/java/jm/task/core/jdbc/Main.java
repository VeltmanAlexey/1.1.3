package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import static jm.task.core.jdbc.util.Util.getConnection;


public class Main {
    public static void main(String[] args) {

//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//
//        userDaoJDBC.createUsersTable();
//
//        userDaoJDBC.saveUser("Nikita","Mocochynin", (byte) 19);
//        userDaoJDBC.saveUser("Alexey","Veltman", (byte) 19);
//        userDaoJDBC.saveUser("Kristina","Egorova", (byte) 18);
//        userDaoJDBC.saveUser("Liza","Sudacova", (byte) 18);
//
//        userDaoJDBC.removeUserById(4);
//
//        userDaoJDBC.getAllUsers();
//        userDaoJDBC.cleanUsersTable();
//
//        userDaoJDBC.dropUsersTable();



        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Nikita","Mocochynin", (byte) 19);
        userDaoHibernate.saveUser("Alexey","Veltman", (byte) 19);
        userDaoHibernate.saveUser("Kristina","Egorova", (byte) 18);
        userDaoHibernate.saveUser("Liza","Sudacova", (byte) 18);

        userDaoHibernate.removeUserById(1);

        userDaoHibernate.getAllUsers();

        userDaoHibernate.cleanUsersTable();

        userDaoHibernate.dropUsersTable();
    }
}
