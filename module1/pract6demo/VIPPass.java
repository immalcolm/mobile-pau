class VIPPass extends Ticket {
    public VIPPass(String movieName, double price) {
        super(movieName, price);
    }

    public double calculateTotalCost(int numTickets) {
        return numTickets * super.getPrice() * 0.9;
    }

    public double calculateTotalCost(int numTickets, double discountPercent) {
        return (numTickets * super.getPrice() * (1-discountPercent))*0.9;
    }
}