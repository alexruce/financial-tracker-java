package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    JPanel content;

    public MainFrame() {
        setTitle("Financial Tracker");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        SidebarPanel sidebar = new SidebarPanel(this);
        content = new JPanel(new BorderLayout());

        add(sidebar, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        showDashboard();
        setVisible(true);
    }

    public void showDashboard() {
        content.removeAll();
        content.add(new DashboardPanel());
        content.revalidate();
        content.repaint();
    }

    public void showTransactions() {
        content.removeAll();
        content.add(new TransactionsPanel());
        content.revalidate();
        content.repaint();
    }

    public void showTrash() {
        content.removeAll();
        content.add(new TrashPanel());
        content.revalidate();
        content.repaint();
    }
}
