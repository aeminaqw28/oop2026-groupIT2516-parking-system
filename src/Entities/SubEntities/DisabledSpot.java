package Entities.SubEntities;

import Entities.ParkingSpot;

public class DisabledSpot extends ParkingSpot {
    public DisabledSpot(int spotNumber, String reservedBy) {
        super(spotNumber, reservedBy);
    }

    @Override
    public String getDescription() {
        return "Disabled spot, is significantly bigger and cheaper.";
    }
}
