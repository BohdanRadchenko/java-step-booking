package org.booking;

import org.booking.entity.Flight;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        BookingManager bookingManager = new BookingManager();
       bookingManager.run();
      }
    }