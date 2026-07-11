import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import entities.ParkingSpot;
import entities.VehicleType;

public class Floor {
    private int floorNumber;
    private Map<VehicleType, List<ParkingSpot>> parkingSpots;    

    public Floor(int floorNumber, int numberOfSpots, VehicleType[] vehicleTypes) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new HashMap<>();
        for (VehicleType vehicleType : VehicleType.values()) {
            this.parkingSpots.put(vehicleType, new ArrayList<>());
        }
        for (int i = 0; i < numberOfSpots; i++) {
            ParkingSpot spot = new ParkingSpot("F" + floorNumber + "-" + i, vehicleTypes[i]);
            this.parkingSpots.get(vehicleTypes[i]).add(spot);
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public ParkingSpot getAvailableSpot(VehicleType vehicleType) {
        for (ParkingSpot spot : parkingSpots.get(vehicleType)) {
            if (!spot.isOccupied()) {
                return spot;
            }
        }
        return null;
    }
}
