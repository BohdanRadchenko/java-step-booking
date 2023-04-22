package org.booking.controllers;

import org.booking.entity.Booking;
import org.booking.enums.FilePath;
import org.booking.interfaces.IController;
import org.booking.services.ServiceBooking;
import org.booking.utils.Console;
import org.booking.utils.FileWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookingController implements IController {
    private final ServiceBooking service = new ServiceBooking();

    public boolean isEmpty() {
        return service.size() == 0;
    }

    public Booking getByCode(String code) {
        try {
            return service.getByCode(code);
        } catch (RuntimeException ex) {
            // TODO: 22.04.2023 Insert logger
            return null;
        }
    }

    public boolean cancelBooking(Booking booking) {
        try {
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
