package org.booking.controllers;

import org.booking.utils.Console;
import org.booking.utils.Logger;

public final class Controller {
    public final UserController user;
    public final FlightController flight;
    public final BookingController booking;

    public Controller() {
        this.user = new UserController();
        this.flight = new FlightController();
        this.booking = new BookingController();
    }

    public void load() throws RuntimeException {
        try {
            String msg = "Loading user data";
            Logger.info(msg);
            Console.hide(msg);
            Console.ln();
            int load = user.load();
            String loaded = String.format("Loaded %d users", load);
            Logger.info(loaded);
            Console.hide(loaded);
            Console.ln();
        } catch (RuntimeException ex) {
            String exceptionMessage = String.format("Load user data error. %s", ex.getMessage());
            Logger.error(exceptionMessage);
            Console.error(exceptionMessage);
        }

        try {
            String msg = "Loading flight data";
            Logger.info(msg);
            Console.hide(msg);
            Console.ln();
            int load = flight.load();
            String loaded = String.format("Loaded %d flights", load);
            Logger.info(loaded);
            Console.hide(loaded);
            Console.ln();
        } catch (RuntimeException ex) {
            String exceptionMessage = String.format("Load flight data error. %s", ex.getMessage());
            Logger.error(exceptionMessage);
            Console.error(exceptionMessage);
        }

        try {
            String msg = "Loading booking data";
            Logger.info(msg);
            Console.hide(msg);
            Console.ln();
            int load = booking.load();
            String loaded = String.format("Loaded %d bookings", load);
            Logger.info(loaded);
            Console.hide(loaded);
            Console.ln();
        } catch (RuntimeException ex) {
            String exceptionMessage = String.format("Load booking data error. %s", ex.getMessage());
            Logger.error(exceptionMessage);
            Console.error(exceptionMessage);
        }
    }

    public void save() {
        int usersSize = user.save();
        int bookingsSize = booking.save();
        int flightsSize = flight.save();
        Logger.info(String.format("Saving user data. %d", usersSize));
        Logger.info(String.format("Saving booking data. %d", bookingsSize));
        Logger.info(String.format("Saving flight data. %d", flightsSize));
    }
}
