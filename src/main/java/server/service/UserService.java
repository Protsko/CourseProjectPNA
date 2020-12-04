package server.service;

import server.dao.UserDao;
import client.entity.User;
import client.entity.UserRole;
import server.exception.DaoException;
import server.exception.ServiceException;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(String login, String password) throws ServiceException {
        User user = new User(null, login, password, UserRole.USER);
        try {
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException("User hasn't been added", e);
        }
    }

    public User loginUser(String login, String password) throws ServiceException {
        try {
            User user = userDao.getUserByLogin(login);
            if (user != null && password.equals(user.getPassword())) {
                return user;
            }
        } catch (DaoException e) {
            throw new ServiceException("User hasn't been found", e);
        }
        return null;
    }

    public User getUserById(Long id) throws ServiceException {
        try {
            return userDao.getUserById(id);
        } catch (DaoException e) {
            throw new ServiceException("User hasn't been found", e);
        }
    }
}