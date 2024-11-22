package ru.kpfu.itis.service;

import ru.kpfu.itis.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserService {

    public User getUser(HttpServletRequest req, HttpServletResponse res) {
        return (User) req.getSession().getAttribute("user");
    }

    public void authUser(User user, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getSession().setAttribute("user", user);
    }

    public void deleteUser(User user, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getSession().removeAttribute("user");
    }

}