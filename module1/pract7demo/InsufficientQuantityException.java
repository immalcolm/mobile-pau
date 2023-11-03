package module1.pract7demo;
// set the file name as InsufficientQuantityException
public class InsufficientQuantityException extends Exception {
    private int availableQuantity;

    public InsufficientQuantityException(int availableQuantity) {
        super("Insufficient quantity. Available quantity: " + availableQuantity);
        this.availableQuantity = availableQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}