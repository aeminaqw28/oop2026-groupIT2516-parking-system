package Components.PaymentComponent.Repositories.Interfaces;

import Components.PaymentComponent.Entities.Tariff;

import java.util.List;

public interface ITariffRepo {
    boolean createTariff(Tariff tariff);
    Tariff getTariff(int id);
    Tariff getTariff(String tariff_name);
    List<Tariff> getAllTariffs();
}
