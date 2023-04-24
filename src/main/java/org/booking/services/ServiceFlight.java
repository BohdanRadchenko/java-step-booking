package org.booking.services;

import org.booking.dao.FlightDao;
import org.booking.entity.Airport;
import org.booking.entity.Flight;
import org.booking.interfaces.IServices;
import org.booking.utils.DateUtil;
import org.booking.utils.StringWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceFlight implements IServices<Flight> {
    private final FlightDao db = new FlightDao();

    private final int NEXT_TIME_HOUR = 24;
    private final int MAX_TRANFER_DIFF = 12;
    private final int MAX_TRANSFER_DIFF_DAY = 12;

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

    public boolean update(Flight flight) {
        return db.update(flight);
    }

    @Override
    public Flight add(Flight entity) throws RuntimeException {
        throw new RuntimeException("Create method");
    }

    @Override
    public void delete(String id) throws RuntimeException {
        throw new RuntimeException("Create method");
    }

    public Flight getByFlightId(String flightId) throws RuntimeException {
        Optional<Flight> flight = db.getAll()
                .stream()
                .filter(f -> StringWorker.toLowerCase(f.getCode()).equals(StringWorker.toLowerCase(flightId)))
                .findAny();

        if (flight.isEmpty()) {
            throw new RuntimeException("Nothing found!");
        }
        return flight.get();
    }

    public List<Flight> getFlightsByTime(long start, long end) throws RuntimeException {
        List<Flight> list = getAll()
                .stream()
                .filter(f -> f.getDepartureTimeStamp() >= start && f.getDepartureTimeStamp() <= end)
                .toList();
        if (list.size() == 0) {
            throw new RuntimeException("Nothing found!");
        }
        return list;
    }

    public List<Flight> getFlightsNextTime(long time) throws RuntimeException {
        long startTime = DateUtil.of(time).getMillis();
        long endTime = DateUtil.of(startTime).plusHours(NEXT_TIME_HOUR).getMillis();
        return getFlightsByTime(startTime, endTime);
    }

    public List<Flight> getFlightsForBooking(Airport from, Airport to, long time, int seats) throws RuntimeException {
        try {
            List<Flight> flightOnTime = getFlightsNextTime(time);
            return flightOnTime
                    .stream()
                    .filter(f -> f.getFrom() == from && f.getTo() == to && f.getFreeSeats() >= seats)
                    .toList();
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<List<Flight>> getFlightsForBookingWithTrans(Airport from, Airport to, long time, int seats) {
        long startTime = DateUtil.of(time).getMillis();
        long endTransTime = DateUtil.of(time).plusDays(MAX_TRANSFER_DIFF_DAY).getMillis();

        List<Flight> flightsFrom = getFlightsNextTime(startTime)
                .stream()
                .filter(f -> f.getFrom() == from && f.getFreeSeats() >= seats)
                .toList();

        List<Flight> flightsTo = getFlightsByTime(startTime, endTransTime)
                .stream()
                .filter(f -> f.getTo() == to && f.getFreeSeats() >= seats)
                .toList();

        List<List<Flight>> flights = new ArrayList<>();

        for (Flight ff : flightsFrom) {
            for (Flight ft : flightsTo) {
                if (ff.getTo() != ft.getFrom()) continue;
                long arrMaxTime = DateUtil.of(ff.getArrivalTimeStamp()).plusHours(MAX_TRANFER_DIFF).getMillis();
                if (ff.getArrivalTimeStamp() > ft.getDepartureTimeStamp()
                        || ft.getDepartureTimeStamp() > arrMaxTime) continue;
                long diffHours = DateUtil.betweenDuration(ff.getArrivalTimeStamp(), ft.getDepartureTimeStamp()).toHours();
                if (diffHours < 1) continue;
                ArrayList<Flight> res = new ArrayList<>();
                res.add(ff);
                res.add(ft);
                flights.add(res);
            }
        }

        return flights;
    }
}
