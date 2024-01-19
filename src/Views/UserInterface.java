package Views;

import Models.MenuOption;
import Services.BudgetService;
import Utility.Menu;
import Services.TransactionService;
import Data.DataManager;

import java.util.Objects;

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
                case DELETE_TRANSACTIONS:
                    transactionService.deleteTransactions();
                    break;
                case VIEW_CATEGORIES:
                    transactionService.viewCategories();
                    break;
                case ENTER_BUDGET:
                    budgetService.enterBudget();
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
