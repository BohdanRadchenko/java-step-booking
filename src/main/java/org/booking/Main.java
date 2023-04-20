package org.booking;


import org.booking.entity.Flight;
import org.booking.entity.FlightList;
import org.booking.enums.Aircraft;
import org.booking.enums.Airline;
import org.booking.enums.Airport;
import org.booking.helpers.FlightDateGenerator;

import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        BookingManager bookingManager = new BookingManager();
        bookingManager.run();

//        Airport from = random Airport.FRA
//        Flight flight = new Flight(1L, Airport.KBP, Airport.MAD, Airline.QATAR_AIRWAYS, Aircraft.PC24);
//        System.out.println(flight.flight);
//        System.out.println(flight.flightDate);
//        System.out.println(flight.arrTime());
//        System.out.println(flight.maxSeat());
//        System.out.println(flight.getFreeSeat());
    }
}