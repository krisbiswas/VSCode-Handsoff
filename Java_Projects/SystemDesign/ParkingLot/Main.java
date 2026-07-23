import java.util.Optional;

import entities.Ticket;
import entities.VehicleType;
import strategy.fee.DynamicRateFeeStrategy;
import strategy.fee.FeeCalculator;

class Main {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = new ParkingLot(2, new int[]{5, 5}, new VehicleType[][]{
            {VehicleType.MOTORCYCLE, VehicleType.CAR, VehicleType.MOTORCYCLE, VehicleType.TRUCK, VehicleType.TRUCK},
            {VehicleType.CAR, VehicleType.CAR, VehicleType.MOTORCYCLE, VehicleType.CAR, VehicleType.TRUCK}
        });

        FeeCalculator calc = new DynamicRateFeeStrategy();
        calc.updateRate(20, VehicleType.CAR);
        calc.updateRate(15, VehicleType.MOTORCYCLE);
        calc.updateRate(35, VehicleType.TRUCK);
        parkingLot.setFeeCalculator(calc);

        Optional<Ticket> ticket1 = parkingLot.entry(VehicleFactory.createVehicle("ABC123", VehicleType.CAR));
        ticket1.ifPresent(t -> System.out.println("Vehicle with license plate " + t.getVehicle().getLicensePlate() +"("+ t.getVehicle().getVehicleType() +")"+ " parked at spot " + t.getParkingSpot().getSpotNumber()));
        Optional<Ticket> ticket2 = parkingLot.entry(VehicleFactory.createVehicle("ABC121", VehicleType.MOTORCYCLE));
        ticket2.ifPresent(t -> System.out.println("Vehicle with license plate " + t.getVehicle().getLicensePlate() +"("+ t.getVehicle().getVehicleType() +")"+ " parked at spot " + t.getParkingSpot().getSpotNumber()));
        Thread.sleep(2000);
        Optional<Ticket> ticket3 = parkingLot.entry(VehicleFactory.createVehicle("ABC341", VehicleType.CAR));
        ticket3.ifPresent(t -> System.out.println("Vehicle with license plate " + t.getVehicle().getLicensePlate() +"("+ t.getVehicle().getVehicleType() +")"+ " parked at spot " + t.getParkingSpot().getSpotNumber()));
        Thread.sleep(2000);
        Optional<Ticket> ticket4 = parkingLot.entry(VehicleFactory.createVehicle("ABC146", VehicleType.CAR));
        ticket4.ifPresent(t -> System.out.println("Vehicle with license plate " + t.getVehicle().getLicensePlate() +"("+ t.getVehicle().getVehicleType() +")"+ " parked at spot " + t.getParkingSpot().getSpotNumber()));
        Optional<Ticket> ticket5 = parkingLot.entry(VehicleFactory.createVehicle("BMC146", VehicleType.MOTORCYCLE));
        ticket5.ifPresent(t -> System.out.println("Vehicle with license plate " + t.getVehicle().getLicensePlate() +"("+ t.getVehicle().getVehicleType() +")"+ " parked at spot " + t.getParkingSpot().getSpotNumber()));
        Thread.sleep(2000);
        
        Optional<Double> fee1 = parkingLot.exit("ABC123");
        fee1.ifPresent(f -> System.out.println("Parking fee for ABC123: $" + f));

        Optional<Ticket> ticket6 = parkingLot.entry(VehicleFactory.createVehicle("ABC145", VehicleType.TRUCK));
        ticket6.ifPresent(t -> System.out.println("Vehicle with license plate " + t.getVehicle().getLicensePlate() +"("+ t.getVehicle().getVehicleType() +")"+ " parked at spot " + t.getParkingSpot().getSpotNumber()));
        Optional<Ticket> ticket7 = parkingLot.entry(VehicleFactory.createVehicle("CNA145", VehicleType.MOTORCYCLE));
        ticket7.ifPresent(t -> System.out.println("Vehicle with license plate " + t.getVehicle().getLicensePlate() +"("+ t.getVehicle().getVehicleType() +")"+ " parked at spot " + t.getParkingSpot().getSpotNumber()));
        Optional<Ticket> ticket8 = parkingLot.entry(VehicleFactory.createVehicle("MHA145", VehicleType.MOTORCYCLE));
        ticket8.ifPresent(t -> System.out.println("Vehicle with license plate " + t.getVehicle().getLicensePlate() +"("+ t.getVehicle().getVehicleType() +")"+ " parked at spot " + t.getParkingSpot().getSpotNumber()));
        
        Optional<Double> fee2 = parkingLot.exit("ABC341");
        fee2.ifPresent(f -> System.out.println("Parking fee for ABC341: $" + f));
        
        Thread.sleep(1000);
        
        Optional<Double> fee3 = parkingLot.exit("ABC145");
        fee3.ifPresent(f -> System.out.println("Parking fee for ABC145: $" + f));
        // parkingLot.exit("ABC341");
        // parkingLot.exit("ABC145");
    }
}