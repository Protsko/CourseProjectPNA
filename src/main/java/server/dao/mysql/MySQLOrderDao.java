package server.dao.mysql;

import server.dao.ConnectionManager;
import server.dao.OrderDao;
import server.dao.SQLConstant;
import client.entity.Order;
import server.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrderDao implements OrderDao {
    @Override
    public void save(Order order) throws DaoException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = MessageFormat.format(SQLConstant.INSERT_ORDER, "user_account_id, product_id, is_payed", "?, ?, ?");
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, order.getUserId());
                statement.setInt(2, order.getProductId());
                statement.setBoolean(3, order.getPayed());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error in saving order", e);
        }
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQLConstant.SELECT_ORDER)) {
                statement.setLong(1, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Order order = new Order(
                            resultSet.getInt("id"),
                            resultSet.getLong("user_account_id"),
                            resultSet.getInt("product_id"),
                            resultSet.getBoolean("is_payed")
                    );
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error in getting orders by user_id", e);
        }
        return orders;
    }

    @Override
    public void update(Integer orderId) throws DaoException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQLConstant.UPDATE_ORDER)) {
                statement.setInt(1, orderId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error in deleting order", e);
        }
    }

    @Override
    public void delete(Integer orderId) throws DaoException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQLConstant.DELETE_ORDER)) {
                statement.setInt(1, orderId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error in deleting order", e);
        }
    }
}
