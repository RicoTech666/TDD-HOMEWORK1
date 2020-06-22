package tdd.robot;

import tdd.Bag;
import tdd.LockerRepo;
import tdd.Ticket;
import tdd.exception.LockerException;

public class LockerRobot {
    LockerRepo repo;

    public LockerRepo getRepo() {
        return repo;
    }

    public Bag getBag(Ticket ticket) throws LockerException {
        return repo.getBag(ticket);
    }

    public Ticket storeBag(Bag bag) throws LockerException {
        return null;
    }
}
