package Services;

import Data.DataManager;
import Models.Transaction;
import Models.TransactionType;

import java.util.List;
import java.util.Scanner;

public class TransactionService {
    private DataManager dataManager;

    public TransactionService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void viewRecentTransactions() {
        //Todo
    }

    public void enterNewTransaction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter amount:");
        double amount = scanner.nextDouble();

        System.out.println("Enter category:");
        String category = scanner.nextLine();

        System.out.println("Enter type (INCOME or EXPENSE):");
        TransactionType type = TransactionType.valueOf(scanner.nextLine().toUpperCase());

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

        // Generate a unique transaction ID
        int uniqueId = generateUniqueTransactionId(); // Implement this method
        transaction.setTransactionId(uniqueId);

        // Add the transaction to the DataManager
        dataManager.getTransactions().add(transaction);

        System.out.println("Transaction added successfully with ID: " + uniqueId);
    }

    public void editTransactions() {
        Scanner scanner = new Scanner(System.in);

        // Display a list of transactions to choose from
        viewRecentTransactions();

        System.out.println("Enter the ID of the transaction you want to edit:");
        int transactionIdToEdit = scanner.nextInt();

        // Find the transaction to edit
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

        // Present a menu for editing options
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

        // Find the transaction to delete
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

        // Confirm deletion
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
        //Todo
    }

    private int generateUniqueTransactionId() {
        List<Transaction> transactions = dataManager.getTransactions();
        int maxId = transactions.stream().mapToInt(Transaction::getTransactionId).max().orElse(0);
        return maxId + 1;
    }
}
