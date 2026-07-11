package strategy.fee;

import entities.Ticket;
import entities.VehicleType;

public class FlatRateFeeStrategy implements FeeCalculator {
    private double flatRate;

    public FlatRateFeeStrategy(double flatRate) {
        this.flatRate = flatRate;
    }

    @Override
    public double calculateFee(Ticket ticket, long exitTime) {
        // double duration = (exitTime - ticket.getEntryTime()) / (1000 * 60 * 60);
        double duration = (exitTime - ticket.getEntryTime()) / (1000);
        return flatRate * duration;
    }

    @Override
    public void updateRate(double newRate, VehicleType vehicleType) {
        flatRate = newRate;
    }
}
