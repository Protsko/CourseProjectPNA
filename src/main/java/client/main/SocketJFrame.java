package client.main;

import client.entity.Order;
import client.entity.Product;
import client.entity.User;

import javax.swing.*;
import java.util.List;

public class SocketJFrame extends JFrame {
    private final ApplicationContext appContext = ApplicationContext.getInstance();

    protected String doRequest(String query) {
        return RequestHandler.doRequest(query);
    }

    protected void login(User user) {
        appContext.login(user);
    }

    protected List<Product> getCurrentProducts() {
        return appContext.getCurrentProducts();
    }

    protected List<Order> getCurrentOrders() {
        return appContext.getCurrentOrders();
    }

    protected Product getOrderedProduct(Integer productId) {
        List<Product> products = appContext.getCurrentProducts();
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    protected User getCurrentUser() {
        return appContext.getCurrentUser();
    }

    protected void setCurrentProducts(List<Product> currentProducts) {
        appContext.setCurrentProducts(currentProducts);
    }

    protected void setCurrentOrders(List<Order> currentOrders) {
        appContext.setCurrentOrders(currentOrders);
    }

    protected void logout() {
        appContext.logout();
    }


}
