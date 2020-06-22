package tdd;

import lombok.Data;

@Data
public class Ticket {
    private TicketTypes ticketType;
    private int bagNumber;
    private int lockerNumber;
    private int robotNumber;

    public Ticket() {
    }

    public Ticket(TicketTypes ticketType, int bagNumber, int lockerNumber) {
        this.ticketType = ticketType;
        this.bagNumber = bagNumber;
        this.lockerNumber = lockerNumber;
    }

    public Ticket(TicketTypes ticketType, int bagNumber, int lockerNumber, int robotNumber) {
        this.ticketType = ticketType;
        this.bagNumber = bagNumber;
        this.lockerNumber = lockerNumber;
        this.robotNumber = robotNumber;
    }
}
