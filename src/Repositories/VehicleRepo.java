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
                        result.getString("vehicle_number"),
                        result.getInt("reserved_spot_number")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return vehicles;
    }

    @Override
    public boolean occupy_spot(int spot_number, String vehicle_number) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "UPDATE vehicles SET reserved_spot_number=? WHERE vehicle_number = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, spot_number);
            statement.setString(2, vehicle_number);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean freeSpot(String vehicle_number) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "UPDATE vehicles SET reserved_spot_number=NULL WHERE vehicle_number = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vehicle_number);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Vehicle getVehicle(String vehicle_number) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM vehicles WHERE vehicle_number=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vehicle_number);
            ResultSet result = statement.executeQuery();
            if(result.next()){
            return new Vehicle(result.getString("vehicle_number"),
                    result.getInt("reserved_spot_number"));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
}