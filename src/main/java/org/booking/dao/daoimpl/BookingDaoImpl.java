package org.booking.dao.daoimpl;

import org.booking.dao.BookingDao;
import org.booking.entity.Flight;
import org.booking.entity.FlightList;

import java.util.Map;

public class BookingDaoImpl  implements BookingDao {

    FlightList flightList = new FlightList();


    //Получение списка рейсов, доступных для бронирования
    Map<Integer, Flight> getFlyList = flightList.getFlightList();

    @Override
    public void bookingFlight(int id, String location, String date, int tickets) {

    }
}
