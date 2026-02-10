package Components.ReservationComponent.Entities.SubEntities;

import Components.ReservationComponent.Entities.ParkingSpot;

public class ElectricSpot extends ParkingSpot {
    public ElectricSpot(int spotNumber, String reservedBy) {
        super(spotNumber, reservedBy);
    }

    @Override
    public String getDescription() {
        return "Electric spot, has charger, has higher per hour rate.";
    }
}
