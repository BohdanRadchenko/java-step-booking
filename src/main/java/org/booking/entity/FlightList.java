package org.booking.entity;
import java.lang.System;

import org.booking.BookingManager;
import org.booking.helpers.FlightDateGenerator;

public class FlightList {
    static FlightDateGenerator generator = new FlightDateGenerator();
    static String departureTimeInMillis = generator.generateFlightDateAsString();
    static String arrivalTimeInMillis = generator.generateArrivalDateAsString(departureTimeInMillis, 8);

    public static void main(String[] args) {

        System.out.println("Случайная дата вылета: " + departureTimeInMillis);
        System.out.println("Случайная дата прилёта: " + arrivalTimeInMillis);

    }

}
