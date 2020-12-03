package client.main;

import client.entity.User;
import client.entity.UserRole;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends SocketJFrame {

    private final RegistrationFrame registrationFrame = new RegistrationFrame(this);
    private final ProductFrame productFrame = new ProductFrame(this);

    private JTextField loginTextField;
    private JPanel mainPanel;
    private JTextField passwordTextField;
    private JButton confirmButton;
    private JButton registrationButton;

    public LoginFrame() {
        registrationFrame.setEnabled(false);
        productFrame.setEnabled(false);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Login");
        setContentPane(mainPanel);
        setVisible(true);
        initButtons();
    }

    private void initButtons() {
        confirmButton.addActionListener(e -> {
            String login = loginTextField.getText();
            String password = passwordTextField.getText();
            String query = "command=login&login=" + login + "&password=" + password;
            String response = super.doRequest(query);
            if (response.startsWith("result=") && !response.replace("result=", "").equals("false")) {
                String[] respParams = response.split("&");
                User currentUser = new User(
                        Long.parseLong(respParams[1].replace("id=", "")),
                        respParams[2].replace("login=", ""),
                        respParams[3].replace("password=", ""),
                        UserRole.valueOf(respParams[4].replace("role=", ""))
                );
                loginTextField.setBackground(Color.WHITE);
                passwordTextField.setBackground(Color.WHITE);
                super.login(currentUser);
                this.setVisible(false);
                this.setEnabled(false);
                productFrame.setVisible();
                productFrame.setEnabled(true);
            } else {
                loginTextField.setBackground(Color.RED);
                passwordTextField.setBackground(Color.RED);
            }
        });
        registrationButton.addActionListener(e -> {
            this.setVisible(false);
            this.setEnabled(false);
            registrationFrame.setVisible(true);
            registrationFrame.setEnabled(true);
        });
    }
}
