package Components.ReportingComponent.EnhancedRepos;
import Components.ReportingComponent.EnhancedRepos.Interface.IGenericRepo;
import Components.ReservationComponent.Entities.Reservation;
import Components.ReservationComponent.Repositories.ReservationRepo;

import java.util.List;

public class EnhancedReserveRepo extends ReservationRepo implements IGenericRepo<Reservation, Integer> {
    @Override public Reservation findByIdentifier(Integer id) { return getReservation(id); }
    @Override public List<Reservation> findAll() { return listReservations(); }
    public List<Reservation> findActive() {
        return findByCondition(r -> r.getReserved_until() == null);
    }
    public Reservation findFirstActive() {
        return findFirst(r -> r.getReserved_until() == null);
    }
}