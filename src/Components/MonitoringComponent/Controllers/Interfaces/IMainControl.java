package Components.MonitoringComponent.Controllers.Interfaces;

import Components.PaymentComponent.Controllers.Interfaces.ITariffControl;
import Components.ReservationComponent.Controllers.Interfaces.IReservationControl;
import Components.ReservationComponent.Entities.Reservation;
import Components.PaymentComponent.Entities.Tariff;
import Exceptions.AllVehiclesParked;
import Exceptions.NoFreeSpots;
import Exceptions.ReservationException;

public interface IMainControl {
    void makeNewReservation(int spot_number, String vehicle_number);
    void finishReservation(int reservation_id);
    int calculate_cost(Reservation reservation, Tariff tariff);
    void listEmptySpots() throws NoFreeSpots;
    void makeReserveAgain(int spot_number, String vehicle_number) throws ReservationException;
    void listUnfinishedReservations();
    void listFinishedReservations();
    IReservationControl getReservationControl();
    ITariffControl getTariffControl();
    void ListUnparkedVehicles() throws AllVehiclesParked;
}