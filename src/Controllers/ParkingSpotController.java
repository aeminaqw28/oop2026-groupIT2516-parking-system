package Controllers;

import Controllers.Interfaces.IParkControl;
import Entities.ParkingSpot;
import Exceptions.SpotAlreadyReserved;
import Repositories.Interfaces.IParkingSpotRepo;

import java.util.List;

public class ParkingSpotController implements IParkControl {
    private final IParkingSpotRepo repo;
    public ParkingSpotController(IParkingSpotRepo repo){
        this.repo=repo;
    }
    public boolean addSpot(){
        boolean created = repo.addSpot();
        return created;
    }

    @Override
    public List<ParkingSpot> listEmptySpots() {
        List<ParkingSpot> empty_spots = repo.listEmptySpots();

        return empty_spots;
    }

    @Override
    public boolean reserveSpot(int spot_number, String vehicle_number) throws SpotAlreadyReserved {
        if(getSpot(spot_number).getReserved_by()!=null){
            throw new SpotAlreadyReserved("Spot is already reserved!");
        }
        boolean reserved = repo.reserveSpot(spot_number,vehicle_number);
        return reserved;
    }

    @Override
    public boolean freeSpot(int spot_number) {
        boolean spotFreed=repo.freeSpot(spot_number);
        return spotFreed;
    }

    @Override
    public ParkingSpot getSpot(int spot_number) {
        ParkingSpot spot = repo.getSpot(spot_number);
        return spot;
    }
}
