package module1.pract7demo;

public class NonPositiveNumberException extends Exception {
    public NonPositiveNumberException() {
        super("Quantity must be a positive number.");
    }
}