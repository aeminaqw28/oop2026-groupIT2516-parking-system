package Entities;

public class Vehicle {
    private int id;
    private String plateNumber;
    private int reserved_spot;


    public Vehicle() {}

    public Vehicle(String plateNumber) {
        setPlateNumber(plateNumber);
    }
    public Vehicle(String plateNumber, int reserved_spot) {
        setPlateNumber(plateNumber);
        setReserved_spot(reserved_spot);
    }


    public int getId() { return id; }
    public String getPlateNumber() { return plateNumber; }
    public int getReserved_spot() { return reserved_spot; }

    public void setId(int id) { this.id = id; }

    public void setPlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Plate number cannot be empty");
        }
        this.plateNumber = plateNumber.trim().toUpperCase();
    }

    public void setReserved_spot(int reserved_spot) {
        this.reserved_spot = reserved_spot;
    }

    @Override
    public String toString() {
        String result = "Vehicle number: "+plateNumber+ ", Reserved spot: "+reserved_spot;
        return result;
    }
}