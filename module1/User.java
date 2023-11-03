import java.time.LocalDate;
import java.util.ArrayList;


class User {
    private int userID;
    private String name;
    private double fines;
    private ArrayList<Book> borrowedBookList = new ArrayList<>();

    public User(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public void displayBorrowedBooks(){
        if (!borrowedBookList.isEmpty()) {
            System.out.println(String.format("Below are the borrowed books by %s", this.name));
            System.out.println(String.format("%-6s | %-22s | %-22s | %-12s", "ItemID", "Title", "Author", "Due Date"));
            String repeatedChar = "-".repeat(69);
            System.out.println(repeatedChar);
            
            for (Book book : borrowedBookList) {
                System.out.println(book);
            }
        } else {
            System.out.println("No records of borrowing");
        }
        
    }

    public void borrowBook(Book book, LocalDate borrowDate) {
        if ((borrowedBookList.isEmpty() || borrowedBookList.size() < 16) && book.isAvailable() && fines == 0) {
            book.checkout(borrowDate);
            borrowedBookList.add(book);
            System.out.println("Borrowed this book successful.");
            System.out.println();
            displayBorrowedBooks();
        } else if (!book.isAvailable()) {
            System.out.println("This book is not available!");
        } else if (fines > 0) {
            System.out.println(String.format("Your borrowing privilege is suspended because you have outstanding fines of $%.2f.", fines));
        } else if (borrowedBookList.size() >= 16 ) {
            System.out.println("You have reached your maximum borrowing limit!");
        }
    }

    public void returnBook(Book book, LocalDate returnDate) {
        if (borrowedBookList.contains(book)) {
            double charge = book.checkin(returnDate);
            this.fines += charge;
            borrowedBookList.remove(book);
            System.out.println("Return this book successful.");
            if (charge > 0) {
                System.out.println(String.format("Your book is overdue. You have to pay the fines of $%.2f before you can continue borrowing books from library.", charge));
            }
        } else {
            System.out.println("Return this book unsuccessful.");
            System.out.println(String.format("This book is not borrowed."));
        }
    }

    public void payFines() {
        double tempFines = this.fines;
        this.fines = 0;
        System.out.println(String.format("Payment of $%.2f successful. You can continue borrowing books from the library.", tempFines));
    }

    @Override
    public String toString() {
        return this.name;
    }
}
