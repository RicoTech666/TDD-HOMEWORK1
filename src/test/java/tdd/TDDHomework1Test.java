package tdd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Arrays;
import java.util.List;

public class TDDHomework1Test {

    @Test
    @DisplayName("Given有空储物空间 when存包 then 存包成功 返回一张票")
    public void should_return_ticket_when_store_package_given_locker_has_empty_capacity() throws LockerException {
        int capacity = 10;
        int currentUsedCapacity = 5;
        Ticket ticket = new Ticket(TicketTypes.VALID_TICKET);
        Locker locker = new Locker(ticket, capacity, currentUsedCapacity);

        Assert.assertTrue(locker.hasEmptyCapacity());
        Assert.assertEquals(ticket.getTicketType(), locker.store().getTicketType());
        Assert.assertEquals(ticket, locker.getTicket());
    }

    @Test
    @DisplayName("Given无空储物空间 when存包 then 存包失败 提示储物柜已满")
    public void should_throw_Exception_when_store_package_given_locker_has_no_empty_capacity() {
        int capacity = 5;
        int currentUsedCapacity = 5;
        Ticket ticket = new Ticket(TicketTypes.VALID_TICKET);
        Locker locker = new Locker(ticket, capacity, currentUsedCapacity);

        Assert.assertFalse(locker.hasEmptyCapacity());
        Assert.assertThrows(LockerException.class, () -> {
            locker.store();
            new LockerException("存包失败 提示储物柜已满");
        });
    }

    @Test
    @DisplayName("Given票有效 when取包 then 取包成功")
    public void should_success_when_get_package_given_ticket_valid() throws LockerException {
        Ticket ticket = new Ticket(TicketTypes.VALID_TICKET);
        Locker locker = new Locker();

        Assert.assertNotNull(locker.getBag(ticket));
    }

    @Test
    @DisplayName("Given伪造票 when取包 then取包失败，提示该票为伪造，无效")
    public void should_throw_exception_when_get_package_given_ticket_forged() {
        Ticket ticket = new Ticket(TicketTypes.FORGED_TICKET);
        Locker locker = new Locker();

        Assert.assertThrows(LockerException.class, () -> {
            locker.getBag(ticket);
            new LockerException("存包失败 提示储物柜已满");
        });
    }

    @Test
    @DisplayName("Given伪造票 when取包 then取包失败，提示该票已使用，无效")
    public void should_throw_exception_when_get_package_given_ticket_used() {
        Ticket ticket = new Ticket(TicketTypes.FORGED_TICKET);
        Locker locker = new Locker();

        Assert.assertThrows(LockerException.class, () -> {
            locker.getBag(ticket);
            new LockerException("该票已使用，无效");
        });
    }

    @Test @DisplayName("Given 两个柜子被机器人管理，且都不满 when 让机器人存包 then 存包成功，返回票据")
    public void should_return_ticket_when_robot_store_package_given_two_locker_have_capacity() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(10,5),new Locker(8,0));
        PrimitiveRobot primitiveRobot = new PrimitiveRobot(lockers);
        Locker lockerUsed = primitiveRobot.getUsedLockerForStore();
        Ticket ticket = primitiveRobot.store();

        Assert.assertNotNull(ticket);
        Assert.assertEquals(ticket.getTicketType(),TicketTypes.VALID_TICKET);
        Assert.assertNotNull(lockerUsed);
        Assert.assertEquals(lockerUsed, lockers.get(0));
    }

    @Test
    @DisplayName("Given 两个柜子被机器人管理，且只有第二个不满 when 让机器人存包 then 存包成功，返回票据")
    public void should_return_ticket_when_robot_store_package_given_one_locker_full_and_one_locker_have_capacity() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(10,10),new Locker(8,0));
        PrimitiveRobot primitiveRobot = new PrimitiveRobot(lockers);
        Locker lockerUsed = primitiveRobot.getUsedLockerForStore();
        Ticket ticket = primitiveRobot.store();

        Assert.assertNotNull(ticket);
        Assert.assertEquals(ticket.getTicketType(),TicketTypes.VALID_TICKET);
        Assert.assertNotNull(lockerUsed);
        Assert.assertEquals(lockerUsed, lockers.get(1));
    }

    @Test
    @DisplayName("Given 两个柜子被机器人管理，且都满了 when 让机器人存包 then 存包失败，提示储物柜都已满")
    public void should_throw_exception_when_robot_store_package_given_two_lockers_both_have_no_capacity() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(10,10),new Locker(8,8));
        PrimitiveRobot primitiveRobot = new PrimitiveRobot(lockers);

        Assert.assertThrows(LockerException.class, () -> {
            primitiveRobot.store();
            new LockerException("存包失败，所有储物柜已满");
        });
    }

    @Test
    @DisplayName("Given 两个柜子被机器人管理，when 给机器人有效票 then 取包成功")
    public void should_return_bag_when_robot_get_package_given_valid_ticket() throws LockerException {
        List<Locker> lockers = Arrays.asList(new Locker(10,10),new Locker(8,0));
        PrimitiveRobot primitiveRobot = new PrimitiveRobot(lockers);
        Ticket ticket = new Ticket(TicketTypes.VALID_TICKET);

        Assert.assertNotNull(primitiveRobot.getBag(ticket));
    }
}
