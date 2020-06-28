package tdd.robot;

import tdd.Bag;
import tdd.LockerRepo;
import tdd.Ticket;
import tdd.exception.LockerException;

public class PrimaryLockerRobot extends LockerRobot {
    
    private LockerRepo repo;
    
    public PrimaryLockerRobot(LockerRepo repo) {
        this.repo = repo;
    }

    @Override
    public LockerRepo getRepo() {
        return repo;
    }

    @Override
    public Bag getBag(Ticket ticket) throws LockerException {
        return repo.getBag(ticket);
    }

    @Override
    public Ticket storeBag(Bag bag) throws LockerException {
        return repo.storeBagByPrimitiveLockerRobot(bag);
    }
}
