package org.booking.controllers;

import org.booking.entity.*;
import org.booking.enums.FilePath;
import org.booking.interfaces.IController;
import org.booking.services.ServiceFlight;
import org.booking.utils.DateUtil;
import org.booking.utils.FileWorker;
import org.booking.utils.Logger;
import org.booking.utils.Randomize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightController implements IController {

    private final int maxRefreshCount = 3;
    private int refreshCount = 0;
    private final ServiceFlight service = new ServiceFlight();

    private List<Long> generateTime(int count) {
        DateUtil date = DateUtil.of().round();
        List<Long> timeList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            timeList.add(date.plusMinutes(15).getMillis());
        }
        return timeList;
    }

    private List<Flight> generateFlights(int count) {
        // TODO: 21.04.2023 MVP2
        // TODO: 21.04.2023 create airport from generator
        // TODO: 21.04.2023 create airport to generator
        // TODO: 21.04.2023 create flight id generator
        // TODO: 21.04.2023 create generator with MVP2 task

        List<Flight> flights = new ArrayList<>();

        List<Long> times = generateTime(count);
        for (int i = 0; i < count; i++) {
            int id = Randomize.num(999);
            Aircraft aircraft = Aircraft.values()[Randomize.num(Aircraft.values().length)];
            Airline airline = Airline.values()[Randomize.num(Airline.values().length)];
            int fromIdx = 0;
            Airport from = Airport.values()[fromIdx];
            Airport to = Airport.values()[Randomize.num(0, Airport.values().length, fromIdx)];
            flights.add(new Flight(times.get(i), from, to, airline, aircraft, id));
        }
        return flights;
    }

    @Override
    public int load() throws RuntimeException {
        if (!FileWorker.exist(FilePath.FLIGHT)) {
            service.upload(generateFlights(100));
            return service.size();
        }
        // TODO: 20.04.2023 load data from file. загрузка данных с файла
        try {
            List<Flight> flights = FileWorker.readBinary(FilePath.FLIGHT);
            service.upload(flights);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int save() {
        // TODO: 20.04.2023 save data
        try {
            FileWorker.writeBinary(FilePath.FLIGHT, service.getAll());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public Flight getByFlightId(String flightId) {
        try {
            return service.getByFlightId(flightId);
        } catch (RuntimeException ex) {
            Logger.error(ex.getMessage());
            return null;
        }
    }

    public List<Flight> getFlightNextDay() {
        List<Flight> flights = new ArrayList<>();
        refreshCount++;
        try {
            flights = service.getFlightNextHour(24 * refreshCount);
        } catch (RuntimeException ex) {
            if (refreshCount < maxRefreshCount) {
                return getFlightNextDay();
            }
            refreshCount = 0;
            // TODO: 22.04.2023 insert logger nothing to next 24 * REFRESH_COUNT hours
        }
        List<Flight> mustFlights = new ArrayList<>();
        if (flights.size() < 100) {
            try {
                mustFlights.addAll(service.getFlightNextHour(24 * refreshCount + 24));
            } catch (RuntimeException ignored) {
                Logger.error("Must upload exception");
            }
        }
        List<Flight> sortedList = new ArrayList<>(flights);
        Collections.sort(sortedList);
        Collections.sort(mustFlights);
        sortedList.addAll(mustFlights);
        return sortedList.stream().limit(Math.max(flights.size(), 100)).toList();
    }

    public List<Flight> getFlightsForBooking(Airport from, Airport to, long time, int seats) {
        try {
            return service.getFlightsForBooking(from, to, time, seats);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean addPassenger(Flight flight, User passenger) {
        flight.addPassenger(passenger);
        return service.update(flight);
    }
}
