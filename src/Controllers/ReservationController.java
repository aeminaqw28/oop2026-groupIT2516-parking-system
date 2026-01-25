package Controllers;

import Controllers.Interfaces.IReservationControl;
import Entities.Reservation;
import Exceptions.ObjectNotFound;
import Repositories.Interfaces.IReserveRepo;

import java.util.List;

public class ReservationController implements IReservationControl {
    private IReserveRepo repo;
    ReservationController(IReserveRepo repo){
        this.repo=repo;
    }

    @Override
    public boolean makeReservation(String vehicle_number, int spot_number) {
        boolean madeReservation =repo.makeReservation(vehicle_number, spot_number);
        return madeReservation;
    }

    @Override
    public Reservation finishReservation(int reservation_id) {
        Reservation finishedReservation = repo.finishReservation(reservation_id);
        return finishedReservation;
    }

    @Override
    public List<Reservation> listReservations() {
        List<Reservation> reservations=repo.listReservations();
        return reservations;
    }

    @Override
    public Reservation getReservation(int reservation_id) throws ObjectNotFound {
        Reservation reservation = repo.getReservation(reservation_id);
        if(reservation==null){
            throw new ObjectNotFound("Reservation was not found!");
        }
        return reservation;
    }
}
