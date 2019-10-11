package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();
    Car myCar;

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }

    public ParkingTicket addCar(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        cars.put(ticket,car);
        if(getAvailableParkingPosition() < 0){
            return ticket;
        }
        return null;
    }

    public Car getCar(ParkingTicket ticket) {
        myCar = cars.get(ticket);
        cars.remove(ticket);
        return myCar;
    }

    public Car checkCar(ParkingTicket ticket) {
        myCar = cars.get(ticket);
        return myCar;
    }

    public String IsValidTicket(ParkingTicket ticket) {
        if(checkCar(ticket) == null) {
            return null;
        }
        return "";
    }

    public String CheckWithoutPosition(ParkingTicket parkingTicket) {
        if(getAvailableParkingPosition() < 0){
            return "";
        }
        return "Not enough position.";
    }
}
