package core;

public class IDRetrival implements TicketRetrievalStrategy{
    private final String ticketId;

    public IDRetrival(String ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String retrieveTicket(TicketPool ticketPool) {
        synchronized (ticketPool) {
            for (String ticket : ticketPool.getAllTickets()) {
                if (ticket.equals(ticketId)) {
                    ticketPool.removeSpecificTicket(ticket);
                    return ticket;
                }
            }
        }
        return null;
    }
}
