package Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private int transactionId;
    private double amount;
    private String category;
    private TransactionType type;
    private String note;
    private boolean isRecurring;

    public Transaction() {
    }

    public Transaction(int transactionId, double amount, String category, TransactionType type, String note, boolean isRecurring) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.note = note;
        this.isRecurring = isRecurring;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public List<Transaction> readTransactionsFromCSV(String filePath) {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                int transactionId = Integer.parseInt(parts[0].trim());
                double amount = Double.parseDouble(parts[1].trim());
                String category = parts[2].trim();
                TransactionType type = TransactionType.valueOf(parts[3].trim());
                String note = parts[4].trim();
                boolean isRecurring = Boolean.parseBoolean(parts[5].trim());

                Transaction transaction = new Transaction(transactionId, amount, category, type, note, isRecurring);
                transactions.add(transaction);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return transactions;
    }



}

