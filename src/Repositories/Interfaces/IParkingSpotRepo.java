package Repositories.Interfaces;

import Entities.ParkingSpot;
import java.util.List;

public interface IParkingSpotRepo {
    boolean addSpot();
    List<ParkingSpot> listEmptySpots();
    boolean reserveSpot(int spot_number, String vehicle_number);
    boolean freeSpot(int spot_number);
    ParkingSpot getSpot(int spot_number);
}
