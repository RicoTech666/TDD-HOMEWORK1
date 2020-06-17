package tdd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class LockerTest {

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


}
