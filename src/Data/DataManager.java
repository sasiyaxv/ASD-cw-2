package Data;

import Models.Category;
import Models.Transaction;
import Models.TransactionType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static volatile DataManager instance;

    private DataManager() {
        this.categories = readCategoriesFromCSV();
        this.transactions = readTransactionsFromFile();
    }


    public static DataManager getInstance() {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager();
                }
            }
        }
        return instance;
    }

    private List<Category> categories = new ArrayList<>();

    private List<Transaction> transactions = new ArrayList<>();

    public List<Category> getCategories() {
        return categories;
    }

    public Category addCategories(Category category) {
        categories.add(category);
        return category;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    private List<Category> readCategoriesFromCSV() {
        List<Category> categories = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/data.csv"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String name = parts[0].trim();
                String description = parts[1].trim();

                Category category = new Category();
                category.setName(name);
                category.setDescription(description);
                categories.add(category);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categories;
    }

    private List<Transaction> readTransactionsFromFile() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }

                int transactionId = Integer.parseInt(parts[0]);
                double amount = Double.parseDouble(parts[1]);
                String category = parts[2];
                TransactionType type = TransactionType.valueOf(parts[3]);
                String note = parts[4];
                boolean isRecurring = Boolean.parseBoolean(parts[5]);

                Transaction transaction = new Transaction();
                transaction.setTransactionId(transactionId);
                transaction.setType(type);
                transaction.setRecurring(isRecurring);
                transaction.setAmount(amount);
                transaction.setNote(note);
                transaction.setCategory(category);

                transactions.add(transaction);
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
