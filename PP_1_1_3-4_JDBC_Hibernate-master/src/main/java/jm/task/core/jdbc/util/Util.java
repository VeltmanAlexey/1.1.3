package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;


import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/users?serverTimezone=Europe/Moscow";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

     public static Connection getConnectionJDBS() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getConnection() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty(Environment.URL, URL)
                        .setProperty(Environment.USER, USER)
                        .setProperty(Environment.PASS, PASSWORD)
                        .setProperty(Environment.HBM2DDL_AUTO, "create-drop")
                        .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect")
                        .setProperty(Environment.SHOW_SQL, "true")
                        .setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
                        .addAnnotatedClass(User.class);
                ServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(registry);

            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}