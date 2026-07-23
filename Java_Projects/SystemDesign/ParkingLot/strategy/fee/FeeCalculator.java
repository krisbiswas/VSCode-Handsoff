package strategy.fee;

import entities.Ticket;
import entities.VehicleType;

public interface FeeCalculator {
    void updateRate(double newRate, VehicleType vehicleType);
    double calculateFee(Ticket ticket, long exitTime);
}
