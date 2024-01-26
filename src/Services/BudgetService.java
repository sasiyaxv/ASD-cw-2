package Services;

import Models.Category;
import Models.Transaction;
import Models.TransactionType;
import Services.Base.BaseService;

import java.util.*;

/**
 * BudgetService class needs to handle the following functionalities:
 * Enter a budget for each category.
 * Track the progress of the budget.
 */
public class BudgetService extends BaseService {
    private List<Category> categories;

    public BudgetService() {
        super();
        this.categories = dataManager.getCategories();
    }

    public void trackProgressAgainstBudget () {
        printBudgetList();
        printTotalBudget();
    }

    // Implement a method to print the category budget list if the budget is set
    public void printBudgetList() {
        for (Category categoryObj : categories) {
            if (categoryObj.getBudget() != 0) {
                System.out.println("Category: " + categoryObj.getName() + " Budget: " + categoryObj.getBudget());
            }
        }
    }

    // Implement a method to print the total budget
    public void printTotalBudget() {
        double totalBudget = 0;
        for (Category categoryObj : categories) {
            totalBudget += categoryObj.getBudget();
        }
        System.out.println("Total Budget: " + totalBudget);
    }

    /**
     * Method is used to enter a budget for a specific category.
     */
    public void enterBudget() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the category for the budget:");
        String category = scanner.nextLine();

        // Check if the category exists
        boolean categoryExists = false;
        for (Category categoryObj : categories) {
            if (categoryObj.getName().equalsIgnoreCase(category)) {
                categoryExists = true;
                // Create a new category object
                System.out.println("The category exists.");
                System.out.println("Enter the budget amount:");
                double budget = scanner.nextDouble();

                categoryObj.setBudget(budget);
                break;
            }
        }
        // If the category does not exist, then ask the user to enter a valid category
        if (!categoryExists) {
            System.out.println("The category does not exist. Please enter a valid category.");
            this.enterBudget();
        }
    }

    /**
     * Method is used to view the remaining budget for a specific category.
     */
    public void viewRemainingBudget() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the category for the budget:");
        String category = scanner.nextLine();

        //Check category list and see if the category exists and print the remaining budget, but if budget is 0, then
        // print no budget set  for this category
        boolean categoryExists = false;
        for (Category categoryObj : categories) {
            if (categoryObj.getName().equalsIgnoreCase(category)) {
                categoryExists = true;
                // Create a new category object
                System.out.println("The category exists.");
                System.out.println("Remaining Budget: " + categoryObj.getBudget());
                // If the budget is 0, then print no budget set for this category
                if (categoryObj.getBudget() == 0) {
                    System.out.println("No budget set for this category.");
                    System.out.println("Enter the budget amount:");
                    double budget = scanner.nextDouble();

                    categoryObj.setBudget(budget);
                }
                break;
            }
        }
        // If the category does not exist, then ask the user to enter a valid category
        if (!categoryExists) {
            System.out.println("The category does not exist. Please enter a valid category.");
            this.viewRemainingBudget();
        }
    }
}
