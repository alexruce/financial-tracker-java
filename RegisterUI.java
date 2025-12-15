package ui;

import services.AuthService;

import javax.swing.*;
import java.awt.*;

public class RegisterUI extends JFrame {

    public RegisterUI() {
        setTitle("Financial Tracker - Register");
        setSize(350, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel p = new JPanel(new GridLayout(0, 1, 10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();
        JPasswordField confirm = new JPasswordField();

        JButton register = new JButton("Register");
        JButton back = new JButton("Back to Login");

        p.add(new JLabel("Username"));
        p.add(user);
        p.add(new JLabel("Password"));
        p.add(pass);
        p.add(new JLabel("Confirm Password"));
        p.add(confirm);
        p.add(register);
        p.add(back);

        register.addActionListener(e -> {
            String u = user.getText();
            String p1 = new String(pass.getPassword());
            String p2 = new String(confirm.getPassword());

            if (!p1.equals(p2)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match");
                return;
            }

            if (AuthService.register(u, p1)) {
                JOptionPane.showMessageDialog(this,
                        "Registration successful. Please log in.");
                dispose();
                new LoginUI();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Registration failed.\nPassword must be 6+ characters and no spaces.");
            }
        });

        back.addActionListener(e -> {
            dispose();
            new LoginUI();
        });

        add(p);
        setVisible(true);
    }
}
