package Components.ReportingComponent.Patterns.Singleton;

import Components.PaymentComponent.Entities.Tariff;
import Components.PaymentComponent.Repositories.TariffRepo;

public class TariffConfig {
    private int defaultTariffId=2;

    private TariffConfig(){}

    private static class ParkingLotManagerHolder{
        public static final TariffConfig instance = new TariffConfig();
    }

    public static TariffConfig getInstance() {
        return ParkingLotManagerHolder.instance;
    }

    public Tariff getDefaultTariff() {
        TariffRepo repo = new TariffRepo();
        return repo.getTariff(defaultTariffId);
    }
}
