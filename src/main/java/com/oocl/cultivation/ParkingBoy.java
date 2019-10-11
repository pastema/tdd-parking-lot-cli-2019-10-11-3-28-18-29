package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return parkingLot.addCar(car);
    }

    public Car fetch(ParkingTicket ticket) {
        return parkingLot.getCar(ticket);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public String IsValidTicket(ParkingTicket parkingTicket, ParkingLot parkingLot) {
        if(parkingTicket == null){
            return lastErrorMessage = "Please provide your parking ticket.";
        }
        if(parkingLot.IsValidTicket(parkingTicket) == null) {
            return lastErrorMessage = "Unrecognized parking ticket.";
        }
        return "";
    }
}
