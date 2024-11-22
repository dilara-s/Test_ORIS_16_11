package ru.kpfu.itis.util;

import ru.kpfu.itis.dao.UserDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Connection connection = DatabaseCollectionUtil.getConnection();
            sce.getServletContext().setAttribute("userDao", new UserDao(connection));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize context", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Connection connection = DatabaseCollectionUtil.getConnection();
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close database connection", e);
        }
    }
}

