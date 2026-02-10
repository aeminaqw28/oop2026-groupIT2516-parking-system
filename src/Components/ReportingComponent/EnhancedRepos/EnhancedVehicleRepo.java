package Components.ReportingComponent.EnhancedRepos;
import Components.ReportingComponent.EnhancedRepos.Interface.IGenericRepo;
import Components.ReservationComponent.Entities.Vehicle;
import Components.ReservationComponent.Repositories.VehicleRepo;

import java.util.List;

public class EnhancedVehicleRepo extends VehicleRepo implements IGenericRepo<Vehicle, String> {
    @Override public Vehicle findByIdentifier(String vehicle_number) {
        return getVehicle(vehicle_number);
    }
    @Override public List<Vehicle> findAll() { return listVehicles(); }
    public List<Vehicle> findUnparked() {
        return findByCondition(v -> v.getReserved_spot() <= 0);
    }
    public List<Vehicle> findByPlate(String plateNumber) {
        return findByCondition(v -> v.getPlateNumber().contains(plateNumber));
    }
}