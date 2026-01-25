package Repositories;

import Entities.Reservation;
import Repositories.Interfaces.IReserveRepo;
import edu.aitu.oop3.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReservationRepo implements IReserveRepo {
    @Override
    public boolean makeReservation(String vehicle_number, int spot_number) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO reservations(occupied_spot, vehicle_number, reservation_time) VALUES(?,?,DEFAULT)";
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setInt(1,spot_number);
            statement.setString(2,vehicle_number);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Reservation finishReservation(int reservation_id) {
        try{
            Connection connection = DatabaseConnection.getConnection();

            //getting current values
            String sql = "SELECT * FROM reservations WHERE reservation_id=?";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,reservation_id);
            ResultSet result=statement.executeQuery();
            Reservation reservation;


            //returning result
            if(result.next()){
                //setting the reserved until
                Timestamp reserved_until=result.getTimestamp("reservation_time");
                reserved_until = Timestamp.valueOf(reserved_until.toLocalDateTime()
                        .plusHours(new Random().nextInt(10) + 1));
                String sql1="UPDATE reservations SET reserved_until=? WHERE reservation_id= ?";
                PreparedStatement statement1 = connection.prepareStatement(sql1);
                statement1.setTimestamp(1, reserved_until);
                statement1.setInt(2,reservation_id);
                statement1.execute();

                //making random reservation finish time

                reservation = new Reservation(reservation_id,
                        result.getInt("occupied_spot"),
                        result.getString("vehicle_number"),
                        result.getTimestamp("reservation_time"),
                        reserved_until);
                return reservation;
            }

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reservation> listReservations() {
        List<Reservation> reservations=new ArrayList<>();
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql="SELECT * FROM reservations";
            PreparedStatement statement=connection.prepareStatement(sql);
            ResultSet result=statement.executeQuery();

            while(result.next()){
                Reservation reservation = new Reservation(result.getInt("reservation_id"),
                        result.getInt("occupied_spot"),
                        result.getString("vehicle_number"),
                        result.getTimestamp("reservation_time"),
                        result.getTimestamp("reserved_until"));
                reservations.add(reservation);
            }
            return reservations;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return reservations;
    }

    @Override
    public Reservation getReservation(int reservation_id) {
        try{
            Connection connection = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM reservations WHERE reservation_id=?";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,reservation_id);
            ResultSet result=statement.executeQuery();
            Reservation reservation;
            if(result.next()){
                reservation = new Reservation(reservation_id,
                        result.getInt("occupied_spot"),
                        result.getString("vehicle_number"),
                        result.getTimestamp("reservation_time"),
                        result.getTimestamp("reserved_until"));
                return reservation;
            }

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
}
