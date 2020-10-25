package client.frame;

import client.entity.User;
import client.entity.UserRole;

import javax.swing.*;
import java.awt.*;

public class LoginFrame {
    private final RegistrationFrame registrationFrame = new RegistrationFrame(this);
    private final ProductFrame productFrame = new ProductFrame(this);

    private JTextField loginTextField;
    private JPanel mainPanel;
    private JTextField passwordTextField;
    private JButton confirmButton;
    private JButton registrationButton;

}
