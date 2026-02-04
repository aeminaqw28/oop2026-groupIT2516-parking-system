package Controllers;

import Patterns.Singleton.TariffConfig;
import Patterns.Factory.SpotFactory;
import Entities.*;
import Repositories.EnhancedRepos.EnhancedParkingRepo;
import Repositories.EnhancedRepos.EnhancedReserveRepo;
import Repositories.EnhancedRepos.EnhancedTariffRepo;
import Repositories.EnhancedRepos.EnhancedVehicleRepo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingAnalyticsService {
    private EnhancedTariffRepo tariffRepo;
    private EnhancedVehicleRepo vehicleRepo;
    private EnhancedParkingRepo spotRepo;
    private EnhancedReserveRepo reservationRepo;

    public ParkingAnalyticsService() {
        this.tariffRepo = new EnhancedTariffRepo();
        this.vehicleRepo = new EnhancedVehicleRepo();
        this.spotRepo = new EnhancedParkingRepo();
        this.reservationRepo = new EnhancedReserveRepo();
    }

    public void generateReport() {
        System.out.println("=== PARKING ANALYTICS ===");

        TariffConfig config = TariffConfig.getInstance();
        System.out.println("Default Tariff: " + config.getDefaultTariff().getTariff_name());

        long available = spotRepo.findAvailable().size();
        long total = spotRepo.findAll().size();
        System.out.println("Available Spots: " + available + "/" + total);

        long parked = vehicleRepo.findByCondition(v -> v.getReserved_spot() > 0).size();
        System.out.println("Parked Vehicles: " + parked);

        List<Reservation> active = reservationRepo.findActive();
        System.out.println("Active Reservations: " + active.size());

        Map<String, Long> byVehicle = reservationRepo.findAll().stream()
                .collect(Collectors.groupingBy(Reservation::getVehicle_number, Collectors.counting()));

        System.out.println("Top Vehicle: " +
                byVehicle.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(e -> e.getKey() + " (" + e.getValue() + ")")
                        .orElse("None"));

        SpotFactory factory = new SpotFactory();
        ParkingSpot sample = factory.createSpot("Electric", 99, null);
        System.out.println("Sample Factory Spot: " + sample.getDescription());

        System.out.println("=== END REPORT ===");
    }
}