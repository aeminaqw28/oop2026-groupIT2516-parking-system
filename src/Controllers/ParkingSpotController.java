package Controllers;

import Controllers.Interfaces.IParkControl;
import Entities.ParkingSpot;
import Repositories.Interfaces.IParkingSpotRepo;
import Repositories.ParkingSpotRepo;

public class ParkingSpotController implements IParkControl {
    private final IParkingSpotRepo repo;
    public ParkingSpotController(IParkingSpotRepo){
        this.repo=repo;
    }
    String addSpot(){
        boolean created = repo.addSpot();
        return created?"Spot was added":"Spot addition failed";
    }
}
