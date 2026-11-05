package org.pluralsight;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;

public class TransactionReport {

    public static ArrayList<Transaction> monthToDate(ArrayList<Transaction> transactionArray) {
        ArrayList<Transaction> result = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);

        for (Transaction trs : transactionArray) {
            if (!trs.getDate().isBefore(startOfMonth) && !trs.getDate().isAfter(today)) {
                result.add(trs);
            }
        }

        result.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
        return result;
    }

    public static ArrayList<Transaction> yearToDate(ArrayList<Transaction> transactionArray) {
        ArrayList<Transaction> result = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.withDayOfYear(1);

        for (Transaction trs : transactionArray) {
            if (!trs.getDate().isBefore(startOfYear) && !trs.getDate().isAfter(today)) {
                result.add(trs);
            }
        }

        result.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
        return result;
    }


    public static ArrayList<Transaction> previousMonth(ArrayList<Transaction> transactionArray) {
        ArrayList<Transaction> result = new ArrayList<>();
        YearMonth previousMonth = YearMonth.now().minusMonths(1);

        for (Transaction trs : transactionArray) {
            if (YearMonth.from(trs.getDate()).equals(previousMonth)) {
                result.add(trs);
            }
        }
        result.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
        return result;
    }

    public static ArrayList<Transaction> previousYear(ArrayList<Transaction> transactionArray) {
        ArrayList<Transaction> result = new ArrayList<>();
        int previousYear = LocalDate.now().getYear() - 1;

        for (Transaction trs : transactionArray) {
            if (trs.getDate().getYear() == previousYear) {
                result.add(trs);
            }
        }
        result.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
        return result;
    }

    public static ArrayList<Transaction> searchByVendor(String vendor, ArrayList<Transaction> transactionArray) {
        ArrayList<Transaction> result = new ArrayList<>();

        for (Transaction trs : transactionArray) {
            if (trs.getVendorName().toLowerCase().contains(vendor.trim().toLowerCase())) {
                result.add(trs);
            }
        }
        result.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
        return result;
    }
}
