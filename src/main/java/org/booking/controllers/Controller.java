package org.booking.controllers;

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
            user.load();
            flight.load();
            booking.load();
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void save() {

    }
}
