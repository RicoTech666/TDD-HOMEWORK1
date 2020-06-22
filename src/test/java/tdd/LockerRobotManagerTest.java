package tdd;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import tdd.exception.LockerException;
import tdd.robot.LockerRobot;
import tdd.robot.LockerRobotManager;
import tdd.robot.PrimaryLockerRobot;
import tdd.robot.SmartLockerRobot;

import java.util.Arrays;
import java.util.List;

public class LockerRobotManagerTest {
    @Test
    public void should_return_ticket_when_LockerRobotManager_store_bag_given_one_primaryLockerRobot_has_one_locker_without_capacity_and_one_smartLockerRobot_has_two_lockers_with_1st_no_capacity_2nd_has_capacity() throws LockerException {
        List<Locker> primaryLockerRobotLockers = Arrays.asList(new Locker(1, 10, 10));
        List<Locker> smartLockerRobotLockers = Arrays.asList(new Locker(1, 10, 10), new Locker(2, 10, 8));
        List<Locker> lockerRobotManagerLockers = Arrays.asList(new Locker(1, 10, 10));
        LockerRepo primaryLockerRobotLockerRepo = new LockerRepo(primaryLockerRobotLockers);
        LockerRepo smartLockerRobotLockerRepo = new LockerRepo(smartLockerRobotLockers);
        LockerRepo lockerRobotManagerLockerRepo = new LockerRepo(lockerRobotManagerLockers);

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(primaryLockerRobotLockerRepo);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(smartLockerRobotLockerRepo);

        List<LockerRobot> managedLockerRobots = Arrays.asList(primaryLockerRobot, smartLockerRobot);

        LockerRobotManager lockerRobotManager = new LockerRobotManager(managedLockerRobots, lockerRobotManagerLockerRepo);
        Bag bagStore = new Bag(1);

        Ticket ticket = lockerRobotManager.storeBag(bagStore);
        Bag bagGet = lockerRobotManager.getBag(ticket);

        Assertions.assertEquals(bagStore, bagGet);
    }

    @Test
    public void should_return_ticket_when_LockerRobotManager_store_bag_given_one_primaryLockerRobot_has_one_locker_without_capacity_and_one_smartLockerRobot_has_two_lockers_with_1st_has_capacity_2nd_has_no_capacity() throws LockerException {
        List<Locker> primaryLockerRobotLockers = Arrays.asList(new Locker(1, 10, 10));
        List<Locker> smartLockerRobotLockers = Arrays.asList(new Locker(1, 11, 10), new Locker(2, 10, 10));
        List<Locker> lockerRobotManagerLockers = Arrays.asList(new Locker(1, 10, 10));
        LockerRepo primaryLockerRobotLockerRepo = new LockerRepo(primaryLockerRobotLockers);
        LockerRepo smartLockerRobotLockerRepo = new LockerRepo(smartLockerRobotLockers);
        LockerRepo lockerRobotManagerLockerRepo = new LockerRepo(lockerRobotManagerLockers);

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(primaryLockerRobotLockerRepo);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(smartLockerRobotLockerRepo);

        List<LockerRobot> managedLockerRobots = Arrays.asList(primaryLockerRobot, smartLockerRobot);

        LockerRobotManager lockerRobotManager = new LockerRobotManager(managedLockerRobots, lockerRobotManagerLockerRepo);
        Bag bagStore = new Bag(1);

        Ticket ticket = lockerRobotManager.storeBag(bagStore);
        Bag bagGet = lockerRobotManager.getBag(ticket);

        Assertions.assertEquals(bagStore, bagGet);
    }
}