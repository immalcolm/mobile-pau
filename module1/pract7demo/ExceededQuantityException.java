package module1.pract7demo;

public class ExceededQuantityException extends Exception {
    public ExceededQuantityException() {
        super("Quantity exceeds the cart limit.");
    }
}