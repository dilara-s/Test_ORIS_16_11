package ru.kpfu.itis.dao.Impl;

import ru.kpfu.itis.dao.UserDao;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.util.DatabaseCollectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final Connection connection = DatabaseCollectionUtil.getConnection();

    @Override
    public User get(Integer id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User getByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("lastname"),
                                resultSet.getString("login"),
                                resultSet.getString("password")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (name, lastname, login, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void logLoginAttempt(String username, boolean success) {
        String sql = "INSERT INTO login_attempts (username, success) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setBoolean(2, success);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
