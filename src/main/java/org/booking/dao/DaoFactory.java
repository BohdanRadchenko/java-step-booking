package org.booking.dao;

import org.booking.dao.daoimpl.DaoFactoryimpl;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public static FlightDao createFlightDao() {
        return null;
    }

    public static BookingDao createBookingDao() {
        return null;
    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new DaoFactoryimpl();
                }
            }
        }
        return daoFactory;
    }
}
