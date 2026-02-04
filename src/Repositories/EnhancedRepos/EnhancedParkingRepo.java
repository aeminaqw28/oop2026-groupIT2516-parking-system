package Repositories.EnhancedRepos;
import Repositories.Interfaces.IGenericRepo;
import Entities.ParkingSpot;
import Repositories.ParkingSpotRepo;
import edu.aitu.oop3.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnhancedParkingRepo extends ParkingSpotRepo implements IGenericRepo<ParkingSpot, Integer> {
    @Override public ParkingSpot findByIdentifier(Integer id) { return getSpot(id); }
    @Override public List<ParkingSpot> findAll() { List<ParkingSpot> spots = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM parking_spots";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                spots.add(new ParkingSpot(
                        result.getInt("spot_number"),
                        result.getString("vehicle_number")));
            }
            return spots;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return spots; }
    public List<ParkingSpot> findAvailable() {
        return findByCondition(s -> s.getReserved_by() == null);
    }
    public ParkingSpot findFirstAvailable() {
        return findFirst(s -> s.getReserved_by() == null);
    }
}