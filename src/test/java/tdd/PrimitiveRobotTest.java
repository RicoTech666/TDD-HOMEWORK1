package tdd;

import org.junit.Assert;
import org.junit.Test;
import tdd.exception.LockerException;
import tdd.robot.PrimitiveLockerRobot;

import java.util.Arrays;
import java.util.List;

public class PrimitiveRobotTest {
    @Test
    public void should_return_ticket_when_robot_store_package_given_two_locker_have_capacity() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 5), new Locker(2, 8, 0));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        PrimitiveLockerRobot primitiveLockerRobot = new PrimitiveLockerRobot(lockerRepo);
        Bag bag = new Bag(1);
        Ticket ticket = primitiveLockerRobot.storeBagByPrimitiveLockerRobot(bag);
        
        Assert.assertTrue(ticket.getBagNumber() == lockerRepo.getBag(ticket).getId());
    }
    
    @Test
    public void should_return_ticket_when_robot_store_package_given_one_locker_full_and_one_locker_have_capacity() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 10), new Locker(2, 8, 0));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        PrimitiveLockerRobot primitiveLockerRobot = new PrimitiveLockerRobot(lockerRepo);
        Bag bag = new Bag(2);
        Ticket ticket = primitiveLockerRobot.storeBagByPrimitiveLockerRobot(bag);
        
        Assert.assertTrue(ticket.getBagNumber() == lockerRepo.getBag(ticket).getId());
    }
    
    @Test
    public void should_throw_exception_when_robot_store_package_given_two_lockers_both_have_no_capacity() {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 10), new Locker(2, 8, 8));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        PrimitiveLockerRobot primitiveLockerRobot = new PrimitiveLockerRobot(lockerRepo);
        Bag bag = new Bag(3);
        
        Assert.assertThrows(LockerException.class, () -> {
            primitiveLockerRobot.storeBagByPrimitiveLockerRobot(bag);
        });
    }
    
    @Test
    public void should_return_bag_when_robot_get_package_given_valid_ticket() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 10), new Locker(2, 8, 0));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        PrimitiveLockerRobot primitiveLockerRobot = new PrimitiveLockerRobot(lockerRepo);
        Bag bag = new Bag(4);
        Ticket ticket = primitiveLockerRobot.storeBagByPrimitiveLockerRobot(bag);
        
        Assert.assertNotNull(primitiveLockerRobot.getBag(ticket));
    }
    
    @Test
    public void should_throw_exception_when_robot_get_package_given_forged_ticket() {
        List<Locker> lockers = Arrays.asList(new Locker(1, 10, 0), new Locker(2, 8, 0));
        LockerRepo lockerRepo = new LockerRepo(lockers);
        PrimitiveLockerRobot primitiveLockerRobot = new PrimitiveLockerRobot(lockerRepo);
        Ticket ticket = new Ticket(TicketTypes.FORGED_TICKET, 1, 8);
        
        Assert.assertThrows(LockerException.class, () -> {
            primitiveLockerRobot.getBag(ticket);
        });
    }
}
