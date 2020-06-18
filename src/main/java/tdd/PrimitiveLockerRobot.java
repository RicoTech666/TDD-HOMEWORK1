package tdd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimitiveLockerRobot {
    private List<Locker> lockers;
    private Map<Integer, Bag> bagMap = new HashMap<>();
    
    public PrimitiveLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }
    
    Locker getUsedLockerForStore() throws LockerException {
        for (Locker locker : lockers) {
            if (locker.hasEmptyCapacity()) {
                return locker;
            }
        }
        throw new LockerException("存包失败，所有储物柜已满");
    }
    
    public Ticket store(Bag bag) throws LockerException {
        if (getUsedLockerForStore() != null) {
            getUsedLockerForStore().store(bag);
            this.bagMap.put(bag.getId(), bag);
            return new Ticket(TicketTypes.VALID_TICKET, bag.getId());
        }
        return null;
    }
    
    public Bag getBag(Ticket ticket) throws LockerException {
        TicketTypes ticketTypes = ticket.getTicketType();
        if (ticketTypes.equals(TicketTypes.FORGED_TICKET)) {
            throw new LockerException("该票为伪造，无效");
        }
        return this.bagMap.get(ticket.getBagNumber());
    }
}
