package Components.ReportingComponent.Patterns.Builder;

import Components.ReservationComponent.Entities.ParkingSpot;
import Components.ReservationComponent.Entities.Reservation;
import Components.PaymentComponent.Entities.Tariff;
import Components.ReservationComponent.Entities.Vehicle;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingSession {
    private Reservation reservation;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private Tariff tariff;
    private int hours;
    private int totalCost;

    private ParkingSession(ParkingSessionBuilder builder) {
        this.reservation = builder.reservation;
        this.vehicle = builder.vehicle;
        this.spot = builder.spot;
        this.tariff = builder.tariff;
        this.hours = builder.hours;
        this.totalCost = builder.totalCost;
    }

    @Override
    public String toString() {
        String result ="Parking Session:\n";
        result +="  Reservation id: "+reservation.getId()+"\n"
                +"  Vehicle: "+vehicle.getPlateNumber()+"\n"
                +"  Spot: "+spot.getSpot_number()+"\n"
                +"  Tariff: "+tariff.getTariff_name()+"\n";
        if(hours>=0){
            result +="  Status: Finalized\n"
                    +"  Hours: "+hours+"\n"
                    +"  Cost: "+totalCost;
        }
        else{
            result +="  Status: Not Finalized";
        }
        return result;
    }

    public static class ParkingSessionBuilder {
        private Reservation reservation;
        private Vehicle vehicle;
        private ParkingSpot spot;
        private Tariff tariff;
        private int hours;
        private int totalCost;

        public ParkingSessionBuilder(Reservation reservation, Vehicle vehicle, ParkingSpot spot, Tariff tariff) {
            this.reservation = reservation;
            this.vehicle = vehicle;
            this.spot = spot;
            this.tariff = tariff;
            hours=-1;
            totalCost=-1;
        }

        public ParkingSessionBuilder finalized() {
            if (reservation.getReserved_until() != null) {
                this.hours = calculateHours(reservation);
                this.totalCost = calculateCost(tariff, hours);
            } else {
                System.out.println("Parking Session is not finalized!\n");
                this.hours = -1;
                this.totalCost = -1;
            }
            return this;
        }

        private int calculateHours(Reservation reservation) {
            LocalDateTime from = reservation.getReserved_from().toLocalDateTime();
            LocalDateTime until = reservation.getReserved_until().toLocalDateTime();
            return (int) ChronoUnit.HOURS.between(from, until);
        }

        private int calculateCost(Tariff tariff, int hours) {
            return tariff.getBasePrice() + (tariff.getPricePerHour() * hours);
        }

        public ParkingSession build() {
            return new ParkingSession(this);
        }
    }
}