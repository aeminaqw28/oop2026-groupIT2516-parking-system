package Repositories;

import Entities.Vehicle;
import Repositories.Interfaces.IVehicleRepo;
import edu.aitu.oop3.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepo implements IVehicleRepo {

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO vehicles (vehicle_number) VALUES(?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vehicle.getPlateNumber());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Vehicle> listVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM vehicles";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Vehicle vehicle = new Vehicle(
                        result.getString("vehicle_number")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return vehicles;
    }

    @Override
    public void occupy_spot(int spot_number) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "UPDATE parking_spots SET is_occupied = true WHERE spot_number = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, spot_number);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}