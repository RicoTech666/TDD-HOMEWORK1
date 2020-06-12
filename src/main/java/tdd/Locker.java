package tdd;

public class Locker {
    private boolean empty;
    
    public Locker() {
    }
    
    public Locker( boolean emptyFlag) {
        this.empty = emptyFlag;
    }
    
    public boolean isEmpty() {
        return empty;
    }
    
    public boolean store() throws LockerException {
        if (empty) return true;
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
    
    public boolean getBag(Ticket ticket) throws LockerException {
        return checkTicket(ticket);
    }
}
