package tdd;

public class Locker {
    private Ticket ticket;
    private int capacity;
    private int currentUsedCapacity;

    public Locker() {
    }

    public Locker(int capacity, int currentUsedCapacity) {
        this.capacity = capacity;
        this.currentUsedCapacity = currentUsedCapacity;
    }

    public Locker(Ticket ticket, int capacity, int currentUsedCapacity) {
        this.ticket = ticket;
        this.capacity = capacity;
        this.currentUsedCapacity = currentUsedCapacity;
    }

    public boolean hasEmptyCapacity() {
        return capacity > currentUsedCapacity;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket store() throws LockerException {
        if (hasEmptyCapacity()) return new Ticket(TicketTypes.VALID_TICKET);
        else throw new LockerException("存包失败 提示储物柜已满");
    }

    private boolean checkTicket(Ticket ticket) throws LockerException {
        switch (ticket.getTicketType()) {
            case VALID_TICKET:
                return true;
            case FORGED_TICKET:
                throw new LockerException("该票为伪造，无效");
            case USED_TICKET:
                throw new LockerException("该票已使用，无效");
            default:
                return false;
        }
    }

    public Bag getBag(Ticket ticket) throws LockerException {
        setTicket(ticket);
        if (this.checkTicket(this.ticket)) {
            return new Bag();
        }
        return null;
    }
}
