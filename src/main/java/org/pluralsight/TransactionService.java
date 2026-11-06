package org.pluralsight;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages the in-memory transaction history, records deposits and payments, and retrieves transactions by type.
 */
public class TransactionService {
    private final ArrayList<Transaction> transactions;

    /**
     * Creates a service using the supplied in-memory transaction list.
     * @param transactions transactions loaded at application startup
     */
    public TransactionService(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Creates a deposit, writes it to CSV, and adds it to the in-memory list.
     * @param description deposit description
     * @param vendor vendor or source of funds
     * @param amount amount to record as a deposit
     */
    public void addDeposit(String description, String vendor, double amount) {
        Transaction newDeposit = new Transaction(description, vendor, amount);
        TransactionWriterAndReader.writeTransaction(newDeposit);
        transactions.add(newDeposit);
    }

    /**
     * Creates a payment using a negative amount, writes it to CSV, and adds it to the in-memory list.
     * @param description payment description
     * @param vendor payment recipient or vendor
     * @param amount payment magnitude; stored as a negative value
     */
    public void makePayment(String description, String vendor, double amount) {
        Transaction newPayment = new Transaction(description, vendor, -Math.abs(amount));
        TransactionWriterAndReader.writeTransaction(newPayment);
        transactions.add(newPayment);
    }

    /**
     * Returns a new list containing all transactions sorted from newest to oldest.
     * @return sorted copy of the transaction history
     */
    public ArrayList<Transaction> getAllTransaction() {
        ArrayList<Transaction> sortedList = new ArrayList<>(transactions);
        sortedList.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
        return sortedList;
    }

    /**
     * Filters and sorts transactions with positive amounts.
     * @return deposits sorted newest first, or an empty list
     */
    public ArrayList<Transaction> getDeposit() {
        ArrayList<Transaction> filteredList = new ArrayList<>();

        for (Transaction trs: transactions) {
            if (trs.getAmount() > 0) {          // checks if transaction.amount is positive
                filteredList.add(trs);
            }
        }
        filteredList.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
        return filteredList;
    }

    /**
     * Filters and sorts transactions with negative amounts.
     * @return payments sorted newest first, or an empty list
     */
    public ArrayList<Transaction> getPayment() {
        ArrayList<Transaction> filteredList = new ArrayList<>();
            for (Transaction trs: transactions) {
                if (trs.getAmount() < 0) {          // checks if transaction object's amount is negative
                    filteredList.add(trs);
                }
        }
        filteredList.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
        return filteredList;
    }
}
