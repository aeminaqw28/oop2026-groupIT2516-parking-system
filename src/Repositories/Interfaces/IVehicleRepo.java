package Repositories.Interfaces;

import Entities.Vehicle;
import java.util.List;

public interface IVehicleRepo {
    boolean addVehicle(Vehicle vehicle);
    List<Vehicle> listVehicles();
    void occupy_spot(int spot_number);

}
