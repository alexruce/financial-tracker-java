package services;

import models.Transaction;
import java.util.ArrayList;

public class TransactionService {
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void add(Transaction t) {
        transactions.add(t);
    }

    public static void delete(int index) {
        TrashService.moveToTrash(transactions.get(index));
        transactions.remove(index);
    }

    public static ArrayList<Transaction> search(String key) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions)
            if (t.category.toLowerCase().contains(key.toLowerCase()) ||
                t.note.toLowerCase().contains(key.toLowerCase()))
                result.add(t);
        return result;
    }

    public static void sortByAmount() {
        for (int i = 0; i < transactions.size(); i++)
            for (int j = 0; j < transactions.size() - 1; j++)
                if (transactions.get(j).amount > transactions.get(j + 1).amount) {
                    Transaction t = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, t);
                }
    }

    public static void sortByDate() {
        for (int i = 0; i < transactions.size(); i++)
            for (int j = 0; j < transactions.size() - 1; j++)
                if (transactions.get(j).date.compareTo(transactions.get(j + 1).date) > 0) {
                    Transaction t = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, t);
                }
    }
}
