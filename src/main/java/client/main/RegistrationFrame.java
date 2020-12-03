package client.main;

import javax.swing.*;
import java.awt.*;

public class RegistrationFrame extends SocketJFrame {

    private final LoginFrame loginFrame;

    private JTextField loginField;
    private JTextField passwordField;
    private JTextField confirmPasswordField;
    private JButton submitButton;
    private JPanel mainPanel;
    private JButton backButton;

    public RegistrationFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Регистрация");
        setContentPane(mainPanel);
        initButtons();
    }

    private void initButtons() {
        submitButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            if (confirmPassword.equals(password)) {
                String query = "command=registration&login=" + login + "&password=" + password;
                String response = super.doRequest(query);
                if (response.startsWith("result=") && response.replace("result=", "").equals("true")) {
                    loginField.setBackground(Color.WHITE);
                    passwordField.setBackground(Color.WHITE);
                    confirmPasswordField.setBackground(Color.WHITE);
                    this.setVisible(false);
                    this.setEnabled(false);
                    loginFrame.setVisible(true);
                    loginFrame.setEnabled(true);
                } else {
                    loginField.setBackground(Color.RED);
                    passwordField.setBackground(Color.RED);
                }
            } else {
                confirmPasswordField.setBackground(Color.RED);
            }
        });
        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.setEnabled(false);
            loginFrame.setVisible(true);
            loginFrame.setEnabled(true);
        });
    }
}
