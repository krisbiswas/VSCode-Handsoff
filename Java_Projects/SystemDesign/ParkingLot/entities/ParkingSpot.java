package entities;

public class ParkingSpot {
    private String spotNumber;
    private boolean isOccupied = false;
    private String vehicleLicensePlate;
    private VehicleType vehicleType;
    // Time of parking can be added later for billing purposes

    public ParkingSpot(String spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
    }

    public void occupy(String vehicleLicensePlate) {
        this.vehicleLicensePlate = vehicleLicensePlate;
        isOccupied = true;
    }

    public void vacate() {
        isOccupied = false;
        vehicleLicensePlate = null;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public String getVehicleLicensePlate() {
        return vehicleLicensePlate;
    }
}
