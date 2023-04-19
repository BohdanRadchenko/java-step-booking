package org.booking.dao;

import org.booking.entity.Booking;
import org.booking.enums.FilePath;
import org.booking.libs.FileWorker;

import java.io.IOException;
import java.util.List;

public class BookingDao extends Dao<Booking> {
    @Override
    public void save() throws IOException {
        FileWorker.writeBinary(FilePath.BOOKING, getAll());
    }

    @Override
    public List<Booking> read() throws ClassNotFoundException, IOException {
        return FileWorker.readBinary(FilePath.BOOKING);
    }
}
