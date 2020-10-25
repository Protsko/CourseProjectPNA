package server.service;

import server.dao.OrderDao;
import client.entity.Order;
import server.exception.DaoException;
import server.exception.ServiceException;

import java.util.List;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void addOrder(Long userId, Integer productId) throws ServiceException {
        Order order = new Order(null, userId, productId, false);
        try {
            orderDao.save(order);
        } catch (DaoException e) {
            throw new ServiceException("Order hasn't been added", e);
        }
    }

    public List<Order> getOrders(Long userId) throws ServiceException {
        try {
            return orderDao.getOrdersByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Orders haven't been found", e);
        }
    }

    public void payOrder(Integer orderId) throws ServiceException {
        try {
            orderDao.update(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Order status hasn't been updated", e);
        }
    }
}
