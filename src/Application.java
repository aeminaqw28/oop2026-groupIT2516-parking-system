import Controllers.Interfaces.IMainControl;
import Controllers.MainController;
import Controllers.ParkingAnalyticsService;
import Entities.Reservation;
import Entities.Tariff;
import Entities.Vehicle;
import Exceptions.AllVehiclesParked;
import Exceptions.NoFreeSpots;
import Exceptions.ReservationException;
import Repositories.EnhancedRepos.EnhancedTariffRepo;
import Repositories.EnhancedRepos.EnhancedVehicleRepo;

import java.util.Scanner;

public class Application {
    private IMainControl controller;
    public Application(){
        controller= new MainController();
    }
    Scanner scanner;
    boolean isRunning;
    public void run(){
        isRunning=true;
        scanner = new Scanner(System.in);

        while(isRunning){
            PrintMenu();
            ChooseOption();
            waitForResponse();
            ClearScreen();
        }
    }
    public void PrintMenu(){
        System.out.println("Welcome to Parking Management System\n"+
                "Choose an option:\n" +
                "1.List Empty Spots\n" +
                "2.Reserve a spot\n" +
                "3.Release the spot\n" +
                "4.Calculate cost\n" +
                "5.Quit\n" +
                "6.Show analytics(patterns demo)\n" +
                "7.Show generics");
    }
    public void ChooseOption(){
        int option = scanner.nextInt();
        switch (option){
            case 1:
                ListEmptySpots();
                break;
            case 2:
                ReserveSpot();
                break;
            case 3:
                ReleaseSpot();
                break;
            case 4:
                calculateCost();
            case 5:
                quit();
                break;
            case 6:
                ParkingAnalyticsService service = new ParkingAnalyticsService();
                service.generateReport();
                break;
            case 7:
                demoGenericsLambdas();
        }
    }
    public void ListEmptySpots(){
        try {
            controller.listEmptySpots();
        } catch (NoFreeSpots e) {
            System.out.println(e.getMessage());
        }
    }
    public void ReserveSpot(){
        System.out.println("Choose one of the following options:\n" +
                "1.Reserve spot for already registered vehicle\n" +
                "2.Reserve spot for new vehicle\n" +
                "Type any other number to cancel\n");
        int option = scanner.nextInt();
        System.out.println("Type spot number you want to occupy\n");
        try{
            controller.listEmptySpots();
        } catch (NoFreeSpots e){
            System.out.println(e.getMessage());
            return;
        }
        int spot_number = scanner.nextInt();
        System.out.println("Type plate number of vehicle that you want to occupy spot with\n");
        if(option==1){

            try{
                controller.ListUnparkedVehicles();
            } catch (AllVehiclesParked e){
                System.out.println(e.getMessage());
                return;
            }
        }
        String vehicle_number = scanner.next();

        try{
            if(option==1){
                controller.makeReserveAgain(spot_number,vehicle_number);
            }
            if(option==2){
                controller.makeNewReservation(spot_number,vehicle_number);
            }
        } catch (ReservationException e){
            System.out.println("Reservation error: " + e.getMessage());
        }
    }
    public void ReleaseSpot(){
        System.out.println("Select id of the reservation you want to finish\n");
        controller.listUnfinishedReservations();
        int reservation_id=scanner.nextInt();
        controller.finishReservation(reservation_id);
    }
    public void calculateCost(){
        System.out.println("Select finished id of the reservation you want to calculate cost for\n");
        controller.listFinishedReservations();
        int reservation_id=scanner.nextInt();
        Reservation reservation= null;
        try {
            reservation = controller.getReservationControl().getReservation(reservation_id);
        } catch (Exceptions.ObjectNotFound e) {
            System.out.println("Reservation error: " + e.getMessage());
        }
        System.out.println("Select tariff id you want to use\n"+controller.getTariffControl().getAllTariffs());
        int tariff_id= scanner.nextInt();
        Tariff tariff= null;
        try {
            tariff = controller.getTariffControl().getTariff(tariff_id);
        } catch (Exceptions.ObjectNotFound e) {
            System.out.println("Reservation error: " + e.getMessage());
        }
        int cost = controller.calculate_cost(reservation,tariff);
        System.out.println("Cost is: "+cost+" units\n");
    }
    public void quit(){
        isRunning=false;
    }



    public void ClearScreen(){
        System.out.print("\n".repeat(100));
    }
    public void waitForResponse(){
        System.out.println("Type any number to proceed\n");
        scanner.nextInt();
    }


    public void showAnalytics() {
        ParkingAnalyticsService analyticsService=new ParkingAnalyticsService();
        analyticsService.generateReport();
    }

    public void demoGenericsLambdas() {
        EnhancedVehicleRepo vehicleRepo = new EnhancedVehicleRepo();
        EnhancedTariffRepo tariffRepo = new EnhancedTariffRepo();

        System.out.println("Unparked vehicles:");
        vehicleRepo.findUnparked().forEach(v -> System.out.println(v.toString()));

        System.out.println("Input vehicle number you want to search for\n");
        String number=scanner.next();
        for(Vehicle i: vehicleRepo.findByPlate(number)){
            System.out.println(i.toString());
        }
    }
}
