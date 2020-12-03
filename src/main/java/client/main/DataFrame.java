package client.main;

import client.main.*;
import client.entity.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DataFrame extends SocketJFrame {

    private DefaultListModel model = new DefaultListModel();
    private JPanel mainPanel;
    private JList productList;
    private JTextField nameField;
    private JTextField priceField;
    private JTextArea descriptionArea;
    private JCheckBox isAvailableCheckBox;
    private JButton addButton;
    private JButton deleteButton;

    public DataFrame() {
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setTitle("Work with data");
        setContentPane(mainPanel);
        productList.setModel(model);
        initButtons();
    }

    private void initButtons(){
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String price = priceField.getText();
            String description = descriptionArea.getText();
            boolean isAvailable = false;
            if(isAvailableCheckBox.isSelected()){
                isAvailable = true;
            }
            String query = "command=add_product&product_name=" + name + "&product_price="
                    + price + "&product_description=" + description + "&is_available=" + isAvailable;
            String response = super.doRequest(query);
            if (response.startsWith("result=") && response.replace("result=", "").equals("true")) {
                nameField.setBackground(Color.WHITE);
                priceField.setBackground(Color.WHITE);
                descriptionArea.setBackground(Color.WHITE);
                JOptionPane.showMessageDialog(this, "Товар добавлен");
            } else {
                nameField.setBackground(Color.RED);
                priceField.setBackground(Color.RED);
                descriptionArea.setBackground(Color.RED);
            }
            fillProductList();
        });
        deleteButton.addActionListener(e -> {
            int selectedIndex = productList.getSelectedIndex();
            java.util.List<Product> currentProducts = super.getCurrentProducts();
            Product product = currentProducts.get(selectedIndex);
            super.doRequest("command=delete_product&id=" + product.getId());
            currentProducts.remove(selectedIndex);
            model.remove(selectedIndex);
        });
    }

    private void fillProductList() {
        model.removeAllElements();
        List<Product> currentProducts = getCurrentProducts();
        currentProducts.forEach(product -> {
            String elem = product.getName() + ". Price: " + product.getPrice();
            if (product.getAvailable()) {
                elem += ". Is available!";
            } else {
                elem += ". Not available!";
            }
            model.addElement(elem);
        });
    }

    public void setVisible() {
        this.setVisible(true);
        model.removeAllElements();
        fillProductList();
    }
}

