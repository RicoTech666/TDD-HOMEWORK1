package tdd;

import java.util.List;

public class PrimitiveRobot {
    private List<Locker> lockers;

    public PrimitiveRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Locker getUsedLocker() {
        for (Locker locker : lockers) {
            if (locker.hasEmptyCapacity()) {
                return locker;
            }
        }
        return null;
    }

    public Ticket store() {
        if(getUsedLocker() != null) {
            return new Ticket(TicketTypes.VALID_TICKET);
        }
        return null;
    }
}
