package Controllers;

import Controllers.Interfaces.*;
import Entities.ParkingSpot;
import Entities.Reservation;
import Entities.Tariff;
import Entities.Vehicle;
import Exceptions.*;
import Repositories.Interfaces.IParkingSpotRepo;
import Repositories.Interfaces.IReserveRepo;
import Repositories.Interfaces.ITariffRepo;
import Repositories.Interfaces.IVehicleRepo;
import Repositories.ParkingSpotRepo;
import Repositories.ReservationRepo;
import Repositories.TariffRepo;
import Repositories.VehicleRepo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MainController implements IMainControl {
    private IParkControl parkControl;
    private IReservationControl reservationControl;
    private ITariffControl tariffControl;
    private IVehicleControl vehicleControl;

    private IParkingSpotRepo parkingSpotRepo;
    private IReserveRepo reserveRepo;
    private ITariffRepo tariffRepo;
    private IVehicleRepo vehicleRepo;

    public MainController(){
        parkingSpotRepo = new ParkingSpotRepo();
        reserveRepo = new ReservationRepo();
        tariffRepo = new TariffRepo();
        vehicleRepo = new VehicleRepo();

        parkControl = new ParkingSpotController(parkingSpotRepo);
        reservationControl = new ReservationController(reserveRepo);
        tariffControl = new TariffController(tariffRepo);
        vehicleControl = new VehicleController(vehicleRepo);
    }

    @Override
    public void makeNewReservation(int spot_number, String vehicle_number){
        try{
            vehicleControl.addVehicle(vehicle_number);
            parkControl.reserveSpot(spot_number,vehicle_number);
            vehicleControl.occupySpot(spot_number,vehicle_number);
            reservationControl.makeReservation(vehicle_number,spot_number);

        } catch (SpotAlreadyReserved e){
            System.out.println("Reservation error: " + e.getMessage());
        } catch (InvalidVehiclePlate e){
            System.out.println("Reservation error: " + e.getMessage());
        } catch (ObjectNotFound objectNotFound) {
            throw new RuntimeException(objectNotFound);
        }

    }

    @Override
    public void finishReservation(int reservation_id) {
        Reservation reservation = null;
        try {
            reservation = reservationControl.getReservation(reservation_id);
        } catch (ObjectNotFound e) {
            System.out.println("Reservation error: " + e.getMessage());
        }
        reservationControl.finishReservation(reservation_id);
        vehicleControl.freeSpot(reservation.getVehicle_number());
        parkControl.freeSpot(reservation.getOccupied_spot());
    }

    @Override
    public int calculate_cost(Reservation reservation, Tariff tariff) {
        LocalDateTime reserved_from=reservation.getReserved_from().toLocalDateTime();
        LocalDateTime reserved_until=reservation.getReserved_until().toLocalDateTime();
        long hours = ChronoUnit.HOURS.between(reserved_from,reserved_until);
        int cost = tariff.getBasePrice()+tariff.getPricePerHour()*(int)hours;
        return cost;
    }

    @Override
    public void listEmptySpots() throws NoFreeSpots {
        List<ParkingSpot> spots = parkControl.listEmptySpots();
        if(spots.isEmpty()){
            throw new NoFreeSpots("No Free Spots!");
        }
        for(ParkingSpot i:spots){
            System.out.println(i.toString()+"\n");
        }
    }

    @Override
    public void makeReserveAgain(int spot_number, String vehicle_number) throws ReservationException {
        try{
            if(vehicleControl.getVehicle(vehicle_number).getReserved_spot()>0){
                throw new ReservationException("Reservation already active for this vehicle!");
            }
            parkControl.reserveSpot(spot_number,vehicle_number);
            vehicleControl.occupySpot(spot_number,vehicle_number);
            reservationControl.makeReservation(vehicle_number,spot_number);

        } catch (ObjectNotFound e) {
            System.out.println("Reservation error: " + e.getMessage());
        } catch (SpotAlreadyReserved e){
            System.out.println("Reservation error: " + e.getMessage());
        }
    }

    @Override
    public void listUnfinishedReservations() {
        List<Reservation> reservations = reservationControl.listReservations();
        for(Reservation i:reservations){
            if(i.getReserved_until()==null){
                System.out.println(i.toString()+"\n");
            }
        }
    }

    @Override
    public void listFinishedReservations() {
        List<Reservation> reservations = reservationControl.listReservations();
        for(Reservation i:reservations){
            if(i.getReserved_until()!=null){
                System.out.println(i.toString()+"\n");
            }
        }
    }

    @Override
    public void ListUnparkedVehicles() throws AllVehiclesParked {
        List<Vehicle> vehicles = vehicleControl.listVehicles();
        int count=0;
        for(Vehicle i:vehicles){
            if(i.getReserved_spot()<1){
                count++;
                System.out.println(i.toString()+"\n");
            }
        }
        if(count==0){
            throw new AllVehiclesParked("All Vehicles Are Parked!");
        }
    }

    @Override
    public IReservationControl getReservationControl() {
        return reservationControl;
    }

    @Override
    public ITariffControl getTariffControl() {
        return tariffControl;
    }
}
