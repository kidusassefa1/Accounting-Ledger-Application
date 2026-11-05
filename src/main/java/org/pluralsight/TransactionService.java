package org.pluralsight;

import java.util.ArrayList;
import java.util.Comparator;

public class TransactionService {
    private ArrayList<Transaction> transactions;

    public TransactionService(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addDeposit(String description, String vendor, double amount) {
        Transaction newDeposit = new Transaction(description, vendor, amount);
        TransactionWriterAndReader.writeTransaction(newDeposit);
        transactions.add(newDeposit);
    }

    public void makePayment(String description, String vendor, double amount) {
        Transaction newPayment = new Transaction(description, vendor, -Math.abs(amount));
        TransactionWriterAndReader.writeTransaction(newPayment);
        transactions.add(newPayment);
    }

    public ArrayList<Transaction> getAllTransaction() {
        ArrayList<Transaction> sortedList = new ArrayList<>(transactions);
        sortedList.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );
        return sortedList;
    }

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
