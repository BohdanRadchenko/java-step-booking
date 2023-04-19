package org.booking.dao.daoimpl;

import org.booking.dao.BookingDao;
import org.booking.dao.DaoFactory;
import org.booking.dao.FlightDao;


//Фабрика объектов DAO
public class DaoFactoryimpl extends DaoFactory {


    //Возвращает объект типа FlightDaoImpl, который реализует интерфейс FlightDao
    public static FlightDao createFlightDao() {
        return new FlightDaoImpl() {
        };
    }

    //Возвращает объект типа BookingDaoImpl, который реализует интерфейс BookingDao
    public static BookingDao createBookingDao() {
        return new BookingDaoImpl() {
        };
    }

}
