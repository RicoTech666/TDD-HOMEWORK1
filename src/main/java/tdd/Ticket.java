package tdd;

public class Ticket {
    private TicketTypes ticketType;
    private int bagNumber;

    public Ticket(TicketTypes ticketType, int bagNumber) {
        this.ticketType = ticketType;
        this.bagNumber = bagNumber;
    }

    TicketTypes getTicketType() {
        return ticketType;
    }
    int getBagNumber() {
        return this.bagNumber;
    }
}
