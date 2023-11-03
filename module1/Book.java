package answers.practical4abstraction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;


public class Book extends LibraryItem implements Finable {
    private String author;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private double fineRate;
    private boolean availability;

    public Book(int itemID, String title, String author, double fineRate) {
        super(itemID, title);        
        this.author = author;
        this.fineRate = fineRate;
        this.availability = true;
    }

    public void checkout(LocalDate borrowDate) {
        this.availability = false;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(21);
    }

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

    public double calculateFine(int daysLate) {
        return fineRate * daysLate;
    }

    public boolean isAvailable() {
        return this.availability;
    }
    @Override
    public String toString() {
        String formattedDate = "-";
        if (this.dueDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formattedDate = this.dueDate.format(formatter);
        }
        return String.format("%s | %-22s | %-12s", super.toString(), this.author, formattedDate);
    }
}