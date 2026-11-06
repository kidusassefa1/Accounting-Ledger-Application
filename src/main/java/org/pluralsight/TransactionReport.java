package org.pluralsight;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Provides date-based and vendor-based filters for lists of financial transactions. All report methods return results sorted newest first.
 */
public class TransactionReport {

    /**
     * Finds transactions dated from the first day of the current month through today, inclusive.
     * @param transactionArray transactions to search
     * @return matching transactions sorted newest first
     */
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

    /**
     * Finds transactions dated from January 1 of the current year through today, inclusive.
     * @param transactionArray transactions to search
     * @return matching transactions sorted newest first
     */
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

    /**
     * Finds transactions in the previous calendar month, including year transitions.
     * @param transactionArray transactions to search
     * @return matching transactions sorted newest first
     */
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

    /**
     * Finds transactions in the previous calendar year.
     * @param transactionArray transactions to search
     * @return matching transactions sorted newest first
     */
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

    /**
     * Finds transactions whose vendor names contain the supplied text, ignoring letter case.
     * @param vendor all or part of the vendor name to search for
     * @param transactionArray transactions to search
     * @return matching transactions sorted newest first
     */
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
