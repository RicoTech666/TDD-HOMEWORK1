package tdd;

public class Locker {
    private Ticket ticket;
    private boolean empty;

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
}
