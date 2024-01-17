package Utility;

import Models.MenuOption;

import java.util.Scanner;

public final class Menu {
    public static MenuOption displayMainMenu() {
        System.out.println("1. View recent transactions");
        System.out.println("2. Enter a new transaction");
        System.out.println("3. Edit transactions");
        System.out.println("4. Delete transactions");
        System.out.println("5. View categories");
        System.out.println("6. Enter budget");
        System.out.println("7. Track progress against budget");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return MenuOption.VIEW_RECENT_TRANSACTIONS;
            case 2:
                return MenuOption.ENTER_NEW_TRANSACTION;
            case 3:
                return MenuOption.EDIT_TRANSACTIONS;
            case 4:
                return MenuOption.DELETE_TRANSACTIONS;
            case 5:
                return MenuOption.VIEW_CATEGORIES;
            case 6:
                return MenuOption.ENTER_BUDGET;
            case 7:
                return MenuOption.TRACK_PROGRESS;
            case 0:
                return MenuOption.EXIT;
            default:
                return null;
        }
    }
}
