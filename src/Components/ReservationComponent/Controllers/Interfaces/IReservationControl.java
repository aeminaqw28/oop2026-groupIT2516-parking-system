package Components.ReservationComponent.Controllers.Interfaces;

import Components.ReservationComponent.Entities.Reservation;
import Exceptions.ObjectNotFound;

import java.util.List;

public interface IReservationControl {
    boolean makeReservation(String vehicle_number, int spot_number);
    Reservation finishReservation(int reservation_id);
    List<Reservation> listReservations();
    Reservation getReservation(int reservation_id) throws ObjectNotFound;
}
