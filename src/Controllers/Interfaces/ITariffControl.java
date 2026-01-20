package Controllers.Interfaces;


public interface ITariffControl {
    String createTariff(String tariff_name, int basePrice, int pricePerHour);
    String getTariff(int id);
    String getTariff(String tariff_name);
    String getAllTariffs();
}