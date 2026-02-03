package Entities.SubEntities;

import Entities.ParkingSpot;

public class ElectricSpot extends ParkingSpot {
    public ElectricSpot(int spotNumber, String reservedBy) {
        super(spotNumber, reservedBy);
    }

    @Override
    public String getDescription() {
        return "Electric spot, has charger, has higher per hour spot.";
    }
}
