package Controllers.Interfaces;

public interface IParkControl {
    String addSpot();
    String listEmptySpots();
    String reserveSpot(int spot_number, String vehicle_number);
}
