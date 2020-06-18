package tdd;

import lombok.Data;
import tdd.exception.LockerException;

import java.util.HashMap;
import java.util.Map;

@Data
public class Locker {
    private int id;
    private int capacity;
    private int currentUsedCapacity;
    private Map<Integer, Bag> bagMap = new HashMap();
    
    public Locker(int id, int capacity, int currentUsedCapacity) {
        this.id = id;
        this.capacity = capacity;
        this.currentUsedCapacity = currentUsedCapacity;
    }
    
    boolean hasEmptyCapacity() {
        return capacity > currentUsedCapacity;
    }
    
    public boolean store(Bag bag) throws LockerException {
        if (hasEmptyCapacity()) {
            bagMap.put(bag.getId(), bag);
            return true;
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
        return checkTicket(ticket) ? bagMap.get(ticket.getBagNumber()) : null;
    }
}
