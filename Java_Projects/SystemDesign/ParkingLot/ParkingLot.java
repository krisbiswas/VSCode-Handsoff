import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import entities.ParkingSpot;
import entities.Ticket;
import entities.Vehicle;
import entities.VehicleType;
import strategy.fee.FeeCalculator;
import strategy.fee.FlatRateFeeStrategy;

public class ParkingLot {
    private Floor[] floors;
    private FeeCalculator feeCalculator = new FlatRateFeeStrategy(10.0);
    private Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();
    
    public ParkingLot(int numberOfFloors, int[] numberOfSpotsPerFloor, VehicleType[][] vehicleTypes) {
        this.floors = new Floor[numberOfFloors];
        for (int i = 0; i < numberOfFloors; i++) {
            floors[i] = new Floor(i, numberOfSpotsPerFloor[i], vehicleTypes[i]);
        }
    }

    void setFeeCalculator(FeeCalculator feeCalculator) {
        this.feeCalculator = feeCalculator;
    }

    Optional<Ticket> entry(Vehicle vehicle) {
        VehicleType vehicleType = vehicle.getVehicleType();
        String vehicleLicensePlate = vehicle.getLicensePlate();
        for (Floor floor : floors) {
            ParkingSpot spot = floor.getAvailableSpot(vehicleType);
            if (spot != null) {
                Ticket ticket = new Ticket(vehicle, spot, System.currentTimeMillis());
                spot.occupy(vehicleLicensePlate);
                activeTickets.put(vehicleLicensePlate, ticket);
                return Optional.of(ticket);
            }
        }
        System.out.println("No available parking spots for vehicle type: " + vehicleType);
        return Optional.empty();
    }

    Optional<Double> exit(String vehicleLicensePlate) {
        Ticket ticket = activeTickets.get(vehicleLicensePlate);
        if (ticket == null) {
            System.out.println("Vehicle with license plate " + vehicleLicensePlate + " not found in the parking lot.");
            return Optional.empty();
        }

        long exitTime = System.currentTimeMillis();
        double fee = feeCalculator.calculateFee(ticket, exitTime);
        ticket.getParkingSpot().vacate();
        activeTickets.remove(vehicleLicensePlate);
        return Optional.of(fee);
    }
}
