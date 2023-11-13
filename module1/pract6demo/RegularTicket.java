class RegularTicket extends Ticket {
    public RegularTicket(String movieName, double price) {
        super(movieName, price);
    }

    public double calculateTotalCost(int numTickets) {
        return numTickets * super.getPrice();
    }

    public double calculateTotalCost(int numTickets, double discountPercent) {
        return numTickets * super.getPrice() * (1 - discountPercent);
    }
}