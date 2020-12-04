package server.dao.mysql;

import server.dao.ConnectionManager;
import server.dao.SQLConstant;
import server.dao.UserDao;
import client.entity.User;
import client.entity.UserRole;
import server.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

public class MySQLUserDao implements UserDao {
    @Override
    public void save(User user) throws DaoException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = MessageFormat.format(SQLConstant.INSERT_USER, "user_login, user_password, user_role", "?, ?, ?");
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setInt(3, user.getUserRole().getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error in saving user", e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws DaoException {
        User user = null;
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQLConstant.SELECT_USER_LOGIN)) {
                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getLong("id"),
                            resultSet.getString("user_login"),
                            resultSet.getString("user_password"),
                            UserRole.getRoleById(resultSet.getInt("user_role"))
                    );
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error in getting user by user_login", e);
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws DaoException {
        User user = null;
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQLConstant.SELECT_USER_ID)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getLong("id"),
                            resultSet.getString("user_login"),
                            resultSet.getString("user_password"),
                            UserRole.getRoleById(resultSet.getInt("user_role"))
                    );
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error in getting user by user_login", e);
        }
        return user;
    }
}
