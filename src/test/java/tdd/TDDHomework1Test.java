package tdd;


import org.junit.Assert;
import org.junit.Test;

public class TDDHomework1Test {

    @Test
    public void should_return_ticket_when_store_package_and_have_empty_locker() {
        Ticket ticket = new Ticket(TicketTypes.VALID_TICKET);
        Locker locker = new Locker(ticket, true);

        Assert.assertEquals(true,locker.isEmpty());
        Assert.assertEquals(ticket,locker.getTicket());
    }
}
