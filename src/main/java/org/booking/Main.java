package org.booking;


import org.booking.entity.Flight;
import org.booking.entity.FlightList;
import org.booking.helpers.FlightDateGenerator;

import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        BookingManager bookingManager = new BookingManager();
        bookingManager.run();


}
}