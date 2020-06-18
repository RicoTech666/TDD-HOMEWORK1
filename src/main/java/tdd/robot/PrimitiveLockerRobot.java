package tdd.robot;

import tdd.Bag;
import tdd.LockerRepo;
import tdd.Ticket;
import tdd.exception.LockerException;

public class PrimitiveLockerRobot {
    
    private LockerRepo repo;
    
    public PrimitiveLockerRobot(LockerRepo repo) {
        this.repo = repo;
    }
    
    public Bag getBag(Ticket ticket) throws LockerException {
        return repo.getBag(ticket);
    }
    
    public Ticket storeBagByPrimitiveLockerRobot(Bag bag) throws LockerException {
        return repo.storeBagByPrimitiveLockerRobot(bag);
    }
}
