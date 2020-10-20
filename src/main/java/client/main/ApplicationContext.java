package client.main;

import client.entity.Order;
import client.entity.Product;
import client.entity.User;

import java.util.List;

public class ApplicationContext {
    private static ApplicationContext instance;

    private User currentUser;
    private List<Product> currentProducts;
    private List<Order> currentOrders;

    private ApplicationContext() {
    }

    public void login(User user) {
        currentUser = user;
    }

    public void setCurrentProducts(List<Product> currentProducts) {
        this.currentProducts = currentProducts;
    }

    public void setCurrentOrders(List<Order> currentOrders) {
        this.currentOrders = currentOrders;
    }

    public List<Product> getCurrentProducts() {
        return currentProducts;
    }

    public List<Order> getCurrentOrders() {
        return currentOrders;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }
}
