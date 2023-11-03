package answers.practical4abstraction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AbstractionPractice {
    public static void displayAllBooks(ArrayList<Book> bList){
        System.out.println(String.format("%-6s | %-22s | %-22s | %-12s", "ItemID", "Title", "Author", "Due Date"));
        String repeatedChar = "-".repeat(69);
        System.out.println(repeatedChar);
        for (Book book : bList) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        ArrayList<Book> bookList = new ArrayList<>();
        User user = new User(1, "Jasmine");
        Book book1 = new Book(1, "The Enigma of Time", "Jameson Black", 0.15);
        Book book2 = new Book(2, "Eternal Echoes", "Isabella Harper", 0.15);
        Book book3 = new Book(3, "Whispers in the Wind", "Samuel Thompson", 0.30);
        Book book4 = new Book(4, "Lost in the Labyrinth", "Emily Roberts", 0.30);
        Book book5 = new Book(5, "The Secret Garden", "Alexander Gray", 0.15);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String menu =   "\nMenu\n" +
                            "[1] Display all books\n" +
                            "[2] Display borrowed books\n" +
                            "[3] Borrow book\n" +
                            "[4] Return book\n" +
                            "[5] Pay fines\n" +
                            "[0] Exit\n" +
                            "Enter option: ";
            System.out.print(menu);
            int option = scanner.nextInt(); 
            System.out.println();

            switch (option) {
                case 1:
                    displayAllBooks(bookList);
                    break;
                case 2:
                    user.displayBorrowedBooks();
                    break;
                case 3:
                    Book foundBook = null;
                    System.out.print("Enter the Book itemID: ");
                    int borrowItemID = scanner.nextInt();
                    System.out.print("Enter borrowed date: ");
                    String borrowedDate = scanner.next();
                    for (Book book : bookList) {
                        if (book.getItemID() == borrowItemID) {
                            foundBook = book;
                            break;
                        }
                    }                    
                    user.borrowBook(foundBook, LocalDate.parse(borrowedDate));
                    break;
                case 4:
                    Book returnBook = null;
                    System.out.print("Enter the Book itemID: ");
                    int returnItemID = scanner.nextInt();
                    System.out.print("Enter return date: ");
                    String returnDate = scanner.next();
                    for (Book book : bookList) {
                        if (book.getItemID() == returnItemID) {
                            returnBook = book;
                            break;
                        }
                    }                    
                    user.returnBook(returnBook, LocalDate.parse(returnDate));
                    break;
                case 5:
                    user.payFines();
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }








        }



    }
}