package tdd;

import java.util.List;

public class PrimitiveRobot {
    private List<Locker> lockers;

    public PrimitiveRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Locker getUsedLockerForStore() throws LockerException {
        for (Locker locker : lockers) {
            if (locker.hasEmptyCapacity()) {
                return locker;
            }
        }
       throw new LockerException("存包失败，所有储物柜已满");
    }

    public Ticket store() throws LockerException {
        if(getUsedLockerForStore() != null) {
            return new Ticket(TicketTypes.VALID_TICKET);
        }
        return null;
    }

    public Bag getBag(Ticket ticket) throws LockerException {
       return this.lockers.get(0).getBag(ticket);
    }
}
