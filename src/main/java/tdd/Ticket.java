package tdd;

import lombok.Data;

@Data
public class Ticket {
    private TicketTypes ticketType;
    private int bagNumber;
    private int lockerNumber;
    
    public Ticket(TicketTypes ticketType, int bagNumber, int lockerNumber) {
        this.ticketType = ticketType;
        this.bagNumber = bagNumber;
        this.lockerNumber = lockerNumber;
    }
}
