package core;

public class PriorityRetrieval implements TicketRetrievalStrategy{
    @Override
    public String retrieveTicket(TicketPool ticketPool) {
        return ticketPool.removeTicket();
    }
}
