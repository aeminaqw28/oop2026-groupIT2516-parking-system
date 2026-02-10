package Components.ReservationComponent.Controllers;

import Components.ReservationComponent.Controllers.Interfaces.IVehicleControl;
import Components.ReservationComponent.Entities.Vehicle;
import Exceptions.InvalidVehiclePlate;
import Exceptions.ObjectNotFound;
import Components.ReservationComponent.Repositories.Interfaces.IVehicleRepo;

import java.util.List;

public class VehicleController implements IVehicleControl {
    private final IVehicleRepo vehicleRepo;

    public VehicleController(IVehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    @Override
    public boolean addVehicle(String vehicle_number) throws InvalidVehiclePlate {
        try {
            if(getVehicle(vehicle_number)!=null){
                throw new InvalidVehiclePlate("This vehicle already exists!");
            }
        } catch (ObjectNotFound objectNotFound) {

        }
        Vehicle vehicle = new Vehicle(vehicle_number);
        boolean vehicleAdded= vehicleRepo.addVehicle(vehicle);
        return vehicleAdded;
    }

    @Override
    public List<Vehicle> listVehicles() {
        return vehicleRepo.listVehicles();
    }

    @Override
    public boolean occupySpot(int spot_number, String vehicle_number) {
        boolean reserved = vehicleRepo.occupy_spot(spot_number,vehicle_number);
        return reserved;
    }

    @Override
    public boolean freeSpot(String vehicle_number) {
        boolean spotFreed = vehicleRepo.freeSpot(vehicle_number);
        return spotFreed;
    }

    @Override
    public Vehicle getVehicle(String vehicle_number) throws ObjectNotFound {
        Vehicle vehicle=vehicleRepo.getVehicle(vehicle_number);
        if(vehicle==null){
            throw new ObjectNotFound("Vehicle was not found");
        }
        return vehicle;
    }
}