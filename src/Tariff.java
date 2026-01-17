public class Tariff {
    private int id;
    private String name;
    private double basePrice;
    private double pricePerHour;

    public Tariff() {}

    public Tariff(String name, double basePrice, double pricePerHour) {
        setName(name);
        setBasePrice(basePrice);
        setPricePerHour(pricePerHour);
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public double getPricePerHour() { return pricePerHour; }


    public void setId(int id) { this.id = id; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tariff name cannot be empty");
        }
        this.name = name;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("Base price cannot be negative");
        }
        this.basePrice = basePrice;
    }

    public void setPricePerHour(double pricePerHour) {
        if (pricePerHour < 0) {
            throw new IllegalArgumentException("Price per hour cannot be negative");
        }
        this.pricePerHour = pricePerHour;
    }

    @Override
    public String toString() {
        return name + ": " + basePrice + " + " + pricePerHour + "/hour";
    }
}