package tdd;

import java.util.LinkedList;
import java.util.List;

public class PrimitiveRobot {
    private List<Locker> lockers;
    private LinkedList<Bag> bags;

    public PrimitiveRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public PrimitiveRobot(List<Locker> lockers, LinkedList<Bag> bags) {
        this.lockers = lockers;
        this.bags = bags;
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
            this.bags.add(bag);
            return new Ticket(TicketTypes.VALID_TICKET, bags.indexOf(bag));
        }
        return null;
    }

    public Bag getBag(Ticket ticket) throws LockerException {
        TicketTypes ticketTypes = ticket.getTicketType();
        if (ticketTypes.equals(TicketTypes.FORGED_TICKET)) {
            throw new LockerException("该票为伪造，无效");
        } else if (ticketTypes.equals(TicketTypes.USED_TICKET)) {
            throw new LockerException("该票已使用，无效");
        }

        if (ticket.getBagNumber() <= this.bags.size()) {
            return this.bags.get(ticket.getBagNumber());
        }
        return null;
    }
}
