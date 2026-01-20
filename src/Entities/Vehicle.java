package Entities;

public class Vehicle {
    private int id;
    private String plateNumber;
    private String type; // CAR, MOTORCYCLE, TRUCK

    public Vehicle() {}

    public Vehicle(String plateNumber, String type) {
        setPlateNumber(plateNumber);
        setType(type);
    }


    public int getId() { return id; }
    public String getPlateNumber() { return plateNumber; }
    public String getType() { return type; }

    public void setId(int id) { this.id = id; }

    public void setPlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Plate number cannot be empty");
        }
        this.plateNumber = plateNumber.trim().toUpperCase();
    }

    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle type cannot be empty");
        }
        this.type = type.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return plateNumber + " (" + type + ")";
    }
}