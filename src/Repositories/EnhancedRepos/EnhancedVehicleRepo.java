package Repositories.EnhancedRepos;
import Repositories.Interfaces.IGenericRepo;
import Entities.Vehicle;
import Repositories.VehicleRepo;
import edu.aitu.oop3.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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