package Data;

import Models.Budget;
import Models.Category;
import Models.Transaction;

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
}
