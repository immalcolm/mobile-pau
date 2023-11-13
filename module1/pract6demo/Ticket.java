abstract class Ticket {
    private String movieName;
    private double ticketPrice;

    public Ticket(String movieName, double ticketPrice) {
        this.movieName = movieName;
        this.ticketPrice = ticketPrice;
    }

    public double getPrice() {
        return this.ticketPrice;
    }
    abstract double calculateTotalCost(int numTickets);

    abstract double calculateTotalCost(int numTickets, double discountPercent);

    @Override
    public String toString(){
        return this.movieName;
    }
}