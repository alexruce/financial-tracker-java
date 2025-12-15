package ui;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel(MainFrame frame) {
        setPreferredSize(new Dimension(200, 600));
        setBackground(new Color(15, 20, 40));
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new GridLayout(4, 1, 10, 10));
        top.setBackground(getBackground());
        top.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Financial Tracker");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton dash = new JButton("Dashboard");
        JButton trans = new JButton("Transactions");
        JButton trash = new JButton("Trash");

        top.add(title);
        top.add(dash);
        top.add(trans);
        top.add(trash);

        dash.addActionListener(e -> frame.showDashboard());
        trans.addActionListener(e -> frame.showTransactions());
        trash.addActionListener(e -> frame.showTrash());

        JPanel bottom = new JPanel();
        bottom.setBackground(getBackground());
        JButton logout = new JButton("Log out");
        logout.addActionListener(e -> {
            frame.dispose();
            new LoginUI();
        });

        bottom.add(logout);

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
    }
}
