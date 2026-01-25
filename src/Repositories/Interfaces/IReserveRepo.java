package Repositories.Interfaces;

import Entities.Reservation;

import java.util.List;

public interface IReserveRepo {
    boolean makeReservation(String vehicle_number, int spot_number);
    Reservation finishReservation(int reservation_id);
    List<Reservation> listReservations();
    Reservation getReservation(int reservation_id);
}
