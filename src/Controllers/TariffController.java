package Controllers;

import Controllers.Interfaces.ITariffControl;
import Entities.Tariff;
import Exceptions.ObjectNotFound;
import Repositories.Interfaces.ITariffRepo;

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
    public Tariff getTariff(int id) throws ObjectNotFound {
        Tariff tariff = repo.getTariff(id);
        if(tariff==null){
            throw new ObjectNotFound("Tariff was not found!");
        }
        return tariff;
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