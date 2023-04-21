package org.booking.controllers;

import org.booking.entity.Aircraft;
import org.booking.entity.Airline;
import org.booking.entity.Airport;
import org.booking.entity.Flight;
import org.booking.enums.FilePath;
import org.booking.interfaces.IController;
import org.booking.services.ServiceFlight;
import org.booking.utils.FileWorker;
import org.booking.utils.Randomize;

import java.util.ArrayList;
import java.util.List;

public class FlightController implements IController {
    private final ServiceFlight service = new ServiceFlight();

    private long generateTime() {
        return System.currentTimeMillis();
    }

    private List<Flight> generateFlights(int count) {
        // TODO: 21.04.2023 MVP1
        // TODO: 21.04.2023 create time generator

        // TODO: 21.04.2023 MVP2
        // TODO: 21.04.2023 create airport from generator
        // TODO: 21.04.2023 create airport to generator
        // TODO: 21.04.2023 create flight id generator
        // TODO: 21.04.2023 create generator with MVP2 task

        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int id = Randomize.num(999);
            Aircraft aircraft = Aircraft.values()[Randomize.num(Aircraft.values().length)];
            Airline airline = Airline.values()[Randomize.num(Airline.values().length)];
            int fromIdx = 0;
            Airport from = Airport.values()[fromIdx];
            Airport to = Airport.values()[Randomize.num(0, Airport.values().length, fromIdx)];

            flights.add(new Flight(generateTime(), from, to, airline, aircraft, id));
        }
        return flights;
    }

    @Override
    public void load() throws RuntimeException {
        if (!FileWorker.exist(FilePath.FLIGHT)) {
            service.upload(generateFlights(100));
            return;
        }
        // TODO: 20.04.2023 load data from file.
        ArrayList<Flight> f = new ArrayList<>();
        service.upload(f);
    }

    @Override
    public void save() {
        // TODO: 20.04.2023 save data
    }
}
