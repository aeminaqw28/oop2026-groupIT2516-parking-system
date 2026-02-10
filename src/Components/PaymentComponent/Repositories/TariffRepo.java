package Components.PaymentComponent.Repositories;

import Components.PaymentComponent.Entities.Tariff;
import Components.PaymentComponent.Repositories.Interfaces.ITariffRepo;
import edu.aitu.oop3.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffRepo implements ITariffRepo {
    @Override
    public boolean createTariff(Tariff tariff) {
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO tariffs(tariff_name, base_cost, cost_per_hour) " +
                    "VALUES(?,?,?)";
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1,tariff.getTariff_name());
            statement.setInt(2, tariff.getBasePrice());
            statement.setInt(3,tariff.getPricePerHour());

            statement.execute();
            return true;
        } catch(SQLException e){
            System.out.println("SQL error: "+e.getMessage());
        }
        return false;
    }

    @Override
    public Tariff getTariff(int id) {
        try{
            Connection connection = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM tariffs WHERE tariff_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if(result.next()){
                return new Tariff(
                        result.getString("tariff_name"),
                        result.getInt("base_cost"),
                        result.getInt("cost_per_hour"));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
    @Override
    public Tariff getTariff(String tariff_name) {
        try{
            Connection connection = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM tariffs WHERE tariff_name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tariff_name);

            ResultSet result = statement.executeQuery();
            if(result.next()){
                return new Tariff(
                        result.getString("tariff_name"),
                        result.getInt("base_cost"),
                        result.getInt("cost_per_hour"));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Tariff> getAllTariffs() {
        List<Tariff> tariffs = new ArrayList<>();
        try{
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM tariffs";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            while(result.next()){
                Tariff tariff=new Tariff(
                        result.getInt("tariff_id"),
                        result.getString("tariff_name"),
                        result.getInt("base_cost"),
                        result.getInt("cost_per_hour"));
                tariffs.add(tariff);
            }
            return tariffs;

        } catch (SQLException e){
            System.out.println("SQL error: "+e.getMessage());
        }
        return tariffs;
    }

}

