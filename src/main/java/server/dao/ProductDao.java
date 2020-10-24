package server.dao;

import client.entity.Product;
import server.exception.DaoException;

import java.util.List;

public interface ProductDao {
    void save(Product product) throws DaoException;

    List<Product> getProducts() throws DaoException;

    void delete(Integer productId) throws DaoException;
}
