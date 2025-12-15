package services;

import models.Transaction;
import java.util.ArrayList;

public class TrashService {
    public static ArrayList<Transaction> trash = new ArrayList<>();

    public static void moveToTrash(Transaction t) {
        trash.add(t);
    }

    public static void restore(int index) {
        TransactionService.transactions.add(trash.remove(index));
    }

    public static void deletePermanently(int index) {
        trash.remove(index);
    }
}
