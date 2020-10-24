package server.dao;

import client.entity.User;
import server.exception.DaoException;

public interface UserDao {
    void save(User user) throws DaoException;

    User getUserByLogin(String login) throws DaoException;
}
