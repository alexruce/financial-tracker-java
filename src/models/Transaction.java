package models;

public class Transaction {
    public String type;
    public String category;
    public String date;
    public double amount;
    public String note;

    public Transaction(String type, String category, String date, double amount, String note) {
        this.type = type;
        this.category = category;
        this.date = date;
        this.amount = amount;
        this.note = note;
    }
}
