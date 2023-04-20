package org.booking;

import org.booking.helpers.FlightDateGenerator;
import org.booking.entity.Flight;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        BookingManager bookingManager = new BookingManager();
        bookingManager.run();
        FlightDateGenerator generator = new FlightDateGenerator();
        LocalDateTime flightDate = generator.generateFlightDate();
        int hoursToAdd = 8; // добавление часов к дате вылета для получения времени перелёта
        LocalDateTime arrivalDate = generator.generateArrivalDate(flightDate, hoursToAdd);
        System.out.println("Дата вылета: " + flightDate);
        System.out.println("Дата прилета: " + arrivalDate);
        Flight flight = new Flight(flightDate, arrivalDate);
        System.out.println(flight.toString());

    }
}