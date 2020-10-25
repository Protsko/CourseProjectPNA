package server.app;

import server.dao.OrderDao;
import server.dao.ProductDao;
import server.dao.UserDao;
import server.dao.mysql.MySQLOrderDao;
import server.dao.mysql.MySQLProductDao;
import server.dao.mysql.MySQLUserDao;
import server.service.OrderService;
import server.service.ProductService;
import server.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class AppContext {

    private static AppContext instance;

    private final Map<Class<?>, Object> beans;

    private AppContext() {
        UserDao userDao = new MySQLUserDao();
        ProductDao productDao = new MySQLProductDao();
        OrderDao orderDao = new MySQLOrderDao();
        UserService userService = new UserService(userDao);
        ProductService productService = new ProductService(productDao);
        OrderService orderService = new OrderService(orderDao);

        beans = new HashMap<>();
        addBean(UserService.class, userService);
        addBean(ProductService.class, productService);
        addBean(OrderService.class, orderService);
    }

    public void addBean(Class<?> clazz, Object obj) {
        if (obj.getClass() == clazz) {
            beans.put(clazz, obj);
        } else {
            throw new IllegalStateException();
        }
    }

    public Object getBean(Class<?> clazz) {
        return beans.get(clazz);
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }
}