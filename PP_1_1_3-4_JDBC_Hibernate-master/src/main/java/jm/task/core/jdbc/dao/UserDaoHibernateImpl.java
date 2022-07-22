package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import javax.persistence.Query;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;


public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory;
    Session session;

    private String createUsersTable = "CREATE TABLE IF NOT EXISTS `users` ("
            + "`id` BIGINT NOT NULL AUTO_INCREMENT,"
            + "`name` VARCHAR(45) NOT NULL,"
            + "`lastName` VARCHAR(45) NOT NULL,"
            + "`age` TINYINT NOT NULL,"
            + "PRIMARY KEY (`id`))";
    private String dropUsersTable = "DROP TABLE IF EXISTS Users";

    public UserDaoHibernateImpl() {


    }


    @Override
    public void createUsersTable() {
        sessionFactory = getConnection();
        session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.createSQLQuery(createUsersTable).executeUpdate();;

        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        sessionFactory = getConnection();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.createSQLQuery(dropUsersTable).executeUpdate();

        session.getTransaction().commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        sessionFactory = getConnection();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = new User(name, lastName, age);
        session.save(user);

        session.getTransaction().commit();

    }

    @Override
    public void removeUserById(long id) {
        sessionFactory = getConnection();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = session.get(User.class,id);
        if (user != null) {
            session.delete(user);
        }

        session.getTransaction().commit();

    }

    @Override
    public List<User> getAllUsers() {
        sessionFactory = getConnection();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<User> list = session.createQuery("select i from User i",User.class).getResultList();

        session.getTransaction().commit();

        return list;
    }

    @Override
    public void cleanUsersTable() {
        sessionFactory = getConnection();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete from User").executeUpdate();

        session.getTransaction().commit();

    }
}
