package Views;

import Models.MenuOption;
import Services.BudgetService;
import Services.CategoryService;
import Utility.MainMenu;
import Services.TransactionService;

import java.util.Objects;
import java.util.Scanner;

public class UserInterface {

    private static final MainMenu mainMenu = new MainMenu();

    private static final BudgetService budgetService = new BudgetService();

    private static final TransactionService transactionService =  new TransactionService();

    private static final CategoryService categoryService =  new CategoryService();

    public static void start() {
        MenuOption choice;
        do {
            choice = mainMenu.display();
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
                case ADD_CATEGORY:
                    categoryService.addCategory();
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
    private static void enterBudget() {
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
    private static void viewRemainingBudget() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the category to view the remaining budget:");
        String category = scanner.nextLine();
        double remainingBudget = budgetService.getRemainingBudget(category);
        System.out.println("The remaining budget for category " + category + " is: " + remainingBudget);
    }
}
