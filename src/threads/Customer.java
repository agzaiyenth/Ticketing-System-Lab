package threads;

import core.AbstractTicketHandler;
import core.TicketPool;
import core.TicketRetrievalStrategy;
import logging.Logger;

public class Customer extends AbstractTicketHandler implements Runnable {
    private final TicketRetrievalStrategy retrievalStrategy;
    private int customerRetrievalRate;
    public Customer(TicketPool ticketPool,int customerRetrievalRate ,TicketRetrievalStrategy retrievalStrategy) {
        super(ticketPool);
        this.retrievalStrategy = retrievalStrategy;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (true) {
            String ticket = retrievalStrategy.retrieveTicket(ticketPool);
            if (ticket != null) {
                Logger.log("Customer retrieved: " + ticket);
            } else {
                Logger.log("Customer found no tickets available.");
                continue;
            }
            try {
                Thread.sleep(customerRetrievalRate);
            } catch (InterruptedException e) {
                Logger.log("Customer interrupted.");
            }
        }
    }

    @Override
    public void handleTickets() {
        run();
    }
}