package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dao.UserDao;
import ru.kpfu.itis.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletConfig().getServletContext().getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        try {
            User user = new User(login, password, email);

            boolean isAdded = userDao.addUser(user);

            userDao.logLoginAttempt(login, isAdded);

            if (isAdded) {
                resp.sendRedirect("weather.jsp");
            } else {
                req.setAttribute("error", "Registration failed");
                getServletContext().getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            try {
                userDao.logLoginAttempt(login, false);
            } catch (Exception logException) {
                throw new ServletException(logException);
            }
            throw new ServletException("Error during registration process", e);
        }
    }
}
