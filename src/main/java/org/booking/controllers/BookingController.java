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
    public void load() throws RuntimeException {
        // TODO: 21.04.2023 add logger
        FilePath path = FilePath.BOOKING;
        if (!FileWorker.exist(path)) return;
        try {
            List<Booking> entities = FileWorker.readBinary(path);
            service.upload(entities);
        } catch (IOException | ClassNotFoundException ex) {
            Console.error(ex.getMessage());
        }
    }

    @Override
    public void save() {
        // TODO: 21.04.2023 add logger
        if (service.size() == 0) return;
        try {
            FileWorker.writeBinary(FilePath.BOOKING, service.getAll());
        } catch (IOException ex) {
            Console.error(ex.getMessage());
        }
    }
}
