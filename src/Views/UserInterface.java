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
                    budgetService.enterBudget();
                    break;
                case VIEW_REMAINING_BUDGET:
                    budgetService.viewRemainingBudget();
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
}
