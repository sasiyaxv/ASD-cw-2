package Data;

import Models.Budget;
import Models.Category;
import Models.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private Budget budget;

    private List<Category> categories;

    private List<Transaction> transactions;

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public List<Category> getCategories() {
        return categories;
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

    public List<Category> readCategoriesFromCSV(String filePath) {
        List<Category> categories = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

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
}
