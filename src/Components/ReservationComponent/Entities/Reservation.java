package Components.ReservationComponent.Entities;

import java.sql.Timestamp;

public class Reservation {
    private int id;
    private int occupied_spot;
    private String vehicle_number;
    private Timestamp reserved_from;
    private Timestamp reserved_until;


    public Reservation(int id, int occupied_spot, String vehicle_number, Timestamp reserved_from, Timestamp reserved_until){
        setId(id);
        setOccupied_spot(occupied_spot);
        setVehicle_number(vehicle_number);
        setReserved_from(reserved_from);
        setReserved_until(reserved_until);
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public int getOccupied_spot() {
        return occupied_spot;
    }

    public void setOccupied_spot(int occupied_spot) {
        this.occupied_spot = occupied_spot;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public Timestamp getReserved_from() {
        return reserved_from;
    }

    public void setReserved_from(Timestamp reserved_from) {
        this.reserved_from = reserved_from;
    }

    public Timestamp getReserved_until() {
        return reserved_until;
    }

    public void setReserved_until(Timestamp reserved_until) {
        this.reserved_until = reserved_until;
    }

    @Override
    public String toString() {
        return "Reservation id: " +id+
                ", occupied spot: " + occupied_spot+
                ", vehicle number: " + vehicle_number+
                ", reserved from: " + reserved_from+
                ", reserved until: " + reserved_until+
                "}";
    }
}
