package Controllers.Interfaces;

import Entities.Vehicle;
import Exceptions.InvalidVehiclePlate;
import Exceptions.ObjectNotFound;

import java.util.List;

public interface IVehicleControl {
    boolean addVehicle(String vehicle_number) throws InvalidVehiclePlate;
    List<Vehicle> listVehicles();
    boolean occupySpot(int spot_number, String vehicle_number);
    boolean freeSpot(String vehicle_number);
    Vehicle getVehicle(String vehicle_number) throws ObjectNotFound;
}