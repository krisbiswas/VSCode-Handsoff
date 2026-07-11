package strategy.fee;

import java.util.Map;

import entities.Ticket;
import entities.VehicleType;

public class DynamicRateFeeStrategy implements FeeCalculator {
    private Map<VehicleType, Double> rates;

    public DynamicRateFeeStrategy() {
        this.rates = new java.util.HashMap<>();
        for (VehicleType vehicleType : VehicleType.values()) {
            rates.put(vehicleType, 10.0);
        }
    }

    @Override
    public double calculateFee(Ticket ticket, long exitTime) {
        // long duration = (exitTime - ticket.getEntryTime()) / (1000 * 60 * 60);
        long duration = (exitTime - ticket.getEntryTime()) / (1000);
        return rates.get(ticket.getVehicle().getVehicleType()) * duration;
    }

    @Override
    public void updateRate(double newRate, VehicleType vehicleType) {
        if(vehicleType == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }
        rates.put(vehicleType, newRate);
    }
}
