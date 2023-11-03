package module1.pract4bdemo;

import java.time.LocalDate;

public abstract class LibraryItem {
    private int itemID;
    private String title;

    public LibraryItem(int itemID, String title) {
        this.itemID = itemID;
        this.title = title;
    }

    public int getItemID(){
        return this.itemID;
    }

    abstract void checkout(LocalDate borrowDate);
    abstract double checkin(LocalDate returnDate);

    @Override
    public String toString(){
        return String.format("%6d | %-22s", this.itemID, this.title);
    };

    
}
