package Repositories.EnhancedRepos;
import Repositories.Interfaces.IGenericRepo;
import Entities.Tariff;
import Repositories.TariffRepo;

import java.util.List;

public class EnhancedTariffRepo extends TariffRepo implements IGenericRepo<Tariff, Integer> {

    @Override
    public Tariff findByIdentifier(Integer id) {
        return getTariff(id);
    }

    @Override
    public List<Tariff> findAll() {
        return getAllTariffs();
    }

    public List<Tariff> findByName(String keyword) {
        return findByCondition(tariff ->
                tariff.getTariff_name().toLowerCase().contains(keyword.toLowerCase()));
    }
}