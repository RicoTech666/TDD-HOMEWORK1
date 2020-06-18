package tdd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import tdd.exception.LockerException;
import tdd.robot.PrimitiveLockerRobot;
import tdd.robot.SmartLockerRobot;

import java.util.Arrays;
import java.util.List;

public class SmartRobotTest {
    @Test
    public void should_return_ticket_when_SmartLockerRobot_store_bag_given_two_lockers_have_capacity_and_one_bigger_than_another() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 5), new Locker(2, 8, 0));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        Bag bag = new Bag(1);
        Ticket ticket = smartLockerRobot.storeBagBySmartLockerRobot(bag);
        
        Assert.assertTrue(ticket.getBagNumber() == lockers.get(1).getBag(ticket).getId());
    }
    
    @Test
    public void should_return_ticket_and_store_in_1st_locker_when_SmartLockerRobot_store_bag_given_two_lockers_have_same_leftover_capacity() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 5), new Locker(2, 10, 5));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        Bag bag = new Bag(1);
        Ticket ticket = smartLockerRobot.storeBagBySmartLockerRobot(bag);
        
        Assert.assertTrue(ticket.getBagNumber() == lockers.get(0).getBag(ticket).getId());
    }
    
    @Test
    public void should_throw_exception_when_SmartLockerRobot_store_package_given_two_lockers_both_have_no_capacity() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 10), new Locker(2, 10, 10));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        Bag bag = new Bag(1);
        Assertions.assertThrows(LockerException.class, () -> {
            smartLockerRobot.storeBagBySmartLockerRobot(bag);
        });
    }
    
    @Test
    public void should_return_bag_when_SmartLockerRobot_get_package_given_valid_ticket() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 8), new Locker(2, 10, 10));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        Bag bag = new Bag(1);
        Ticket ticket = smartLockerRobot.storeBagBySmartLockerRobot(bag);
        Assertions.assertTrue(bag.getId() == lockerRepo.getBag(ticket).getId());
    }
    
    @Test
    public void should_throw_exception_when_SmartLockerRobot_get_package_given_forged_ticket() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 8), new Locker(2, 10, 10));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        Assertions.assertThrows(LockerException.class, () -> {
            smartLockerRobot.getBag(new Ticket(TicketTypes.FORGED_TICKET, -1, 8));
        });
    }
    
    @Test
    public void should_return_bag_when_SmartLockerRobot_store_bag_and_PrimitiveLockerRobot_get_bag_given_valid_ticket() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 8), new Locker(2, 10, 10));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        Bag bagStore = new Bag(1);
        Ticket ticket = smartLockerRobot.storeBagBySmartLockerRobot(bagStore);
        PrimitiveLockerRobot primitiveLockerRobot = new PrimitiveLockerRobot(lockerRepo);
        Bag bagGet = primitiveLockerRobot.getBag(ticket);
        Assertions.assertEquals(bagStore, bagGet);
    }
}
