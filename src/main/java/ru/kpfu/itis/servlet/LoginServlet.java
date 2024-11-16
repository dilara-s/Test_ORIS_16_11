package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dao.Impl.UserDaoImpl;
import ru.kpfu.itis.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDaoImpl userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userDao.getUserByUsername(login);
        if (user != null && user.getPassword().equals(password)) {
            userDao.logLoginAttempt(login, true);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("welcome.jsp");
        } else {
            userDao.logLoginAttempt(login, false);
            response.sendRedirect("index.jsp?error=true");
        }
    }
}
