package org.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents one financial transaction, including its date, time, description, vendor, and signed amount. Positive amounts represent deposits and negative amounts represent payments.
 */
public class Transaction {
    private final LocalDate date;
    private final LocalTime time;
    private final String description;
    private final String vendorName;
    private final double amount;

    /**
     * Creates a new transaction using the current local date and time.
     * @param description description of the transaction
     * @param vendorName name of the vendor or other party
     * @param amount signed transaction amount
     */
    public Transaction(String description, String vendorName, double amount) {
        this.time = LocalTime.now();
        this.date = LocalDate.now();
        this.description = description;
        this.vendorName = vendorName;
        this.amount = amount;
    }

    /**
     * Reconstructs a transaction with an existing date and time, such as one loaded from the CSV file.
     * @param date original transaction date
     * @param time original transaction time
     * @param description description of the transaction
     * @param vendorName name of the vendor or other party
     * @param amount signed transaction amount
     */
    public Transaction(LocalDate date, LocalTime time, String description, String vendorName, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendorName = vendorName;
        this.amount = amount;
    }

    /**
     * Returns the transaction date.
     * @return transaction date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the transaction time.
     * @return transaction time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns the transaction description.
     * @return description text
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the vendor name.
     * @return vendor name
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * Returns the signed transaction amount.
     * @return positive deposit or negative payment amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Formats this transaction as a pipe-delimited CSV record with a time accurate to seconds and an amount shown with two decimal places.
     * @return transaction formatted as date|time|description|vendor|amount
     */
    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%.2f", date, time.format(DateTimeFormatter.ofPattern("HH:mm:ss")), description, vendorName, amount);
    }
}
