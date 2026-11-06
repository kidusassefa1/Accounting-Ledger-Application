package org.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Reads transactions from and appends transactions to the pipe-delimited CSV file at DataFiles/transactions.csv.
 */
public class TransactionWriterAndReader {
    private BufferedReader bufReader;
    private static BufferedWriter bufWriter;
    private static String fileName = "DataFiles/transactions.csv";

    /**
     * Reads saved transactions from the pipe-delimited CSV file, skipping the first header line. An I/O failure is printed and the accumulated list is returned.
     * @return loaded transactions, or an empty/partial list if no rows were loaded or reading failed
     */
    public ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactionsArray = new ArrayList<>();
        try {
            bufReader = new BufferedReader(new FileReader(fileName));
            String transaction;
            bufReader.readLine();
            while((transaction = bufReader.readLine()) != null) {
                String[] input = transaction.trim().split("\\|");

                LocalDate date = LocalDate.parse(input[0]);
                LocalTime time = LocalTime.parse(input[1]);
                String description = input[2];
                String vendor = input[3];
                double amount = Double.parseDouble(input[4]);

                transactionsArray.add(new Transaction(date,time,description,vendor,amount));

            }
            bufReader.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

        return transactionsArray;
    }

    /**
     * Appends one transaction as a new CSV line. An I/O failure is printed rather than propagated to the caller.
     * @param newTransaction transaction to append
     */
    public static void writeTransaction(Transaction newTransaction) {
        try {
            bufWriter = new BufferedWriter(new FileWriter(fileName, true));

            bufWriter.write(String.format("%s", newTransaction));
            bufWriter.newLine();
            bufWriter.close();

        } catch (IOException e) {
            System.out.print("ERROR: An unexpected error occurred");
            e.printStackTrace();
        }
    }
}
