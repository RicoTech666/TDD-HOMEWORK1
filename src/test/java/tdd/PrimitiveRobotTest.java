package tdd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PrimitiveRobotTest {
    @Test
    @DisplayName("Given 两个柜子被机器人管理，且都不满 when 让机器人存包 then 存包成功，返回票据")
    public void should_return_ticket_when_robot_store_package_given_two_locker_have_capacity() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(10,5),new Locker(8,0));
        LinkedList<Bag> bags = new LinkedList<>();
        PrimitiveRobot primitiveRobot = new PrimitiveRobot(lockers, bags);
        Bag bag = new Bag();
        Locker lockerUsed = primitiveRobot.getUsedLockerForStore();
        Ticket ticket = primitiveRobot.store(bag);

        Assert.assertNotNull(ticket);
        Assert.assertEquals(ticket.getTicketType(),TicketTypes.VALID_TICKET);
        Assert.assertNotNull(lockerUsed);
        Assert.assertEquals(lockerUsed, lockers.get(0));
    }

    @Test
    @DisplayName("Given 两个柜子被机器人管理，且只有第二个不满 when 让机器人存包 then 存包成功，返回票据")
    public void should_return_ticket_when_robot_store_package_given_one_locker_full_and_one_locker_have_capacity() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(10,10),new Locker(8,0));
        LinkedList<Bag> bags = new LinkedList<>();
        PrimitiveRobot primitiveRobot = new PrimitiveRobot(lockers, bags);
        Bag bag = new Bag();
        Locker lockerUsed = primitiveRobot.getUsedLockerForStore();
        Ticket ticket = primitiveRobot.store(bag);

        Assert.assertNotNull(ticket);
        Assert.assertEquals(ticket.getTicketType(),TicketTypes.VALID_TICKET);
        Assert.assertNotNull(lockerUsed);
        Assert.assertEquals(lockerUsed, lockers.get(1));
    }

    @Test
    @DisplayName("Given 两个柜子被机器人管理，且都满了 when 让机器人存包 then 存包失败，提示储物柜都已满")
    public void should_throw_exception_when_robot_store_package_given_two_lockers_both_have_no_capacity() {
        List<Locker> lockers = Arrays.asList(new Locker(10,10),new Locker(8,8));
        PrimitiveRobot primitiveRobot = new PrimitiveRobot(lockers);
        Bag bag = new Bag();

        Assert.assertThrows(LockerException.class, () -> {
            primitiveRobot.store(bag);
            new LockerException("存包失败，所有储物柜已满");
        });
    }

    @Test
    @DisplayName("Given 两个柜子被机器人管理，when 给机器人有效票 then 取包成功")
    public void should_return_bag_when_robot_get_package_given_valid_ticket() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(10,10),new Locker(8,0));
        LinkedList<Bag> bags = new LinkedList<>();
        PrimitiveRobot primitiveRobot = new PrimitiveRobot(lockers, bags);
        Bag bag = new Bag();
        Ticket ticket = primitiveRobot.store(bag);

        Assert.assertNotNull(primitiveRobot.getBag(ticket));
    }

    @Test
    @DisplayName("Given 两个柜子被机器人管理, when 给机器人伪造票 then 取包失败，提示票无效")
    public void should_throw_exception_when_robot_get_package_given_forged_ticket() {
        List<Locker> lockers = Arrays.asList(new Locker(10,0),new Locker(8,0));
        LinkedList<Bag> bags = new LinkedList<>();
        PrimitiveRobot primitiveRobot = new PrimitiveRobot(lockers, bags);
        Ticket ticket = new Ticket(TicketTypes.FORGED_TICKET,1);

        Assert.assertThrows(LockerException.class, () -> {
            primitiveRobot.getBag(ticket);
            new LockerException("该票为伪造，无效");
        });
    }

    @Test
    @DisplayName("Given 两个柜子被机器人管理, when 给机器人已使用的票 then 取包失败，提示票已使用，无效")
    public void should_throw_exception_when_robot_get_package_given_used_ticket() {
        List<Locker> lockers = Arrays.asList(new Locker(10,10),new Locker(8,0));
        PrimitiveRobot primitiveRobot = new PrimitiveRobot(lockers);
        Ticket ticket = new Ticket(TicketTypes.USED_TICKET);

        Assert.assertThrows(LockerException.class, () -> {
            primitiveRobot.getBag(ticket);
            new LockerException("该票已使用，无效");
        });
    }
}
