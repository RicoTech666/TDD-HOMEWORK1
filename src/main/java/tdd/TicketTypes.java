package tdd;

public enum TicketTypes {
    VALID_TICKET("valid ticket"),
    FORGED_TICKET("forged ticket");
    
    private String ticketType;
    
    TicketTypes(String ticketType) {
        this.ticketType = ticketType;
    }
}
