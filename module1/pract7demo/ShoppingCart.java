package module1.pract7demo;

public class ShoppingCart {
    private int itemQuantity;
    private int maxQuantity;

    public ShoppingCart(int maxQuantity) {
        this.maxQuantity = maxQuantity;
        this.itemQuantity = 0;//assign a default
    }

    //0. setup which exceptions to handle
    public void addItem(int quantity) throws ExceededQuantityException, NonPositiveNumberException {
        //try{catch ()} mechanism

        //1. due to this rule
        if (quantity <= 0) {
            //2. throw error 
            throw new NonPositiveNumberException();
        }

        // Check if adding the specified quantity exceeds the cart limit
        if (itemQuantity + quantity > maxQuantity) {
            throw new ExceededQuantityException();
        }

        itemQuantity += quantity;
    }

    public void removeItem(int quantity) throws InsufficientQuantityException, NonPositiveNumberException {
        if (quantity <= 0) {
            throw new NonPositiveNumberException();
        }

        if (quantity > itemQuantity) {
            throw new InsufficientQuantityException(itemQuantity);
        }

        itemQuantity -= quantity;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }
}