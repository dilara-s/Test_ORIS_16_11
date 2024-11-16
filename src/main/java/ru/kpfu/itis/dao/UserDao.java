package ru.kpfu.itis.dao;

import ru.kpfu.itis.entity.User;

import java.util.List;

public interface UserDao {

    User get(Integer id);

    User getByLogin(String login);

    List<User> getAll();

    void save(User user);

    User getUserByUsername(String username);
}
