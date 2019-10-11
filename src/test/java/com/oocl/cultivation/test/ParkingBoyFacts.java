package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    //------------------------------------------------------------------- STORY 1//
    @Test
    void should_get_parking_ticket_when_parking_boy_parks_car_to_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket ticket = parkingBoy.park(car);
        assertNotNull(ticket);
    }

    @Test
    void should_customer_give_ticket_to_parking_boy_to_fetch_car() {
        ParkingTicket parkingTicket;
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingTicket = parkingBoy.park(car);
        car = parkingBoy.fetch(parkingTicket);
        assertNotNull(car);
    }

    @Test
    void should_park_multiple_cars_to_parking_lot_not_equal() {
        ParkingTicket parkingTicket;
        Car originalCar = new Car();
        ParkingLot parkingLot = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingTicket = parkingBoy.park(originalCar);
        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        assertEquals(originalCar, fetchedCar);
    }

    @Test
    void should_customer_give_wrong_ticket_or_does_not_give_a_ticket_then_car_should_not_be_fetched() {
        ParkingTicket parkingTicket = new ParkingTicket();
        ParkingTicket parkingTicket2 = new ParkingTicket();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        parkingTicket = parkingBoy.park(car);
        car = parkingBoy.fetch(parkingTicket2);
        assertNull(car);
    }

    @Test
    void should_customer_give_ticket_that_already_exists_then_no_car_should_be_fetched() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Car car2 = new Car();

        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        car = parkingBoy.fetch(parkingTicket);
        car2 = parkingBoy.fetch(parkingTicket);

        assertNull(car2);
    }

    @Test
    void should_not_get_ticket_when_parking_lot_is_over_capacity() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        IntStream.rangeClosed(0,9).forEach(number ->{
            Car car = new Car();
            parkingBoy.park(car);
        });

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        assertNull(parkingTicket);
    }



    //------------------------------------------------------------------- STORY 2//



    @Test
    void should_send_message_when_customer_gives_wrong_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car car = new Car();
        ParkingTicket originalParkingTicket = parkingBoy.park(car);
        ParkingTicket fakeParkingTicket = parkingBoy.park(car);
        car = parkingBoy.fetch(fakeParkingTicket);
        String getParkingBoyMessage = parkingBoy.IsValidTicket(fakeParkingTicket,parkingLot);

        assertEquals(getParkingBoyMessage, "Unrecognized parking ticket.");
    }

    @Test
    void should_send_message_when_customer_gives_no_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket);
        String getParkingBoyMessage = parkingBoy.IsValidTicket(null,parkingLot);

        assertEquals(getParkingBoyMessage, "Please provide your parking ticket.");
    }

    @Test
    void should_parking_boy_attempt_to_park_a_car_without_a_position_then_print_error() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        IntStream.rangeClosed(0,9).forEach(number ->{
            Car car = new Car();
            parkingBoy.park(car);
        });

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingLot.CheckWithoutPosition(parkingTicket);

        assertEquals(parkingLot.CheckWithoutPosition(parkingTicket), "Not enough position.");
    }



    //------------------------------------------------------------------- STORY 3//



    @Test
    void should_parking_boy_will_park_cars_to_the_second_parking_lot_when_the_first_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        IntStream.rangeClosed(0,9).forEach(number ->{
            Car car = new Car();
            parkingBoy.park(car);
        });

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        assertNull(parkingTicket);
    }
}
