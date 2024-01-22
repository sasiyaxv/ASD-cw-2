package Views;

import Models.MenuOption;
import Services.BudgetService;
import Utility.Menu;
import Services.TransactionService;
import Data.DataManager;

import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    private DataManager dataManager;

    private BudgetService budgetService;
    private TransactionService transactionService;

    public UserInterface(DataManager dataManager) {
        this.dataManager = dataManager;
        this.budgetService = new BudgetService(dataManager);
        this.transactionService = new TransactionService(dataManager);
    }

    public void start() {
        MenuOption choice;
        do {
            choice = Menu.displayMainMenu();
            switch (Objects.requireNonNull(choice)) {
                case VIEW_RECENT_TRANSACTIONS:
                    transactionService.viewRecentTransactions();
                    break;
                case ENTER_NEW_TRANSACTION:
                    transactionService.enterNewTransaction();
                    break;
                case EDIT_TRANSACTIONS:
                    transactionService.editTransactions();
                    break;
                case DELETE_TRANSACTIONS:
                    transactionService.deleteTransactions();
                    break;
                case VIEW_CATEGORIES:
                    transactionService.viewCategories();
                    break;
                case ENTER_BUDGET:
                    enterBudget();
                    break;
                case VIEW_REMAINING_BUDGET:
                    viewRemainingBudget();
                    break;
                case TRACK_PROGRESS:
                    budgetService.trackProgressAgainstBudget();
                    break;
                case EXIT:
                    System.out.println("Exiting the Budget Tracker. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != MenuOption.EXIT);
    }

    /**
     * Method is used to enter a budget for a specific category.
     */
    private void enterBudget() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the category for the budget:");
        String category = scanner.nextLine();
        System.out.println("Enter the budget amount:");
        double budget = scanner.nextDouble();
        budgetService.enterBudget(category, budget);
    }

    /**
     * Method is used to view the remaining budget for a specific category.
     */
    private void viewRemainingBudget() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the category to view the remaining budget:");
        String category = scanner.nextLine();
        double remainingBudget = budgetService.getRemainingBudget(category);
        System.out.println("The remaining budget for category " + category + " is: " + remainingBudget);
    }
}
