package ru.kpfu.itis.servlet;

import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "usersServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if (login != null) {
            req.setAttribute("users", List.of(userService.getByLogin(login)));
        } else {
            req.setAttribute("users", userService.getAll());
        }
        req.getRequestDispatcher("users.ftl").forward(req, resp);
    }
}