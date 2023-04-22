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
            timeList.add(date.plusMinutes(30).getMillis());
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
            int fromIdx = Randomize.num(0, Airport.values().length);
            int toIdx = Randomize.num(0, Airport.values().length, fromIdx);
            if (i % 2 == 0) {
                fromIdx = 0;
                toIdx = 6;
            } else {
                fromIdx = 6;
                toIdx = 2;
            }
            Airport from = Airport.values()[fromIdx];
            Airport to = Airport.values()[toIdx];
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

    public List<Flight> getFlightByTime(long start, long end, int limit) {
        refreshCount++;
        DateUtil s = DateUtil.of(start);
        DateUtil e = DateUtil.of(end);
        try {
            List<Flight> flights = service.getFlightByFromToTime(s.getMillis(), e.getMillis());
            if (flights.size() < limit && refreshCount <= maxRefreshCount) {
                return getFlightByTime(start, e.plusHours(24).getMillis(), limit);
            }
            return flights.stream().limit(limit).toList();
        } catch (RuntimeException ex) {
            return getFlightByTime(start, e.plusHours(24).getMillis(), limit);
        }
    }

    public List<Flight> getFlightNextDay() {
        DateUtil now = DateUtil.of();
        DateUtil next = DateUtil.of().plusDays(1);
        int limit = 100;
        List<Flight> flights = getFlightByTime(now.getMillis(), next.getMillis(), limit);
        List<Flight> sortedList = new ArrayList<>(flights);
        Collections.sort(sortedList);
        return sortedList;
    }

    public List<List<Flight>> getFlightsForBooking(Airport from, Airport to, long time, int seats) {
        try {
            List<List<Flight>> res = new ArrayList<>();
            service.getFlightsForBooking(from, to, time, seats)
                    .forEach(f -> {
                        ArrayList<Flight> fls = new ArrayList<>();
                        fls.add(f);
                        res.add(fls);
                    });
            if (res.size() == 0) {
                throw new RuntimeException("Nothing found!");
            }
            return res;
        } catch (RuntimeException ex) {
            return service.getFlightsForBookingWithTrans(from, to, time, seats);
        }
    }
}
