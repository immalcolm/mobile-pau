import java.util.ArrayList;

public class TicketBooking<T extends Ticket> {

    public double bookTickets(T ticket, int numTickets) {
        return ticket.calculateTotalCost(numTickets);
    }

    public double bookTickets(T ticket, int numTickets, double discountPercent) {
        return ticket.calculateTotalCost(numTickets, discountPercent);
    }

    public static void main(String[] args) {
        TicketBooking<Ticket> booking = new TicketBooking<>();
        RegularTicket regularTicket = new RegularTicket("Movie A", 10.0);
        VIPPass vipTicket = new VIPPass("Movie A", 10.0);
        double regularTicketCost = booking.bookTickets(regularTicket, 2);
        double vipTicketCost = booking.bookTickets(vipTicket, 2);
        System.out.println(String.format("Total cost of Regular ticket booking: $%.2f", regularTicketCost));
        System.out.println(String.format("Total cost of VIP Pass ticket booking: $%.2f", vipTicketCost));
        System.out.println();

        RegularTicket regularTicket2 = new RegularTicket("Movie A", 10.0);
        VIPPass vipTicket2 = new VIPPass("Movie A", 10.0);
        double regularTicketCost2 = booking.bookTickets(regularTicket2, 2, 0.2);
        double vipTicketCost2 = booking.bookTickets(vipTicket2, 2, 0.2);
        System.out.println(String.format("Total cost of Regular ticket booking during Christmas: $%.2f", regularTicketCost2));
        System.out.println(String.format("Total cost of VIP Pass ticket booking during Christmas: $%.2f", vipTicketCost2));
    }
}



