package ui;

import models.Transaction;
import services.TransactionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class TransactionsPanel extends JPanel {

    DefaultTableModel model;
    JTable table;

    public TransactionsPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{
                "Type", "Category", "Date", "Amount (₱)", "Note"
        }, 0);

        table = new JTable(model);
        refresh();

        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        JButton sortAmt = new JButton("Sort Amount");
        JButton sortDate = new JButton("Sort Date");

        JPanel top = new JPanel();
        top.add(add);
        top.add(delete);
        top.add(sortAmt);
        top.add(sortDate);

        add.addActionListener(e -> addTransaction());
        delete.addActionListener(e -> deleteTransaction());
        sortAmt.addActionListener(e -> {
            TransactionService.sortByAmount();
            refresh();
        });
        sortDate.addActionListener(e -> {
            TransactionService.sortByDate();
            refresh();
        });

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void addTransaction() {
        JTextField amount = new JTextField();
        JTextField note = new JTextField();
        JComboBox<String> type = new JComboBox<>(new String[]{"Income", "Expense"});
        JComboBox<String> cat = new JComboBox<>(new String[]{
                "Food", "Transport", "Bills", "Entertainment", "Others"
        });
        JTextField other = new JTextField();

        JPanel p = new JPanel(new GridLayout(0, 1));
        p.add(new JLabel("Type")); p.add(type);
        p.add(new JLabel("Category")); p.add(cat);
        p.add(new JLabel("If Others")); p.add(other);
        p.add(new JLabel("Amount")); p.add(amount);
        p.add(new JLabel("Note")); p.add(note);

        int res = JOptionPane.showConfirmDialog(this, p,
                "Add Transaction", JOptionPane.OK_CANCEL_OPTION);

        if (res == JOptionPane.OK_OPTION) {
            String category = cat.getSelectedItem().equals("Others")
                    ? other.getText()
                    : cat.getSelectedItem().toString();

            TransactionService.add(new Transaction(
                    type.getSelectedItem().toString(),
                    category,
                    LocalDate.now().toString(),
                    Double.parseDouble(amount.getText()),
                    note.getText()
            ));
            refresh();
        }
    }

    private void deleteTransaction() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int confirm = JOptionPane.showConfirmDialog(this,
                "Move transaction to trash?",
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            TransactionService.delete(row);
            refresh();
        }
    }

    private void refresh() {
        model.setRowCount(0);
        for (Transaction t : TransactionService.transactions) {
            model.addRow(new Object[]{
                    t.type, t.category, t.date, "₱" + t.amount, t.note
            });
        }
    }
}
