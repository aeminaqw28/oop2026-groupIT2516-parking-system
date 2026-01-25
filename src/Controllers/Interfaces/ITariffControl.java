package Controllers.Interfaces;


import Entities.Tariff;
import Exceptions.ObjectNotFound;

public interface ITariffControl {
    String createTariff(String tariff_name, int basePrice, int pricePerHour);
    Tariff getTariff(int id) throws ObjectNotFound;
    String getTariff(String tariff_name);
    String getAllTariffs();
}