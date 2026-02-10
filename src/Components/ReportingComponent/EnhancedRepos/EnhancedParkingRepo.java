package Components.ReportingComponent.EnhancedRepos;
import Components.ReportingComponent.EnhancedRepos.Interface.IGenericRepo;
import Components.ReservationComponent.Entities.ParkingSpot;
import Components.ReservationComponent.Repositories.ParkingSpotRepo;

import java.util.ArrayList;
import java.util.List;

public class EnhancedParkingRepo extends ParkingSpotRepo implements IGenericRepo<ParkingSpot, Integer> {
    @Override public ParkingSpot findByIdentifier(Integer id) { return getSpot(id); }
    @Override public List<ParkingSpot> findAll() { List<ParkingSpot> spots = new ArrayList<>();
        return listAllSpots();}
    public List<ParkingSpot> findAvailable() {
        return findByCondition(s -> s.getReserved_by() == null);
    }
    public ParkingSpot findFirstAvailable() {
        return findFirst(s -> s.getReserved_by() == null);
    }
}