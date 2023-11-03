package module1.pract4bdemo;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {

    private int userID;
    private String name;
    private double fines;//default value is 0
    private ArrayList<LibraryItem> borrowedItems = new ArrayList<>();// declare our array list first

    public User(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    //option 2
    //display all books 
    //if no books >> show sth
    public void displayBorrowedBooks() {
        if ( !borrowedItems.isEmpty()) {
            System.out.println(String.format("Below are the borrowed items by %s", this.name));
            System.out.println(
                    String.format("%-6s | %-22s | %-22s | %-12s", "ItemID", "Title", "Author/Publisher", "Due Date"));
            String repeatedChar = "-".repeat(69);
            System.out.println(repeatedChar);

            for (LibraryItem item : borrowedItems) {
                System.out.println(item); //we insert Book objects inside . Then tap on book toString
            }
        } else {
            System.out.println(">> No records of borrowing");
        }
    }

    public void borrowBook(Book book, LocalDate borrowDate) {
        if ((borrowedItems.isEmpty() || borrowedItems.size() < 16) && book.isAvailable() && fines == 0) {
            borrowedItems.add(book);
            book.checkout(borrowDate);
            System.out.println(">> Borrowed this item successful.");
            System.out.println();
            displayBorrowedBooks();
        } else if (!book.isAvailable()) {
            System.out.println(">> This item is not available!");
        } else if (fines > 0) {
            System.out.println(String.format(">> Your borrowing privilege is suspended because you have outstanding fines of $%.2f.", fines));
        } else if (borrowedItems.size() >= 16 ) {
            System.out.println(">> You have reached your maximum borrowing limit!");
        }

    }

    //option 4 
    //check in and get fines 
    //if fined >> add to fine charges
    //remove book from user's booklist 
    public void returnBook(Book book, LocalDate returnDate) {
        if (borrowedItems.contains(book)) {
            double charge = book.checkin(returnDate);
            this.fines += charge;
            borrowedItems.remove(book);
            System.out.println(">> Return this item successful.");
            if (charge > 0) {
                System.out.println(String.format(">> Your item is overdue. You have to pay the fines of $%.2f before you can continue borrowing items from library.", charge));
            }
        } else {
            System.out.println(">> Return this item unsuccessful.");
            System.out.println(String.format(">> This item is not borrowed."));
        }
    }

    //user input 4
    //instant pay fines if selected
    public void payFines() {
        double tempFines = this.fines;
        this.fines = 0;
        System.out.println(String.format(">> Payment of $%.2f successful. You can continue borrowing items from the library.", tempFines));
    }
 
    @Override
    public String toString() {
        return String.format("%6d | %-22s", this.userID, this.name);
    }

}
