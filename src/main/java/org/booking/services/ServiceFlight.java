package org.booking.services;

import org.booking.dao.FlightDao;
import org.booking.entity.Flight;
import org.booking.interfaces.IServices;
import org.booking.utils.Console;
import org.booking.utils.DateUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ServiceFlight implements IServices<Flight> {
    private final FlightDao db = new FlightDao();

    @Override
    public int size() {
        return db.size();
    }

    @Override
    public void upload(List<Flight> flights) {
        if (flights.size() == 0) return;
        db.upload(flights);
    }

    @Override
    public List<Flight> getAll() {
        return db.getAll();
    }

    @Override
    public Flight getById(String id) throws RuntimeException {
        if (id.length() == 0) {
            throw new RuntimeException("Invalid id");
        }
        Flight f = db.getById(id);
        if (f == null) {
            throw new RuntimeException("No data in db");
        }
        return f;
    }

    @Override
    public void add(Flight entity) throws RuntimeException {
        throw new RuntimeException("Create method");
    }

    @Override
    public void delete(String id) throws RuntimeException {
        throw new RuntimeException("Create method");
    }

    public Flight getByFlightId(String flightId) throws RuntimeException {
        Optional<Flight> flight = db.getAll()
                .stream()
                .filter(f -> f.getCode().equals(flightId))
                .findAny();

        if (flight.isEmpty()) {
            throw new RuntimeException("Nothing found!");
        }
        return flight.get();
    }

    public List<Flight> getFlightNextHour(int hour) throws RuntimeException {
        long time = DateUtil
                .of()
                .round((3600000))
                .plusHours(hour)
                .getMillis();

        List<Flight> list = getAll()
                .stream()
                .filter(f -> f.getDepartureTimeStamp() <= time)
                .toList();
        if (list.size() == 0) {
            throw new RuntimeException("Nothing to found");
        }
        return list;
    }
}
