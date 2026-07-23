package entities;

import java.util.UUID;

public class Ticket {
    private String ticketNumber;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private long entryTime;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot, long entryTime) {
        UUID uuid = UUID.randomUUID();
        this.ticketNumber = uuid.toString();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public long getEntryTime() {
        return entryTime;
    }
}
