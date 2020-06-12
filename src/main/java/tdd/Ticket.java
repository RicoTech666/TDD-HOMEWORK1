package tdd;

public class Ticket {
    private TicketTypes ticketType;

    public Ticket(TicketTypes ticketType) {
        this.ticketType = ticketType;
    }

    public TicketTypes getTicketType() {
        return ticketType;
    }
}
