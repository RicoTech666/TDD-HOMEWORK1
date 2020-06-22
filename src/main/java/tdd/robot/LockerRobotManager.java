package tdd.robot;

import tdd.Bag;
import tdd.LockerRepo;
import tdd.Ticket;
import tdd.exception.LockerException;

import java.util.List;

public class LockerRobotManager extends LockerRobot {

    private List<LockerRobot> managedLockerRobots;
    private LockerRepo repo;

    public LockerRobotManager(List<LockerRobot> managedLockerRobots, LockerRepo repo) {
        this.managedLockerRobots = managedLockerRobots;
        this.repo = repo;
    }

    @Override
    public Bag getBag(Ticket ticket) throws LockerException {
        return repo.getBagByLockerRobotManager(ticket, this.managedLockerRobots);
    }

    @Override
    public Ticket storeBag(Bag bag) {
        return repo.storeBagByLockerRobotManager(bag, this.managedLockerRobots);
    }
}
