package ui;

import models.Transaction;
import services.TrashService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TrashPanel extends JPanel {

    DefaultTableModel model;
    JTable table;

    public TrashPanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{
                "Type", "Category", "Date", "Amount (₱)", "Note"
        }, 0);

        table = new JTable(model);
        refresh();

        JButton restore = new JButton("Restore");
        JButton delete = new JButton("Delete Permanently");

        JPanel top = new JPanel();
        top.add(restore);
        top.add(delete);

        restore.addActionListener(e -> restore());
        delete.addActionListener(e -> deletePermanent());

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void restore() {
        int row = table.getSelectedRow();
        if (row == -1) return;
        TrashService.restore(row);
        refresh();
    }

    private void deletePermanent() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int confirm = JOptionPane.showConfirmDialog(this,
                "Permanently delete this transaction?",
                "Warning",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            TrashService.deletePermanently(row);
            refresh();
        }
    }

    private void refresh() {
        model.setRowCount(0);
        for (Transaction t : TrashService.trash) {
            model.addRow(new Object[]{
                    t.type, t.category, t.date, "₱" + t.amount, t.note
            });
        }
    }
}
