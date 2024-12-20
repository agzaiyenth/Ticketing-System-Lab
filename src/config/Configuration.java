package config;

import com.google.gson.Gson;
import exceptions.InvalidConfigurationException;

import java.io.*;

public class Configuration {
    private final int totalTickets;
    private final int ticketReleaseRate;
    private final int customerRetrievalRate;
    private final int maxTicketCapacity;

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) throws InvalidConfigurationException {
        if (totalTickets <= 0 || ticketReleaseRate <= 0 || customerRetrievalRate <= 0 || maxTicketCapacity <= 0) {
            throw new InvalidConfigurationException("All configuration values must be positive integers.");
        }
        if (totalTickets > maxTicketCapacity) {
            throw new InvalidConfigurationException("Total tickets cannot exceed max ticket capacity.");
        }
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public static Configuration loadFromFile(String filePath) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Configuration.class);
        }
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void saveToFile(String filePath) throws IOException {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        }
    }
}