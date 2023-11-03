package module1.pract4bdemo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Book extends LibraryItem implements Finable {
    private String author;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private double fineRate;
    private boolean availability;
    final int borrowDays = 21;

    public Book(int itemid, String title,
            String author, double fineRate) {
        super(itemid, title);
        this.author = author;
        this.fineRate = fineRate;
        this.availability = true;
    }

    public boolean isAvailable() {
        return this.availability;
    }

    @Override
    public String toString() {
        String formattedDate = borrowDate == null ? "-" : dueDate.toString();
        return String.format("%s | %-22s | %-12s",
                super.toString(), this.author, formattedDate);
    }

    @Override
    // what are possibile conditions?
    // set not available
    // set book borrow date
    // set book due date
    public void checkout(LocalDate borrowDate) {
        availability = false;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(borrowDays);

    }

    @Override
    // what are possibile conditions?
    // set available to true
    // check for fines
    public double checkin(LocalDate returnDate) {
        double fines = 0;
        if (returnDate.isAfter(dueDate)) {
            int daysLate = (int) ChronoUnit.DAYS.between(dueDate, returnDate);
            fines = calculateFine(daysLate);
        }
        this.availability = true;
        this.borrowDate = null;
        this.dueDate = null;
        
        return fines;
    }

    @Override
    //coming from our interface Finable
    //we need to implement this method
    public double calculateFine(int daysLate) {
        return this.fineRate * daysLate;
    }
}
