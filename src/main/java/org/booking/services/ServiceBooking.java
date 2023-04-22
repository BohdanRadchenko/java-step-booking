package org.booking.services;

import org.booking.dao.BookingDao;
import org.booking.entity.Booking;
import org.booking.enums.Message;
import org.booking.interfaces.IServices;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class ServiceBooking implements IServices<Booking> {
    private final BookingDao db = new BookingDao();

    public Booking getByCode(String code) throws RuntimeException {
        Optional<Booking> booking = db
                .getAll()
                .stream()
                .filter(b -> b.getCode().equals(code))
                .findFirst();
        if (booking.isEmpty()) {
            throw new RuntimeException("Booking not found!");
        }
        return booking.get();
    }

    public List<Booking> getBookingsByPassengerId(String id) throws RuntimeException {
        List<Booking> bookings = db
                .getAll()
                .stream()
                .filter(b -> Objects.equals(b.getPassengerId(), id))
                .toList();
        if (bookings.size() == 0) {
            throw new NoSuchElementException();
        }
        return bookings;
    }

    public List<Booking> getBookingsByCreatorId(String id) throws RuntimeException {
        List<Booking> bookings = db
                .getAll()
                .stream()
                .filter(b -> Objects.equals(b.getCreatorId(), id))
                .toList();
        if (bookings.size() == 0) {
            throw new NoSuchElementException();
        }
        return bookings;
    }

    @Override
    public int size() {
        return db.size();
    }

    @Override
    public void upload(List<Booking> bookings) {
        if (bookings.size() == 0) return;
        db.upload(bookings);
    }

    @Override
    public List<Booking> getAll() {
        return db.getAll();
    }

    @Override
    public Booking getById(String id) throws RuntimeException {
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
    public Booking add(Booking booking) throws RuntimeException {
        if (!db.add(booking)) {
            throw new RuntimeException("Booking add exception");
        }
        return booking;
    }

    @Override
    public void delete(String id) throws RuntimeException {
        if (!db.delete(id)) {
            throw new RuntimeException(String.format("Error delete booking with ID:%s", id));
        }
    }
}
