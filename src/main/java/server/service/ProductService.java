package server.service;

import server.dao.ProductDao;
import client.entity.Product;
import server.exception.DaoException;
import server.exception.ServiceException;

import java.util.List;

public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() throws ServiceException {
        try {
            return productDao.getProducts();
        } catch (DaoException e) {
            throw new ServiceException("Products haven't been found", e);
        }
    }

    public void deleteProduct(Integer id) throws ServiceException {
        try {
            productDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Product hasn't been deleted", e);
        }
    }
}
