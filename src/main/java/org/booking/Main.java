package org.booking;

import org.booking.utils.Logger;

public class Main {

    public static void main(String[] args) {
        Logger.start();
        BookingManager bookingManager = new BookingManager();
        bookingManager.run();
        Logger.exit();
    }
}