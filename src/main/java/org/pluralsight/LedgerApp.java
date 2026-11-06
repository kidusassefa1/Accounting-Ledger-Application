package org.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class LedgerApp {
    private static Scanner scanner = new Scanner(System.in);
    private static TransactionService transactionService;

    public static void main(String[] args) {

        //TODO: Write JavaDoc for all methods
        //TODO: Class relationship diagram
        //TODO: DO Bonus
        //TODO: Experiment with JPanel fpr UI stuff

        start();

    }

    private static void start() {

        TransactionWriterAndReader twr = new TransactionWriterAndReader();
        ArrayList<Transaction> transactions = twr.loadTransactions();
        transactionService = new TransactionService(transactions);

        displayHome();
    }

    private static void displayHome() {
        String choice;
        do {
            System.out.print("""
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

    private static void displayLedger() {
        String choice;
        do {
            System.out.print("""
                        A - Display all transactions
                        D - Display Deposits
                        P - Display Payments
                        R - Reports
                        H - Home
                        Enter your choice:\s""");

            choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "A" -> displayTransaction(transactionService.getAllTransaction());
                case "D" -> displayDeposit();
                case "P" -> displayPayment();
                case "R" -> displayReport();
                case "H" -> System.out.println("Returning to home screen ...");
                default -> System.out.println("Invalid choice! Please enter A, D, P, R or H.");
            }
        } while (!choice.equalsIgnoreCase("H"));
    }


    private static void displayReport() {
        String choice;
        do {
            System.out.print("""
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
                    ArrayList<Transaction> filteredList =
                            TransactionReport.monthToDate(transactionService.getAllTransaction());
                    displayTransaction(filteredList);
                }
                case "2" -> {
                    ArrayList<Transaction> filteredList =
                            TransactionReport.previousMonth(transactionService.getAllTransaction());
                    displayTransaction(filteredList);
                }
                case "3" -> {
                    ArrayList<Transaction> filteredList =
                            TransactionReport.yearToDate(transactionService.getAllTransaction());
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
                case "0" -> System.out.println("Returning to Ledger Menu  ...");
                default -> System.out.println("Invalid choice! Please enter 1 - 5, or 0 to return to Ledger Menu.");
            }
        } while (!choice.equalsIgnoreCase("0"));
    }

    private static void displayTransaction(ArrayList<Transaction> transactionArray) {
        if (transactionArray.isEmpty()) {
            System.out.println("No Transactions Found, Returning To Menu ...");
        } else {
            System.out.println("Listing all transactions:");
            for (Transaction trs: transactionArray) {
                System.out.println(trs);
            }
        }
    }

    private static void displayDeposit() {
        ArrayList<Transaction> depositArray = transactionService.getDeposit();

        if (depositArray.isEmpty()) {
            System.out.println("No deposits recorded on file. Returning to Menu ...");
        } else {
            System.out.println("Listing all Deposit Transactions:");
            for (Transaction trs: depositArray) {
                System.out.println(trs);
            }
        }
    }

    private static void displayPayment() {
        ArrayList<Transaction> paymentArray = transactionService.getPayment();

        if (paymentArray.isEmpty()) {
            System.out.println("No payments recorded on file. Returning to Menu ...");
        } else {
            System.out.println("Listing all Payment Transactions:");
            for (Transaction trs: paymentArray) {
                System.out.println(trs);
            }
        }
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

    private static void promptPayment() {
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

        System.out.println("Payment made successfully!");
    }

}
