package tdd;

public class Locker {
    private Ticket ticket;
    private boolean empty;

    public Locker(Ticket ticket) {
        this.ticket = ticket;
    }

    public Locker(Ticket ticket, boolean emptyFlag) {
        this.ticket = ticket;
        this.empty = emptyFlag;
    }
    
    public boolean isEmpty() {
        return empty;
    }
    
    public Ticket getTicket() {
        return this.ticket;
    }
    
    public boolean store() throws LockerException {
        if (empty) return true;
        else throw new LockerException("存包失败 提示储物柜已满");
    }

    public boolean checkTicket() {
        return this.ticket.getTicketType().equals(TicketTypes.VALID_TICKET);
    }
}
