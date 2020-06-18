package tdd;

import org.junit.Assert;
import org.junit.Test;
import tdd.exception.LockerException;
import tdd.robot.SmartLockerRobot;

import java.util.Arrays;
import java.util.List;

public class SmartRobotTest {
    @Test
    public void should_return_ticket_when_SmartLockerRobot_store_bag_given_two_lockers_have_capacity_and_one_bigger_than_another() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 5), new Locker(2, 8, 0));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot primitiveLockerRobot = new SmartLockerRobot(lockerRepo);
        Bag bag = new Bag(1);
        Ticket ticket = primitiveLockerRobot.storeBagBySmartLockerRobot(bag);
        
        Assert.assertTrue(ticket.getBagNumber() == lockers.get(1).getBag(ticket).getId());
    }
}
