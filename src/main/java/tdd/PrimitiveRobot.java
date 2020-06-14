package tdd;

import java.util.List;

public class PrimitiveRobot {
    private List<Locker> lockers;

    public PrimitiveRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Locker getUsedLocker() throws LockerException {
        for (Locker locker : lockers) {
            if (locker.hasEmptyCapacity()) {
                return locker;
            }
        }
       throw new LockerException("存包失败，所有储物柜已满");
    }

    public Ticket store() throws LockerException {
        if(getUsedLocker() != null) {
            return new Ticket(TicketTypes.VALID_TICKET);
        }
        return null;
    }
}
