package org.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the accounting ledger command-line application and handles menu navigation and user input.
 */
public class LedgerApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static TransactionService transactionService;
    private static final int delayTime = 500;

    /**
     * Starts the accounting ledger application.
     * @param args command-line arguments (not used)
     * @throws InterruptedException if a menu delay is interrupted
     */
    public static void main(String[] args) throws InterruptedException {

        start();

    }

    /**
     * Loads saved transactions, initializes the transaction service, and opens the home menu.
     * @throws InterruptedException if a menu delay is interrupted
     */
    private static void start() throws InterruptedException {

        TransactionWriterAndReader twr = new TransactionWriterAndReader();
        ArrayList<Transaction> transactions = twr.loadTransactions();
        transactionService = new TransactionService(transactions);

        displayHome();
    }

    /**
     * Displays the home menu until the user chooses to exit.
     * @throws InterruptedException if a menu delay is interrupted
     */
    private static void displayHome() throws InterruptedException {
        Thread.sleep(delayTime);
        String choice;
        do {
            System.out.print("""
                   |-------------------------|
                   |----Accounting Ledger----|
                   |-------Application-------|
                   |-------------------------|
                    D - Add Deposit
                    P - Make Payment
                    L - Ledger
                    X - Exit
                    Enter your choice:\s""");

            choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "D" -> promptDeposit();
                case "P" -> promptPayment();
                case "L" -> displayLedger();
                case "X" -> System.out.println("Thank you, good bye!");
                default -> System.out.println("Invalid choice! Please enter D, P, L or X.");
            }
        } while (!choice.equalsIgnoreCase("X"));
    }


    /**
     * Displays transaction viewing options and the reports menu until the user returns home.
     * @throws InterruptedException if a menu delay is interrupted
     */
    private static void displayLedger() throws InterruptedException {
        String choice;
        Thread.sleep(delayTime);
        do {
            System.out.print("""
                           |---------------|
                           |--LEDGER MENU--|
                           |---------------|
                        A - Display all transactions
                        D - Display Deposits
                        P - Display Payments
                        R - Reports
                        H - Home
                        Enter your choice:\s""");

            choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "A" -> displayTransaction(transactionService.getAllTransaction());
                case "D" -> displayTransaction(transactionService.getDeposit());
                case "P" -> displayTransaction(transactionService.getPayment());
                case "R" -> displayReport();
                case "H" -> System.out.println("Returning to home screen ...\n");
                default -> System.out.println("Invalid choice! Please enter A, D, P, R or H.\n");
            }
        } while (!choice.equalsIgnoreCase("H"));
    }


    /**
     * Displays report options, prompts for vendor searches, and prints filtered transactions.
     * @throws InterruptedException if a menu delay is interrupted
     */
    private static void displayReport() throws InterruptedException {
        String choice;
        Thread.sleep(delayTime);
        do {
            System.out.print("""
                           |----------------|
                           |--REPORTS MENU--|
                           |----------------|
                        1 - Month to Date
                        2 - Previous Month
                        3 - Year to date
                        4 - Previous Years
                        5 - Search By Vendor
                        0 - Back
                        Enter your choice:\s""");

            choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    ArrayList<Transaction> filteredList = TransactionReport.monthToDate(transactionService.getAllTransaction());
                    displayTransaction(filteredList);
                }
                case "2" -> {
                    ArrayList<Transaction> filteredList = TransactionReport.previousMonth(transactionService.getAllTransaction());
                    displayTransaction(filteredList);
                }
                case "3" -> {
                    ArrayList<Transaction> filteredList = TransactionReport.yearToDate(transactionService.getAllTransaction());
                    displayTransaction(filteredList);
                }
                case "4" -> {
                    ArrayList<Transaction> filteredList =
                            TransactionReport.previousYear(transactionService.getAllTransaction());
                    displayTransaction(filteredList);
                }
                case "5" -> {
                    System.out.print("Vendor's name: ");
                    String vendor = scanner.nextLine().trim();

                    ArrayList<Transaction> filteredList =
                            TransactionReport.searchByVendor(vendor, transactionService.getAllTransaction());
                    displayTransaction(filteredList);
                }
                case "0" -> System.out.println("Returning to Ledger Menu ...");
                default -> System.out.println("Invalid choice! Please enter 1 - 5, or 0 to return to Ledger Menu.");
            }
        } while (!choice.equalsIgnoreCase("0"));
    }


    /**
     * Prints a numbered list of transactions or a message when no transactions match.
     * @param transactionArray transactions to display
     * @throws InterruptedException if a display delay is interrupted
     */
    private static void displayTransaction(ArrayList<Transaction> transactionArray) throws InterruptedException {
        int count = 1;
        if (transactionArray.isEmpty()) {
            System.out.println("No Transactions Found, Returning To Menu ...");
            Thread.sleep(delayTime);
            return;
        } else {
            System.out.println("Listing all transactions:");
            Thread.sleep(delayTime);
            for (Transaction trs: transactionArray) {
                System.out.printf("%s - %s\n", count++, trs);
            }
        }
        Thread.sleep(delayTime);
        System.out.println("\nEnd of list! Returning to Menu . . .");
        Thread.sleep(delayTime);
    }


    /**
     * Prompts for deposit details, validates a positive finite amount, and records the deposit.
     * @throws InterruptedException if a display delay is interrupted
     */
    private static void promptDeposit() throws InterruptedException {
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
        Thread.sleep(delayTime);
        System.out.println("Deposit added successfully!");
        Thread.sleep(delayTime);
    }

    /**
     * Prompts for payment details, validates a positive finite amount, and records the payment.
     * @throws InterruptedException if a display delay is interrupted
     */
    private static void promptPayment() throws InterruptedException {
        System.out.println("\n===== Make Payment =====");
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine().trim();

        double amount;

        while (true) {
            System.out.print("Enter payment amount: ");
            try {
                amount = Double.parseDouble(scanner.nextLine().trim());

                if (!Double.isFinite(amount) || amount <= 0) {
                    System.out.println("Payment amount must be greater than 0.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount! Please enter a valid number.");
            }
        }
        transactionService.makePayment(description, vendor, amount);
        Thread.sleep(delayTime);
        System.out.println("Payment made successfully! Returning to Menu ...");
        Thread.sleep(delayTime);
    }
}
