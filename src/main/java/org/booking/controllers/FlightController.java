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
import java.util.*;
import java.util.stream.IntStream;

public class FlightController implements IController {
    public static final int FLIGHT_RANDOM_COUNT = 4321;
    private final int MAX_REFRESH_COUNT = 3;
    private int REFRESH_COUNT = 0;
    private final int MAX_NEXT_LIMIT = 100;
    private final int PLUS_NEXT_HOURS = 24;
    private final int GENERATE_ROUND_TIME = 10;
    private final int FLIGHT_MAX_ID = 999;
    private final int MIN_PASSENGERS_GENERATE = 6;
    private final double MAX_PASSENGERS_GENERATE_COF = 0.9;
    private final ServiceFlight service = new ServiceFlight();

    private List<Long> generateTime(int count) {
        DateUtil date = DateUtil.of().round();
        List<Long> timeList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            timeList.add(date.plusMinutes(GENERATE_ROUND_TIME).getMillis());
        }
        return timeList;
    }

    private Set<User> generatePassenger(Aircraft aircraft) {
        Set<User> users = new HashSet<>();
        IntStream
                .range(0, Randomize.num(MIN_PASSENGERS_GENERATE, (int) (aircraft.seats * MAX_PASSENGERS_GENERATE_COF)))
                .forEach(c -> {
                    String fn = String.valueOf(Randomize.num(Integer.MAX_VALUE - 1));
                    String ln = String.valueOf(Randomize.num(Integer.MAX_VALUE - 1));
                    users.add(new User(fn, ln));
                });
        return users;
    }

    private List<Flight> generateFlights(int count) {
        final int numFrom1 = 15;
        final int numTo1 = 20;
        final int numFrom2 = 25;
        final int numTo2 = 30;
        final int numFrom3 = 35;
        final int numTo3 = 40;

        List<Flight> flights = new ArrayList<>();
        List<Long> times = generateTime(count);

        for (int i = 0; i < count; i++) {
            int id = Randomize.num(FLIGHT_MAX_ID);
            Aircraft aircraft = Aircraft.values()[Randomize.num(Aircraft.values().length)];
            Airline airline = Airline.values()[Randomize.num(Airline.values().length)];
            int fromIdx = Randomize.num(0, Airport.values().length);
            int toIdx = Randomize.num(0, Airport.values().length, fromIdx);

            if (i % numFrom1 == 0) {
                fromIdx = Airport.KBP.ordinal();
                toIdx = Airport.FRA.ordinal();
            }
            if (i % numTo1 == 0) {
                fromIdx = Airport.FRA.ordinal();
                toIdx = Airport.LHR.ordinal();
            }

            if (i % numFrom2 == 0) {
                fromIdx = Airport.KBP.ordinal();
                toIdx = Airport.BUD.ordinal();
            }
            if (i % numTo2 == 0) {
                fromIdx = Airport.BUD.ordinal();
                toIdx = Airport.LHR.ordinal();
            }

            if (i % numFrom3 == 0) {
                fromIdx = Airport.KBP.ordinal();
                toIdx = Airport.VIE.ordinal();
            }
            if (i % numTo3 == 0) {
                fromIdx = Airport.VIE.ordinal();
                toIdx = Airport.LHR.ordinal();
            }

            Airport from = Airport.values()[fromIdx];
            Airport to = Airport.values()[toIdx];
            flights.add(new Flight(times.get(i), from, to, airline, aircraft, id, generatePassenger(aircraft)));
        }
        return flights;
    }

    @Override
    public int load() throws RuntimeException {
        if (!FileWorker.exist(FilePath.FLIGHT)) {
            service.upload(generateFlights(FLIGHT_RANDOM_COUNT));
            return service.size();
        }
        try {
            List<Flight> flights = FileWorker.readBinary(FilePath.FLIGHT);
            service.upload(flights);
            if (flights.size() < FLIGHT_RANDOM_COUNT) {
                service.upload(generateFlights(FLIGHT_RANDOM_COUNT - flights.size()));
            }
            return service.size();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        REFRESH_COUNT++;
        DateUtil s = DateUtil.of(start);
        DateUtil e = DateUtil.of(end);
        try {
            List<Flight> flights = service.getFlightsByTime(s.getMillis(), e.getMillis());
            if (flights.size() < limit && REFRESH_COUNT <= MAX_REFRESH_COUNT) {
                return getFlightByTime(start, e.plusHours(PLUS_NEXT_HOURS).getMillis(), limit);
            }
            return flights.stream().limit(limit).toList();
        } catch (RuntimeException ex) {
            return getFlightByTime(start, e.plusHours(PLUS_NEXT_HOURS).getMillis(), limit);
        }
    }

    public List<Flight> getFlightNextDay() {
        DateUtil now = DateUtil.of();
        DateUtil next = DateUtil.of().plusDays(1);
        List<Flight> flights = getFlightByTime(now.getMillis(), next.getMillis(), MAX_NEXT_LIMIT);
        List<Flight> sortedList = new ArrayList<>(flights);
        Collections.sort(sortedList);
        return sortedList;
    }

    public List<List<Flight>> getFlightsForBooking(Airport from, Airport to, long time, int seats) {
        final int minForTrans = 1;
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
            if (res.size() <= minForTrans) {
                try {
                    List<List<Flight>> flightsForBookingWithTrans = service.getFlightsForBookingWithTrans(from, to, time, seats);
                    res.addAll(flightsForBookingWithTrans);
                } catch (RuntimeException ex) {
                    throw new RuntimeException(ex);
                }
            }
            return res;
        } catch (RuntimeException ex) {
            Logger.error(ex.getMessage());
            try {
                return service.getFlightsForBookingWithTrans(from, to, time, seats);
            } catch (RuntimeException exception) {
                Logger.error(exception.getMessage());
                return new ArrayList<>();
            }
        }
    }
}
