package tdd;

import lombok.Data;
import tdd.exception.LockerException;

import java.util.HashMap;
import java.util.Map;

@Data
public class Locker {
    private int id;
    private int capacity;
    private Map<Integer, Bag> bagMap = new HashMap<>();
    
    public Locker(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }
    
    boolean hasEmptyCapacity() {
        return capacity > bagMap.size();
    }
    
    public Ticket store(Bag bag) throws LockerException {
        if (hasEmptyCapacity()) {
            this.bagMap.put(bag.getId(), bag);
            return new Ticket(TicketTypes.VALID_TICKET, bag.getId(), id);
        } else throw new LockerException("存包失败 提示储物柜已满");
    }
    
    private boolean checkTicket(Ticket ticket) throws LockerException {
        switch (ticket.getTicketType()) {
            case VALID_TICKET:
                return true;
            case FORGED_TICKET:
                throw new LockerException("该票为伪造，无效");
            default:
                return false;
        }
    }
    
    public Bag getBag(Ticket ticket) throws LockerException {
        return checkTicket(ticket) ? this.bagMap.get(ticket.getBagNumber()) : null;
    }
    
    public int getCurrentUsedCapacity() {
        return bagMap.size();
    }
}
