package Controllers;

import Controllers.Interfaces.IVehicleControl;
import Entities.Vehicle;
import Repositories.Interfaces.IVehicleRepo;
import Repositories.Interfaces.IParkingSpotRepo;
import java.util.List;

public class VehicleController implements IVehicleControl {
    private final IVehicleRepo vehicleRepo;
    private final IParkingSpotRepo parkingSpotRepo;

    public VehicleController(IVehicleRepo vehicleRepo, IParkingSpotRepo parkingSpotRepo) {
        this.vehicleRepo = vehicleRepo;
        this.parkingSpotRepo = parkingSpotRepo;
    }

    @Override
    public boolean addVehicle(String vehicle_number) {
        Vehicle vehicle = new Vehicle(vehicle_number);
        return vehicleRepo.addVehicle(vehicle);
    }

    @Override
    public List<Vehicle> listVehicles() {
        return vehicleRepo.listVehicles();
    }

    @Override
    public boolean reserveSpot(int spot_number) {
        System.out.println("Need vehicle number!");
        return false;
    }

    public boolean reserveSpot(int spot_number, String vehicle_number) {
        return parkingSpotRepo.reserveSpot(spot_number, vehicle_number);
    }
}