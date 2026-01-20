package Controllers;

import Controllers.Interfaces.ITariffControl;
import Entities.Tariff;
import Repositories.Interfaces.ITariffRepo;
import Repositories.TariffRepo;

import java.util.List;

public class TariffController implements ITariffControl {
    private final ITariffRepo repo;
    public TariffController(ITariffRepo repo){
        this.repo=repo;
    }
    public String createTariff(String tariff_name, int basePrice, int pricePerHour) {
        Tariff tariff=new Tariff(tariff_name,basePrice,pricePerHour);
        boolean created = repo.createTariff(tariff);

        return (created?"Tariff was created":"Creation has failed");
    }

    @Override
    public String getTariff(int id) {
        Tariff tariff = repo.getTariff(id);
        return (tariff!=null?tariff.toString():"Tariff was not found.");
    }
    @Override
    public String getTariff(String tariff_name) {
        Tariff tariff = repo.getTariff(tariff_name);
        return (tariff != null ? tariff.toString() : "Tariff was not found.");
    }

    @Override
    public String getAllTariffs() {
        List<Tariff> tariffs = repo.getAllTariffs();

        String result="";
        for (Tariff i:tariffs){
            result+=i.toString()+"\n";
        }
        return result;
    }
}