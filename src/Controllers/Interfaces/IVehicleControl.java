package Controllers.Interfaces;

import Entities.Vehicle;
import java.util.List;

public interface IVehicleControl {
    boolean addVehicle(String vehicle_number);
    List<Vehicle> listVehicles();
    boolean reserveSpot(int spot_number); // только один параметр!
}