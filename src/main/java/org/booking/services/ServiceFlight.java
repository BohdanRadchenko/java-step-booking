package org.booking.services;

import org.booking.dao.FlightDao;
import org.booking.entity.Flight;
import org.booking.interfaces.IServices;

import java.util.List;

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
}
