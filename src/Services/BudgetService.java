package Services;

import Models.Transaction;
import Services.Base.BaseService;

import java.util.HashMap;
import java.util.Map;

/**
 * BudgetService class needs to handle the following functionalities:
 * Enter a budget for each category.
 * Track the progress of the budget.
 */
public class BudgetService extends BaseService {
    private Map<String, Double> categoryBudgets;
    private Map<String, Double> categorySpendings;

    public BudgetService() {
        this.categoryBudgets = new HashMap<>();
        this.categorySpendings = new HashMap<>();
    }

    /**
     * Method is used to enter a budget for a specific category.
     * @param category
     * @param budget
     */
    public void enterBudget(String category, double budget) {
        categoryBudgets.put(category, budget);
    }

    /**
     * Method is used to calculate the total spending for each category.
     */
    public void trackProgressAgainstBudget() {
        for (Transaction transaction : dataManager.getTransactions()) {
            String category = transaction.getCategory();
            double amount = transaction.getAmount();
            categorySpendings.put(category, categorySpendings.getOrDefault(category, 0.0) + amount);
        }
    }

    /**
     * Method is used to get the remaining budget for a specific category.
     * @param category
     * @return
     */
    public double getRemainingBudget(String category) {
        return categoryBudgets.getOrDefault(category, 0.0) - categorySpendings.getOrDefault(category, 0.0);
    }
}
