package org.booking;

import org.booking.entity.Flight;
import org.booking.enums.Aircraft;
import org.booking.enums.Airline;
import org.booking.enums.Airport;
import org.booking.utils.DateUtil;

public class Main {
    public static void main(String[] args) {
//        BookingManager bookingManager = new BookingManager();
//        bookingManager.run();
        Flight fl = new Flight(System.currentTimeMillis(), Airport.BOS, Airport.FRA, Airline.QATAR_AIRWAYS, Aircraft.B777,"023");
        System.out.println(DateUtil.of(fl.getDepartureTimeStamp()).formatter("yyyy-MM-dd HH:mm:ss"));
        System.out.println( DateUtil.of(fl.getArrivalTimeStamp()).formatter("yyyy-MM-dd HH:mm:ss"));
    }
}