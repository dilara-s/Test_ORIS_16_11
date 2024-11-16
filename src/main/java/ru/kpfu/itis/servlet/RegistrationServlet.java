package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dao.UserDao;
import ru.kpfu.itis.dao.Impl.UserDaoImpl;
import ru.kpfu.itis.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = new User(name, lastname, login, password);

        try {
            userDao.save(user);
            response.sendRedirect("index.jsp"); // Перенаправление на страницу авторизации после успешной регистрации
        } catch (Exception e) {
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
