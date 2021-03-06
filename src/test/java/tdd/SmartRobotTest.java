package tdd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import tdd.exception.LockerException;
import tdd.robot.PrimaryLockerRobot;
import tdd.robot.SmartLockerRobot;

import java.util.Arrays;
import java.util.List;

public class SmartRobotTest {
    @Test
    public void should_return_ticket_when_SmartLockerRobot_store_bag_given_two_lockers_have_capacity_and_one_bigger_than_another() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10), new Locker(2,8));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        Bag bag = new Bag(1);
        Ticket ticket = smartLockerRobot.storeBag(bag);
        
        Assert.assertTrue(ticket.getBagNumber() == smartLockerRobot.getBag(ticket).getId());
    }
    
    @Test
    public void should_return_ticket_and_store_in_1st_locker_when_SmartLockerRobot_store_bag_given_two_lockers_have_same_leftover_capacity() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10), new Locker(2,10));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        Bag bag = new Bag(1);
        Ticket ticket = smartLockerRobot.storeBag(bag);
        
        Assert.assertTrue(ticket.getBagNumber() == smartLockerRobot.getBag(ticket).getId());
    }
    
    @Test
    public void should_throw_exception_when_SmartLockerRobot_store_package_given_two_lockers_both_have_no_capacity() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1,0), new Locker(2,0));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        Bag bag = new Bag(1);
        
        Assertions.assertThrows(LockerException.class, () -> {
            smartLockerRobot.storeBag(bag);
        });
    }
    
    @Test
    public void should_return_bag_when_SmartLockerRobot_get_package_given_valid_ticket() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 1), new Locker(2,1));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        Bag bag = new Bag(1);
        Ticket ticket = smartLockerRobot.storeBag(bag);
        
        Assertions.assertTrue(bag.getId() == lockerRepo.getBag(ticket).getId());
    }
    
    @Test
    public void should_throw_exception_when_SmartLockerRobot_get_package_given_forged_ticket() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10), new Locker(2,10));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        
        Assertions.assertThrows(LockerException.class, () -> {
            smartLockerRobot.getBag(new Ticket(TicketTypes.FORGED_TICKET, -1, 8));
        });
    }
    
    @Test
    public void should_return_bag_when_SmartLockerRobot_store_bag_and_PrimitiveLockerRobot_get_bag_given_valid_ticket() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10), new Locker(2,10));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockerRepo);
        Bag bagStore = new Bag(1);
        
        Ticket ticket = smartLockerRobot.storeBag(bagStore);
        Bag bagGet = primaryLockerRobot.getBag(ticket);
        
        Assertions.assertEquals(bagStore, bagGet);
    }
    
    @Test
    public void should_return_bag_when_PrimitiveLockerRobot_store_bag_and_SmartLockerRobot_get_bag_given_valid_ticket() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10), new Locker(2,10));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockerRepo);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(lockerRepo);
        Bag bagStore = new Bag(1);
        
        Ticket ticket = primaryLockerRobot.storeBag(bagStore);
        Bag bagGet = smartLockerRobot.getBag(ticket);
        
        Assertions.assertEquals(bagStore, bagGet);
    }
}
