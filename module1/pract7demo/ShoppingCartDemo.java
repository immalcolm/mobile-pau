package module1.pract7demo;

import java.util.Scanner;

public class ShoppingCartDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int maxQuantity = 0; // Initialize maxQuantity to zero

        try {
            System.out.print("Enter the maximum quantity for the cart: ");
            maxQuantity = input.nextInt();
            if (maxQuantity <= 0) {
                throw new NonPositiveNumberException();
            }
        } catch (NonPositiveNumberException e) {
            System.out.println("Error: Maximum quantity must be a positive number.");
            return; // Exit the program if maxQuantity is not positive
        }

        ShoppingCart cart = new ShoppingCart(maxQuantity);

        while (true) {
            System.out.print("1. Add items\n2. Remove items\n3. View cart\n4. Exit\nEnter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter the quantity to add: ");
                        int addQuantity = input.nextInt();
                        cart.addItem(addQuantity);
                        System.out.println("Item(s) added to the cart.");
                    } catch (ExceededQuantityException | NonPositiveNumberException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter the quantity to remove: ");
                        int removeQuantity = input.nextInt();
                        cart.removeItem(removeQuantity);
                        System.out.println("Item(s) removed from the cart.");
                    } catch (InsufficientQuantityException | NonPositiveNumberException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Current item quantity in the cart: " + cart.getItemQuantity());
                    break;

                case 4:
                    System.out.println("Exiting the program.");
                    input.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
