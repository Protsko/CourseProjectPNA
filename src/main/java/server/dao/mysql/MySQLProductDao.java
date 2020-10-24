package server.dao.mysql;

import server.dao.ConnectionManager;
import server.dao.ProductDao;
import server.dao.SQLConstant;
import client.entity.Product;
import server.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductDao implements ProductDao {
    @Override
    public void save(Product product) throws DaoException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            String sql = MessageFormat.format(SQLConstant.INSERT_PRODUCT, "product_name, product_price, product_description, is_available", "?, ?, ?, ?");
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, product.getName());
                statement.setDouble(2, product.getPrice());
                statement.setString(3, product.getDescription());
                statement.setBoolean(4, product.getAvailable());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error in saving product", e);
        }
    }

    @Override
    public List<Product> getProducts() throws DaoException {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQLConstant.SELECT_PRODUCT)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("product_name"),
                            resultSet.getDouble("product_price"),
                            resultSet.getString("product_description"),
                            resultSet.getBoolean("is_available")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error in getting products", e);
        }
        return products;
    }

    @Override
    public void delete(Integer productId) throws DaoException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQLConstant.DELETE_PRODUCT)) {
                statement.setInt(1, productId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("Error in deleting product", e);
        }
    }
}
