package Components.ReservationComponent.Entities;

public class ParkingSpot {
    private int spot_number;
    private String reserved_by;

    public ParkingSpot(int spot_number,String reserved_by){
      setReserved_by(reserved_by);
       setSpot_number(spot_number);
    }

    public int getSpot_number() {
        return spot_number;
    }

    public String getReserved_by() {
        return reserved_by;
    }

    public void setReserved_by(String reserved_by) {
        this.reserved_by = reserved_by;
    }

    public void setSpot_number(int spot_number) {
        this.spot_number = spot_number;
    }

    @Override
    public String toString() {
        String result="Spot number: "+spot_number+", Reserved by: "+reserved_by;
        return result;
    }

    public String getDescription(){
        return "Standard spot, nothing unique.";
    }
}

