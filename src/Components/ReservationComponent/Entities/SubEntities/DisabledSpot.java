package Components.ReservationComponent.Entities.SubEntities;

import Components.ReservationComponent.Entities.ParkingSpot;

public class DisabledSpot extends ParkingSpot {
    public DisabledSpot(int spotNumber, String reservedBy) {
        super(spotNumber, reservedBy);
    }

    @Override
    public String getDescription() {
        return "Disabled spot, is significantly bigger and cheaper.";
    }
}
