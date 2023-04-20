package org.booking.controllers;

import org.booking.interfaces.IController;
import org.booking.services.ServiceFlight;

public class FlightController implements IController {
    private final ServiceFlight service = new ServiceFlight();

    @Override
    public void load() throws RuntimeException {
        // TODO: 20.04.2023 load data from file. If file is not exist - generate data
    }

    @Override
    public void save() {
        // TODO: 20.04.2023 save data
    }
}
