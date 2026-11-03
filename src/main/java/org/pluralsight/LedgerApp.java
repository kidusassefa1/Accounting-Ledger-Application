package org.pluralsight;

import java.util.Scanner;

public class LedgerApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayHome();
    }

    private static void start() {


    }

    private static void displayHome() {

        String choice;
        do {
            System.out.println("""
                    D - Add Deposit
                    P - Make Payment
                    L - Ledger
                    X - Exit
                    """);

            choice = scanner.nextLine();
            switch (choice.toUpperCase()) {
                case "D" -> TransactionService.addDeposit();
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

            choice = scanner.nextLine();
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

            choice = scanner.nextLine();
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



}
