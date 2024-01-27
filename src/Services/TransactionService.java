package Services;

import Models.Category;
import Models.Transaction;
import Models.TransactionType;
import Services.Base.BaseService;

import java.util.List;
import java.util.Scanner;

public class TransactionService extends BaseService {

    private List<Category> categories;

    public TransactionService() {
        super();
        this.categories = dataManager.getCategories();
    }

    public void viewRecentTransactions() {
        List<Transaction> transactions = dataManager.getTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        transactions.sort((t1, t2) -> Integer.compare(t2.getTransactionId(), t1.getTransactionId()));

        System.out.println("Recent transactions:");
        System.out.println("--------------------");

        int count = 0;
        for (Transaction transaction : transactions) {
            System.out.println("ID: " + transaction.getTransactionId());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("Category: " + transaction.getCategory());
            System.out.println("Type: " + transaction.getType());
            System.out.println("Note: " + transaction.getNote());
            System.out.println("--------------------");

            count++;
            if (count >= 10) {
                break;
            }
        }
    }

    public void enterNewTransaction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter category:");
        String category = scanner.nextLine();

        boolean categoryExists = false;
        Category category1 = null;
        for (Category categoryObj : categories) {
            if (categoryObj.getName().equalsIgnoreCase(category)) {
                categoryExists = true;
                // Create a new category object
                category1 = categoryObj;
                System.out.println("The category exists.");

                break;
            }
        }
        // If the category does not exist, then ask the user to enter a valid category
        if (!categoryExists) {
            System.out.println("The category does not exist. Please enter a valid category and add the transaction");
            this.enterNewTransaction();
        }

        System.out.println("Enter amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume the leftover newline character

        System.out.println("Enter type (INCOME or EXPENSE):");
        TransactionType type = TransactionType.valueOf(scanner.nextLine().toUpperCase());

        // if type is expense and amount is greater than budget, then print budget exceeded
        if (type == TransactionType.EXPENSE) {
            assert category1 != null;
            if (amount > category1.getBudget()) {
                System.out.println("Budget exceeded.");
                System.out.println("Transaction Revoked.");
                return;
            } else {
                category1.setBudget(category1.getBudget() - amount);
            }
        }

        System.out.println("Enter note (optional):");
        String note = scanner.nextLine();

        System.out.println("Is recurring? (y/n):");
        boolean isRecurring = scanner.nextLine().equalsIgnoreCase("y");

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCategory(category);
        transaction.setType(type);
        transaction.setNote(note);
        transaction.setRecurring(isRecurring);

        int uniqueId = generateUniqueTransactionId(); // Implement this method
        transaction.setTransactionId(uniqueId);

        dataManager.getTransactions().add(transaction);

        System.out.println("Transaction added successfully with ID: " + uniqueId);
    }

    public void editTransactions() {
        Scanner scanner = new Scanner(System.in);

        viewRecentTransactions();

        System.out.println("Enter the ID of the transaction you want to edit:");
        int transactionIdToEdit = scanner.nextInt();

        Transaction transactionToEdit = null;
        for (Transaction transaction : dataManager.getTransactions()) {
            if (transaction.getTransactionId() == transactionIdToEdit) {
                transactionToEdit = transaction;
                break;
            }
        }

        if (transactionToEdit == null) {
            System.out.println("Transaction not found.");
            return;
        }

        System.out.println("Choose what you want to edit:");
        System.out.println("1. Amount");
        System.out.println("2. Category");
        System.out.println("3. Type");
        System.out.println("4. Note");
        System.out.println("5. Recurring status");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter new amount:");
                double newAmount = scanner.nextDouble();
                transactionToEdit.setAmount(newAmount);
                break;
            case 2:
                System.out.println("Enter new category:");
                String newCategory = scanner.nextLine();
                transactionToEdit.setCategory(newCategory);
                break;
            case 3:
                System.out.println("Enter new type (INCOME or EXPENSE):");
                TransactionType newType = TransactionType.valueOf(scanner.nextLine().toUpperCase());
                transactionToEdit.setType(newType);
                break;
            case 4:
                System.out.println("Enter new note (optional):");
                String newNote = scanner.nextLine();
                transactionToEdit.setNote(newNote);
                break;
            case 5:
                System.out.println("Set recurring status (y/n):");
                boolean newRecurring = scanner.nextLine().equalsIgnoreCase("y");
                transactionToEdit.setRecurring(newRecurring);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        System.out.println("Transaction edited successfully!");
    }

    public void deleteTransactions() {
        Scanner scanner = new Scanner(System.in);

        viewRecentTransactions();

        System.out.println("Enter the ID of the transaction you want to delete:");
        int transactionIdToDelete = scanner.nextInt();

        Transaction transactionToDelete = null;
        for (Transaction transaction : dataManager.getTransactions()) {
            if (transaction.getTransactionId() == transactionIdToDelete) {
                transactionToDelete = transaction;
                break;
            }
        }

        if (transactionToDelete == null) {
            System.out.println("Transaction not found.");
            return;
        }

        System.out.println("Are you sure you want to delete this transaction? (y/n)");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            dataManager.getTransactions().remove(transactionToDelete);
            System.out.println("Transaction deleted successfully!");
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    public void viewCategories() {
        List<Category> categories = dataManager.getCategories();

        if (categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }

        System.out.println("Available categories:");
        System.out.println("--------------------");

        for (Category category : categories) {
            System.out.println("- Name: " + category.getName());
            System.out.println("  Description: " + category.getDescription());
            System.out.println("--------------------");
        }
    }

    private int generateUniqueTransactionId() {
        List<Transaction> transactions = dataManager.getTransactions();
        int maxId = transactions.stream().mapToInt(Transaction::getTransactionId).max().orElse(0);
        return maxId + 1;
    }
}
