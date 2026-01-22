package Controllers;

import Controllers.Interfaces.IParkControl;
import Entities.ParkingSpot;
import Repositories.Interfaces.IParkingSpotRepo;
import Repositories.ParkingSpotRepo;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpotController implements IParkControl {
    private final IParkingSpotRepo repo;
    public ParkingSpotController(IParkingSpotRepo repo){
        this.repo=repo;
    }
    public String addSpot(){
        boolean created = repo.addSpot();
        return created?"Spot was added":"Spot addition failed";
    }

    @Override
    public String listEmptySpots() {
        List<ParkingSpot> empty_spots = repo.listEmptySpots();

        String result ="";
        for(ParkingSpot i:empty_spots){
            result+=i.toString()+"\n";
        }

        return result;
    }

    @Override
    public String reserveSpot(int spot_number, String vehicle_number) {
        boolean reserved = repo.reserveSpot(spot_number,vehicle_number);
        return reserved?"Spot has been successfully reserved":"Reservation has failed";
    }
}
