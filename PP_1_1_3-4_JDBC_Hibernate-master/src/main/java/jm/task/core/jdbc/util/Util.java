package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import javax.imageio.spi.ServiceRegistry;
import java.lang.module.Configuration;
import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/users?serverTimezone=Europe/Moscow";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnectionJDBS() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

   // public static SessionFactory getConnectionHibernate() {
//
   // }


}