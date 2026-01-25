package Controllers.Interfaces;

import Entities.ParkingSpot;
import Exceptions.SpotAlreadyReserved;

import java.util.List;

public interface IParkControl {
    boolean addSpot();
    List<ParkingSpot> listEmptySpots();
    boolean reserveSpot(int spot_number, String vehicle_number) throws SpotAlreadyReserved;
    boolean freeSpot(int spot_number);
    ParkingSpot getSpot(int spot_number);
}
