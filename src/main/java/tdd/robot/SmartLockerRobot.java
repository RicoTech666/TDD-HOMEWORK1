package tdd.robot;

import tdd.Bag;
import tdd.LockerRepo;
import tdd.Ticket;
import tdd.exception.LockerException;

public class SmartLockerRobot {
    private LockerRepo repo;
    
    public SmartLockerRobot(LockerRepo repo) {
        this.repo = repo;
    }
    
    public Bag getBag(Ticket ticket) throws LockerException {
        return repo.getBag(ticket);
    }
    
    public Ticket storeBagBySmartLockerRobot(Bag bag) throws LockerException {
        return repo.storeBagBySmartLockerRobot(bag);
    }
}
