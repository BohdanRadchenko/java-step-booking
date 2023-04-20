package org.booking.entity;

import org.booking.dao.Dao;
import org.booking.dao.FlightDao;
import org.booking.enums.Aircraft;
import org.booking.enums.Airline;
import org.booking.enums.Airport;
import org.booking.helpers.Utm;
import org.booking.utils.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight extends Entity {
    public long date; //Дата вылета
    private Airport from; //Город вылета
    private Airport to; //Город прилёта
    private Airline airline; // авиалинии
    private Aircraft aircraft; // авиалинии
    public String flight; //номер рейса
    public List<String> reserved = new ArrayList<>();
//    Map<Integer, String> String = place passenger id

    public Flight(long date, Airport from, Airport to, Airline airline, Aircraft aircraft, String fId) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.airline = airline;
        this.aircraft = aircraft;
        this.flight = String.format("%s%s", airline.getCode(), fId);
    }

    public int time() {
//        DateUtil.of(date).formatter("yyyy-MM-dd HH:mm:ss");
        return Utm.distance(from, to) * 200 * aircraft.coefficient;
    }

    public void insertPassenger() {
        reserved.add("1");
        reserved.add("2");
        reserved.add("3");
    }

    public int maxSeat() {
        return aircraft.passenger;
    }

//    public long arrTime() {
//        return flightDate + (time() * 10);
//    }

    public int getFreeSeat() {
        reserved.add("1");
        reserved.add("2");
        reserved.add("3");
        return maxSeat() - reserved.size();
    }

    @Override
    public String toString() {
        return String.format("%s", flight);
    }
}
