package org.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final LocalDate date;
    private final LocalTime time;
    private final String description;
    private final String vendorName;
    private final float amount;

    public Transaction(String description, String vendorName, float amount) {
//        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("H:mm:ss"));
        this.time = LocalTime.now();
        this.date = LocalDate.now();
        this.description = description;
        this.vendorName = vendorName;
        this.amount = amount;
    }

    public static Transaction csvToTransaction(String row) {

    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendorName() {
        return vendorName;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
