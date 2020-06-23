package tdd;

import lombok.Data;
import tdd.exception.LockerException;
import tdd.robot.LockerRobot;
import tdd.robot.PrimaryLockerRobot;
import tdd.robot.SmartLockerRobot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gavin
 */
@Data
public class LockerRepo {

    private List<Locker> lockers;
    private Map<Integer, Bag> bagMap = new HashMap<>();

    public LockerRepo(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public LockerRepo() {
    }

    private Locker getPrimitiveLockerRobotLocker() throws LockerException {
        for (Locker locker : lockers) {
            if (locker.hasEmptyCapacity()) {
                return locker;
            }
        }
        throw new LockerException("存包失败，所有储物柜已满");
    }

    private Locker getSmartLockerRobotLocker() throws LockerException {
        int lockerFlag = 0;
        Locker targetLocker = null;
        for (Locker locker : lockers) {
            if (locker.getCapacity() - locker.getCurrentUsedCapacity() > lockerFlag) {
                lockerFlag = locker.getCapacity() - locker.getCurrentUsedCapacity();
                targetLocker = locker;
            }
        }
        if (lockerFlag > 0) {
            return targetLocker;
        }
        throw new LockerException("存包失败，所有储物柜已满");
    }

    public Bag getBag(Ticket ticket) throws LockerException {
        TicketTypes ticketTypes = ticket.getTicketType();
        if (ticketTypes.equals(TicketTypes.FORGED_TICKET)) {
            throw new LockerException("该票为伪造，无效");
        }
        return this.bagMap.get(ticket.getBagNumber());
    }

    public Ticket storeBagByPrimitiveLockerRobot(Bag bag) throws LockerException {
        Locker locker = this.getPrimitiveLockerRobotLocker();
        locker.store(bag);
        this.bagMap.put(bag.getId(), bag);
        return new Ticket(TicketTypes.VALID_TICKET, bag.getId(), locker.getId());
    }

    public Ticket storeBagBySmartLockerRobot(Bag bag) throws LockerException {
        Locker locker = this.getSmartLockerRobotLocker();
        locker.store(bag);
        this.bagMap.put(bag.getId(), bag);
        return new Ticket(TicketTypes.VALID_TICKET, bag.getId(), locker.getId());
    }

    public Ticket storeBagByLockerRobotManager(Bag bag, List<LockerRobot> managedLockerRobots) {
        Ticket tempTicket = new Ticket();
        tempTicket.setRobotNumber(-1);
        try {
            if(this.lockers == null) {
                throw new LockerException();
            }
            return storeBagByPrimitiveLockerRobot(bag);
        } catch (LockerException e) {
            for (LockerRobot robot : managedLockerRobots) {
                if (robot instanceof PrimaryLockerRobot) {
                    try {
                        tempTicket = robot.getRepo().storeBagByPrimitiveLockerRobot(bag);
                        tempTicket.setRobotNumber(managedLockerRobots.indexOf(robot));
                    } catch (LockerException ignored) {
                    }
                } else if (robot instanceof SmartLockerRobot) {
                    try {
                        tempTicket = robot.getRepo().storeBagBySmartLockerRobot(bag);
                        tempTicket.setRobotNumber(managedLockerRobots.indexOf(robot));
                    } catch (LockerException ignored) {
                    }
                }
            }
            return tempTicket;
        }
    }

    public Bag getBagByLockerRobotManager(Ticket ticket, List<LockerRobot> managedLockerRobots) throws LockerException {
        if (ticket.getRobotNumber() != -1) {
            int robotNumber = ticket.getRobotNumber();
            return managedLockerRobots.get(robotNumber).getRepo().bagMap.get(ticket.getBagNumber());
        } else {
            return getBag(ticket);
        }

    }
}
