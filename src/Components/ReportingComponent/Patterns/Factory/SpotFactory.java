package Components.ReportingComponent.Patterns.Factory;

import Components.ReservationComponent.Entities.ParkingSpot;
import Components.ReservationComponent.Entities.SubEntities.DisabledSpot;
import Components.ReservationComponent.Entities.SubEntities.ElectricSpot;

public class SpotFactory {
    public ParkingSpot createSpot(String spot_type, int spot_number, String reservedBy){
        switch(spot_type){
            case "Electric":
                return new ElectricSpot(spot_number,reservedBy);
            case "Disabled":
                return new DisabledSpot(spot_number, reservedBy);
            default:
                return new ParkingSpot(spot_number, reservedBy);
        }
    }
}
