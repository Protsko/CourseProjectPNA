package client.frame;

import client.entity.Order;
import client.entity.Product;
import client.main.SocketJFrame;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OrderFrame extends SocketJFrame {

    private DefaultListModel model = new DefaultListModel();
    private JPanel mainPanel;
    private JButton toPayButton;
    private JButton toCommentButton;
    private JList orderList;

    public OrderFrame() {
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setTitle("Login");
        setContentPane(mainPanel);
        orderList.setModel(model);
        initButtons();
    }

    private void fillOrders() {
        String id = super.getCurrentUser().getId().toString();
        String response = super.doRequest("command=get_orders&user_id=" + id);
        DefaultListModel listModel = (DefaultListModel) orderList.getModel();
        listModel.removeAllElements();
        if (!response.equals("result=error") && !response.equals("")) {
            String[] orders = response.split("&");
            List<Order> currentOrders = new ArrayList<>();
            for (String order : orders) {
                String[] productFields = order.split(",");

                Integer orderId = Integer.parseInt(productFields[0].replace("id=", ""));
                Long userId = Long.parseLong(productFields[1].replace("userId=", ""));
                Integer productId = Integer.parseInt(productFields[2].replace("productId=", ""));
                Boolean isPayed = Boolean.parseBoolean(productFields[3].replace("isPayed=", ""));
                Order or = new Order(orderId, userId, productId, isPayed);
                currentOrders.add(or);

                Product product = super.getOrderedProduct(productId);
                String elem = product.getName() + ". Price: " + product.getPrice();
                if (isPayed) {
                    elem += ". Is payed!";
                } else {
                    elem += ". Not payed!";
                }
                model.addElement(elem);
            }
            super.setCurrentOrders(currentOrders);
        }
    }



    private void initButtons() {
        toPayButton.addActionListener(e -> {
            int selectedIndex = orderList.getSelectedIndex();
            List<Order> orders = super.getCurrentOrders();
            Order order = orders.get(selectedIndex);
            String orderId = order.getId().toString();
            super.doRequest("command=pay_order&order_id=" + orderId);
        });
        toCommentButton.addActionListener(e -> {
            int selectedIndex = orderList.getSelectedIndex();
            List<Order> orders = super.getCurrentOrders();
            Order order = orders.get(selectedIndex);
            String orderId = order.getId().toString();

        });
    }

    public void setVisible() {
        fillOrders();
        this.setVisible(true);
    }
}
