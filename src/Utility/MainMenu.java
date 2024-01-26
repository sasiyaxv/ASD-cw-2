package Utility;

import Models.MenuOption;

import java.util.Scanner;

public final class MainMenu {
    public MenuOption display() {
        System.out.println("1. View recent transactions");
        System.out.println("2. Enter a new transaction");
        System.out.println("3. Edit transactions");
        System.out.println("4. Delete transactions");
        System.out.println("5. View categories");
        System.out.println("6. Add a category");
        System.out.println("7. Enter budget");
        System.out.println("8. View remaining budget");
        System.out.println("9. Track progress against budget");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        return switch (choice) {
            case 1 -> MenuOption.VIEW_RECENT_TRANSACTIONS;
            case 2 -> MenuOption.ENTER_NEW_TRANSACTION;
            case 3 -> MenuOption.EDIT_TRANSACTIONS;
            case 4 -> MenuOption.DELETE_TRANSACTIONS;
            case 5 -> MenuOption.VIEW_CATEGORIES;
            case 6 -> MenuOption.ADD_CATEGORY;
            case 7 -> MenuOption.ENTER_BUDGET;
            case 8 -> MenuOption.VIEW_REMAINING_BUDGET;
            case 9 -> MenuOption.TRACK_PROGRESS;
            case 10 -> MenuOption.EXIT;
            default -> null;
        };
    }
}
