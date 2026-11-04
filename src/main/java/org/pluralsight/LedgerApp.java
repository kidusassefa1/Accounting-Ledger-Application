package org.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class LedgerApp {
    private static Scanner scanner = new Scanner(System.in);
    private static TransactionService transactionService;

    public static void main(String[] args) {

        start();

    }

    private static void start() {

        TransactionWriterAndReader twr = new TransactionWriterAndReader();
        ArrayList<Transaction> transactions = twr.loadTransactions();
        transactionService = new TransactionService(transactions);

        displayHome();
    }

    private static void displayHome() {
        TransactionWriterAndReader trs = new TransactionWriterAndReader();
        String choice;
        do {
            System.out.println("""
                    D - Add Deposit
                    P - Make Payment
                    L - Ledger
                    X - Exit
                    """);

            choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "D" -> promptDeposit();
                case "P" -> { }
                case "L" -> displayLedger();
                case "X" -> System.out.println("Thank you, good bye!");
                default -> System.out.println("Invalid choice! Please enter D, P, L or X.");
            }
        } while (!choice.equalsIgnoreCase("X"));
    }

    public static void displayLedger() {
        String choice;
        do {
            System.out.println("""
                    A - Display all transactions
                    D - Display Deposits
                    P - Display Payments
                    R - Reports
                    H - Home
                    """);

            choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "A" -> { }
                case "D" -> { }
                case "P" -> { }
                case "R" -> displayReport();
                case "H" -> System.out.println("Returning to home screen ...");
                default -> System.out.println("Invalid choice! Please enter A, D, P, R or H.");
            }
        } while (!choice.equalsIgnoreCase("H"));
    }


    public static void displayReport() {
        String choice;
        do {
            System.out.println("""
                        1 - Month to Date
                        2 - Previous Month
                        3 - Year to date
                        4 - Previous Years
                        5 - Search By Vendor
                        0 - Back
                    """);

            choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> { }
                case "2" -> { }
                case "3" -> { }
                case "4" -> { }
                case "5" -> { }
                case "0" -> System.out.println("Returning to Ledger Menu  ...");
                default -> System.out.println("Invalid choice! Please enter 1 - 5, or 0 to return to Ledger Menu.");
            }
        } while (!choice.equalsIgnoreCase("0"));
    }

    private static void promptDeposit() {
        System.out.println("\n===== Add Deposit =====");
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine().trim();

        double amount;

        while (true) {
            System.out.print("Enter deposit amount: ");
            try {
                amount = Double.parseDouble(scanner.nextLine().trim());

                if (!Double.isFinite(amount) || amount <= 0) {
                    System.out.println("Deposit amount must be greater than 0.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount! Please enter a valid number.");
            }
        }
        transactionService.addDeposit(description, vendor, amount);

        System.out.println("Deposit added successfully!");
    }

}
