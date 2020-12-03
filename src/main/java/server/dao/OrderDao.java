package server.dao;

import client.entity.Order;
import server.exception.DaoException;

import java.util.List;

public interface OrderDao {
    void save(Order order) throws DaoException;

    List<Order> getOrdersByUserId(Long userId) throws DaoException;

    void update(Integer orderId) throws DaoException;

    void delete(Integer productId) throws DaoException;
}
