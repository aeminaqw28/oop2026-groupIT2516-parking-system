package Components.ReservationComponent.Repositories.Interfaces;

import Components.ReservationComponent.Entities.Vehicle;
import java.util.List;

public interface IVehicleRepo {
    boolean addVehicle(Vehicle vehicle);
    List<Vehicle> listVehicles();
    boolean occupy_spot(int spot_number, String vehicle_number);
    boolean freeSpot(String vehicle_number);
    Vehicle getVehicle(String vehicle_number);
}
