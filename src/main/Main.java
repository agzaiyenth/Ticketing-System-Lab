package main;

import config.Configuration;
import core.PriorityRetrieval;
import core.TicketPool;
import exceptions.InvalidConfigurationException;
import logging.Logger;
import threads.Customer;
import threads.Vendor;
import UI.CommandLineInterface;

public class Main {
    public static void main(String[] args) throws InvalidConfigurationException {
        Configuration config = CommandLineInterface.configureSystem();
        TicketPool ticketPool = new TicketPool();
        Thread vendor = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate()));
        Thread customer = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate() ,new PriorityRetrieval()));
        vendor.start();
        customer.start();
        try {
            vendor.join();
            customer.join();
        } catch (InterruptedException e) {
            Logger.log("Main thread interrupted.");
        }
        Logger.log("System terminated.");
    }
}