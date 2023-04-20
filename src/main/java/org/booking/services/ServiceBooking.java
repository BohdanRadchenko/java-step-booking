package org.booking.services;

import org.booking.dao.BookingDao;
import org.booking.entity.Booking;
import org.booking.interfaces.IServices;

import java.util.List;

public class ServiceBooking implements IServices<Booking> {
    private final BookingDao db = new BookingDao();

    @Override
    public void upload(List<Booking> lists) {
        lists.forEach(db::add);
    }

    @Override
    public List<Booking> getAll() {
        return db.getAll();
    }

    @Override
    public Booking get(String id) throws RuntimeException {
        if (id.length() == 0) {
            throw new RuntimeException("Invalid id");
        }
        Booking f = db.getById(id);
        if (f == null) {
            throw new RuntimeException("No data in db");
        }
        return f;
    }

    @Override
    public void add(Booking entity) throws RuntimeException {
        throw new RuntimeException("Create method");
    }

    @Override
    public void delete(String id) throws RuntimeException {
        throw new RuntimeException("Create method");
    }
}
