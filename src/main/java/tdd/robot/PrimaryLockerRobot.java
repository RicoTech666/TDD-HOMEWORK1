package tdd.robot;

import tdd.Bag;
import tdd.LockerRepo;
import tdd.Ticket;
import tdd.exception.LockerException;

public class PrimaryLockerRobot {
    
    private LockerRepo repo;
    
    public PrimaryLockerRobot(LockerRepo repo) {
        this.repo = repo;
    }
    
    public Bag getBag(Ticket ticket) throws LockerException {
        return repo.getBag(ticket);
    }
    
    public Ticket storeBagByPrimitiveLockerRobot(Bag bag) throws LockerException {
        return repo.storeBagByPrimitiveLockerRobot(bag);
    }
}
