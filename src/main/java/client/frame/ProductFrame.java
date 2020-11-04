package client.frame;

import client.entity.Product;
import client.entity.User;
import client.entity.UserRole;
import client.main.SocketJFrame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductFrame extends SocketJFrame {

    private final OrderFrame orderFrame = new OrderFrame();
    private final LoginFrame loginFrame;

    private DefaultListModel model = new DefaultListModel();
    private JPanel mainPanel;
    private JList productList;
    private JLabel userNameLabel = new JLabel();
    private JButton logoutButton = new JButton("logout");
    private JButton infoButton;
    private JButton toOrderButton;
    private JButton myOrdersButton;
    private JButton deleteButton;

    public ProductFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
        logoutButton.setUI(new BasicButtonUI());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Products");
        setContentPane(mainPanel);
        productList.setModel(model);
        fillProductList();
        initButtons();
        initJMenu();
    }

    private void initButtons() {
        logoutButton.addActionListener(e -> {
            super.logout();
            this.setVisible(false);
            this.setEnabled(false);
            loginFrame.setVisible(true);
            loginFrame.setEnabled(true);
        });
        infoButton.addActionListener(e -> {
            int selectedIndex = productList.getSelectedIndex();
            List<Product> currentProducts = super.getCurrentProducts();
            Product product = currentProducts.get(selectedIndex);
            JOptionPane.showMessageDialog(this, product.getDescription());
        });
        toOrderButton.addActionListener(e -> {
            int selectedIndex = productList.getSelectedIndex();
            String userId = super.getCurrentUser().getId().toString();
            List<Product> products = super.getCurrentProducts();
            Product currentProduct = products.get(selectedIndex);
            if(currentProduct.getAvailable() == false){
                JOptionPane.showMessageDialog(this, "Товара нет в наличии");
            }else {
                Product product = products.get(selectedIndex);
                String productId = product.getId().toString();
                String query = "command=order&user_id=" + userId + "&product_id=" + productId;
                String response = super.doRequest(query);
                if ("ok".equals(response.replace("code=", ""))) {
                    JOptionPane.showMessageDialog(this, "Заказ выполнен");
                } else {
                    JOptionPane.showMessageDialog(this, "Заказ не выполнен");
                }
            }
        });
        myOrdersButton.addActionListener(e -> {
            orderFrame.setVisible();
        });
        deleteButton.addActionListener(e -> {
            int selectedIndex = productList.getSelectedIndex();
            List<Product> currentProducts = super.getCurrentProducts();
            Product product = currentProducts.get(selectedIndex);
            super.doRequest("command=delete_product&id=" + product.getId());
            productList.remove(selectedIndex);
        });
    }

    private void fillProductList() {
        String response = super.doRequest("command=get_products");
        if (!response.equals("result=error") && !response.equals("")) {
            String[] products = response.split("&");
            List<Product> currentProducts = new ArrayList<>();
            for (String product : products) {
                String[] productFields = product.split(",");

                Integer id = Integer.parseInt(productFields[0].replace("id=", ""));
                String name = productFields[1].replace("name=", "");
                Double price = Double.parseDouble(productFields[2].replace("price=", ""));
                String description = productFields[3].replace("description=", "");
                Boolean isAvailable = Boolean.parseBoolean(productFields[4].replace("isAvailable=", ""));
                Product pr = new Product(id, name, price, description, isAvailable);
                currentProducts.add(pr);

                String elem = name + ". Price: " + price;
                if (isAvailable) {
                    elem += ". Is available!";
                } else {
                    elem += ". Not available!";
                }
                model.addElement(elem);
            }
            super.setCurrentProducts(currentProducts);
        }
    }

    private void initJMenu() {
        JMenuBar menuBar = new JMenuBar();
        LayoutManager layoutManager = new BorderLayout();
        menuBar.setLayout(layoutManager);
        menuBar.add(userNameLabel, BorderLayout.WEST);
        menuBar.add(logoutButton, BorderLayout.EAST);
        setJMenuBar(menuBar);
    }

    public void setVisible() {
        setVisible(true);
        User user = super.getCurrentUser();
        userNameLabel.setText("login: " + user.getLogin());
        if (user.getUserRole() == UserRole.USER) {
            deleteButton.setVisible(false);
            deleteButton.setEnabled(false);
        } else {
            deleteButton.setVisible(true);
            deleteButton.setEnabled(true);
        }
    }
}
