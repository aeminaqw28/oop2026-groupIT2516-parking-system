package Repositories.Interfaces;

import Entities.ParkingSpot;
import java.util.List;

public interface IParkingSpotRepo {
    boolean addSpot(ParkingSpot spot);
    List<ParkingSpot> listEmptySpots();
    boolean reserveSpot(int spot_number, String vehicle_number);
}
