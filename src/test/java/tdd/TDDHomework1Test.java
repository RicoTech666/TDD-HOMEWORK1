package tdd;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TDDHomework1Test {

    @Test
    @DisplayName("Given有空储物柜 when存包 then 存包成功 返回一张票")
    public void should_return_ticket_when_store_package_and_have_empty_locker() throws LockerException {
        Ticket ticket = new Ticket(TicketTypes.VALID_TICKET);
        Locker locker = new Locker(ticket, true);

        Assert.assertEquals(true, locker.isEmpty());
        Assert.assertEquals(true, locker.store());
        Assert.assertEquals(ticket, locker.getTicket());

    }

    @Test
    @DisplayName("Given无储物柜 when存包 then 存包失败 提示储物柜已满")
    public void should_throw_Exception_when_store_package_and_have_full_locker() {
        Ticket ticket = new Ticket(TicketTypes.VALID_TICKET);
        Locker locker = new Locker(ticket, false);

        Assert.assertEquals(false, locker.isEmpty());
        Assert.assertThrows(LockerException.class, () -> {
            locker.store();
            new LockerException("存包失败 提示储物柜已满");
        });
    }

    @Test
    @DisplayName("Given票有效 when取包 then 取包成功")
    public void should_success_when_ticket_valid_for_locker() {
        Ticket ticket = new Ticket(TicketTypes.VALID_TICKET);
        Locker locker = new Locker(ticket);

        Assert.assertEquals(ticket, locker.checkTicket());
    }
}