package ui;

import services.TransactionService;
import models.Transaction;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setBackground(new Color(10, 15, 30));
        setLayout(new GridLayout(1, 3, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        double income = 0;
        double expense = 0;

        for (Transaction t : TransactionService.transactions) {
            if (t.type.equals("Income")) income += t.amount;
            else expense += t.amount;
        }

        add(card("Income", "₱" + income, new Color(33, 150, 243)));
        add(card("Expenses", "₱" + expense, new Color(244, 67, 54)));
        add(card("Balance", "₱" + (income - expense), new Color(76, 175, 80)));
    }

    private JPanel card(String title, String value, Color color) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(color);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel t = new JLabel(title);
        t.setForeground(Color.WHITE);
        t.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel v = new JLabel(value);
        v.setForeground(Color.WHITE);
        v.setFont(new Font("Segoe UI", Font.BOLD, 26));

        p.add(t, BorderLayout.NORTH);
        p.add(v, BorderLayout.CENTER);
        return p;
    }
}
