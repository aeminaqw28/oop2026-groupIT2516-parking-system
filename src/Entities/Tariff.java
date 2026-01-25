package Entities;

public class Tariff {
    private int id;
    private String tariff_name;
    private int basePrice;
    private int pricePerHour;

    public Tariff() {}

    public Tariff(int id, String tariff_name, int basePrice, int pricePerHour) {
        setId(id);
        setTariff_name(tariff_name);
        setBasePrice(basePrice);
        setPricePerHour(pricePerHour);
    }

    public Tariff(String tariff_name, int basePrice, int pricePerHour) {
        setTariff_name(tariff_name);
        setBasePrice(basePrice);
        setPricePerHour(pricePerHour);
    }


    public int getId() { return id; }
    public String getTariff_name() { return tariff_name; }
    public int getBasePrice() { return basePrice; }
    public int getPricePerHour() { return pricePerHour; }


    public void setId(int id) { this.id = id; }

    public void setTariff_name(String tariff_name) {
        if (tariff_name == null || tariff_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tariff tariff_name cannot be empty");
        }
        this.tariff_name = tariff_name;
    }

    public void setBasePrice(int basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("Base price cannot be negative");
        }
        this.basePrice = basePrice;
    }

    public void setPricePerHour(int pricePerHour) {
        if (pricePerHour < 0) {
            throw new IllegalArgumentException("Price per hour cannot be negative");
        }
        this.pricePerHour = pricePerHour;
    }

    @Override
    public String toString() {
        return "Tariff id: " + id +
                ", Tariff name: " + tariff_name +
                ", Base price: " + basePrice +
                ", Price per hour: " + pricePerHour;
    }
}