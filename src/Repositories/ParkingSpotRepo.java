package Repositories;

import Entities.ParkingSpot;
import Repositories.Interfaces.IParkingSpotRepo;
import edu.aitu.oop3.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpotRepo implements IParkingSpotRepo {

    @Override
    public boolean addSpot(ParkingSpot spot) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO parking_spots(vehicle_number) VALUES(?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,null);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<ParkingSpot> listEmptySpots() {
        List<ParkingSpot> spots = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM parking_spots WHERE vehicle_number=NULL";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                spots.add(new ParkingSpot(
                        result.getInt("spot_number"),
                        result.getString("vehicle_number")));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return spots;
    }

    @Override
    public boolean reserveSpot(int spot_number, String vehicle_number) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "UPDATE parking_spots SET vehicle_number=? WHERE spot_number=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, spot_number);
            statement.setString(2, vehicle_number);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }
}
