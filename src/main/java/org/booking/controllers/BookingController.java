package org.booking.controllers;

import org.booking.entity.Booking;
import org.booking.entity.Flight;
import org.booking.entity.User;
import org.booking.enums.FilePath;
import org.booking.interfaces.IController;
import org.booking.services.ServiceBooking;
import org.booking.utils.Console;
import org.booking.utils.FileWorker;
import org.booking.utils.Logger;

import java.io.IOException;
import java.util.*;

public class BookingController implements IController {
    private final ServiceBooking service = new ServiceBooking();

    public boolean isEmpty() {
        return service.size() == 0;
    }

    public Booking getByCode(String code) {
        try {
            return service.getByCode(code);
        } catch (RuntimeException ex) {
            Logger.error(ex.getMessage());
            return null;
        }
    }

    public boolean cancelBooking(Booking booking) {
        try {
            booking.cancel();
            service.delete(booking.getId());
            return true;
        } catch (RuntimeException exception) {
            // TODO: 22.04.2023 Insert logger
            return false;
        }
    }

    public List<Booking> getBookingsByPassengerId(String id) {
        try {
            return service.getBookingsByPassengerId(id);
        } catch (RuntimeException ex) {
            // TODO: 21.04.2023 insert logger
            return new ArrayList<>();
        }
    }

    public List<Booking> getBookingsByCreatorId(String id) {
        try {
            return service.getBookingsByCreatorId(id);
        } catch (RuntimeException ex) {
            // TODO: 21.04.2023 insert logger
            return new ArrayList<>();
        }
    }

    public List<Booking> getBookingsByUserId(String userId) {
        List<Booking> bookingListByPassenger = getBookingsByPassengerId(userId);
        List<Booking> bookingListByCreator = getBookingsByCreatorId(userId);
        if (bookingListByPassenger.size() == 0 && bookingListByCreator.size() == 0) {
            return new ArrayList<>();
        }
        // TODO: 22.04.2023 Example code for next time using
        // Booking[] spread = (Booking[]) Stream.of(bookingListByCreator, bookingListByPassenger)
        // .flatMap(Stream::of)
        // .toArray();
        // ArrayList<Booking> bookings = new ArrayList<>(Set.of(spread));
        List<Booking> res;
        if (bookingListByPassenger.size() != 0) {
            res = new ArrayList<>(bookingListByPassenger);
        } else {
            res = new ArrayList<>(bookingListByCreator);
        }
        Collections.sort(res);
        return res;
    }

    public Booking createBooking(Flight flight, User creator, User passenger) {
        return service.add(new Booking(flight, creator, passenger));
    }

    @Override
    public int load() throws RuntimeException {
        FilePath path = FilePath.BOOKING;
        if (!FileWorker.exist(path)) return 0;
        try {
            List<Booking> entities = FileWorker.readBinary(path);
            service.upload(entities);
            return service.size();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public int save() {
        if (service.size() == 0) return 0;
        try {
            FileWorker.writeBinary(FilePath.BOOKING, service.getAll());
            return service.size();
        } catch (IOException ex) {
            Console.error(ex.getMessage());
            return 0;
        }
    }
}
