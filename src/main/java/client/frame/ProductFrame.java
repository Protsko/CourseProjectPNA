package client.frame;

import client.main.SocketJFrame;

import javax.swing.*;

public class ProductFrame extends SocketJFrame {

    private final OrderFrame orderFrame = new OrderFrame();
    private final LoginFrame loginFrame = new LoginFrame();

    private DefaultListModel model = new DefaultListModel();
    private JPanel mainPanel;
    private JList productList;
    private JLabel userNameLabel = new JLabel();
    private JButton loginButton = new JButton("Вход");
    private JButton logoutButton = new JButton("Выход");
    private JButton infoButton;
    private JButton toOrderButton;
    private JButton myOrdersButton;
    private JButton deleteButton;


}
