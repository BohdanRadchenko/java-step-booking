package org.booking.controllers;

import org.booking.interfaces.IController;
import org.booking.services.ServiceBooking;

public class BookingController implements IController {
    private final ServiceBooking service = new ServiceBooking();

    @Override
    public void load() throws RuntimeException {
        // TODO: 20.04.2023 load data from file
    }

    @Override
    public void save() {
        // TODO: 20.04.2023 save data to file

    }
}
