package ui;

import services.AuthService;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    public LoginUI() {
        setTitle("Financial Tracker - Login");
        setSize(350, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel p = new JPanel(new GridLayout(0, 1, 10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();

        JButton login = new JButton("Login");
        JButton register = new JButton("Register");

        p.add(new JLabel("Username"));
        p.add(user);
        p.add(new JLabel("Password"));
        p.add(pass);
        p.add(login);
        p.add(register);

        login.addActionListener(e -> {
            if (AuthService.login(user.getText(), new String(pass.getPassword()))) {
                dispose();
                new MainFrame();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid login or account not registered");
            }
        });

        register.addActionListener(e -> {
            dispose();
            new RegisterUI();
        });

        add(p);
        setVisible(true);
    }
}
