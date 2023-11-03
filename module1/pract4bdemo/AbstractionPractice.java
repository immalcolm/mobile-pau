package module1.pract4bdemo;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AbstractionPractice {

    public static void main(String[] args) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(1, "The Engima of Time", "Jameson Black", 0.15));
        bookList.add(new Book(2, "Eternal Echoes", "Isabelle Harper", 0.15));
        bookList.add(new Book(3, "Whispers in the Wind", "Samuel Thompson", 0.3));
        bookList.add(new Book(4, "Lost in the Labyrinth", "Emily Roberts", 0.3));
        bookList.add(new Book(5, "The Secret Garden", "Alexande r Gray", 0.15));

        User demoUser = new User(1, "Jasmine");

        //
        boolean appRun = true;
        Scanner scanner = new Scanner(System.in);
        do {

            /*
             * System.out.println("\n[Menu]");
             * System.out.println("[1] Display all books");
             * System.out.println("[2] Display borrowed books");
             * System.out.println("[3] Borrow books");
             * System.out.println("[4] Return  books");
             * System.out.println("[5] Pay fines");
             * System.out.println("[0] Exit\n");
             */
            String menu = "\n==Welcome to Pau Library==\n" +
                    "[1] Display all items\n" +
                    "[2] Display borrowed items\n" +
                    "[3] Borrow item\n" +
                    "[4] Return item\n" +
                    "[5] Pay fines\n" +
                    "[0] Exit\n" +
                    "Enter option: ";
            System.out.print(menu);
            int option = scanner.nextInt();
            // String userInput = scanner.nextLine();
            Book foundBook = null;
            switch (option) {
                case 1:
                    displayAllBooks(bookList);
                    break;
                case 2:
                    demoUser.displayBorrowedBooks();
                    break;
                case 3:
                    // borrow books
                    foundBook = null;
                    System.out.print("Enter the itemID: ");
                    int borrowItemID = scanner.nextInt();
                    System.out.print("Enter borrowed date: ");
                    String borrowedDate = scanner.next();
                    // @TODO
                    // we can use LibraryItem here instead for a more generic usage
                    for (Book li : bookList) {
                        if (li.getItemID() == borrowItemID) {
                            foundBook = li;
                            break;
                        }
                    }
                    // we leave the checking of book availability to the checkout
                    // different item may checkout differently
                    // pass found object
                    if (foundBook != null) {
                        demoUser.borrowBook(foundBook, LocalDate.parse(borrowedDate));
                    }

                    break;
                case 4:
                    foundBook = null;
                    System.out.print("Enter the itemID: ");
                    int returnItemID = scanner.nextInt();
                    System.out.print("Enter return date: ");
                    String returnDate = scanner.next();
                    for (Book item : bookList) {
                        if (item.getItemID() == returnItemID) {
                            foundBook = item;
                            break;
                        }
                    }
                    demoUser.returnBook(foundBook, LocalDate.parse(returnDate));
                    break;
                case 5:
                    demoUser.payFines();
                    break;
                case 0:
                    scanner.close();
                    System.out.println(">> Thanks for visiting Pau Library. \nkthxbye");
                    appRun = false;
                    //optional
                    System.exit(0);
                    break;
                default:
                    System.out.println(">> Try entering 0-5");
                    break;
            }
        } while (appRun);
    }

    public static void displayAllBooks(ArrayList<Book> bList) {
        System.out.println(String.format("%-6s | %-22s | %-22s | %-12s", "ItemID", "Title", "Author", "Due Date"));
        String repeatedChar = "-".repeat(69);
        System.out.println(repeatedChar);
        for (Book book : bList) {
            System.out.println(book.toString());
        }
    }

    public void printMenu() {

    }

}
