package org.booking.entity;

import org.booking.dao.Dao;
import org.booking.dao.FlightDao;
import org.booking.enums.Airline;
import org.booking.enums.Airport;

import java.time.LocalDateTime;

public class Flight extends Entity {
    private Long flightDate; //Дата вылета
    private Long arrivalDate; //Дата прилёта
    private Airport arrivalAirPortFrom; //Город вылета
    private Airport arrivalAirPortTo; //Город прилёта
    private Airline airline; // авиалинии
    private String flight; //номер рейса
    private int freeSeat; //свободные места
    private String id; //id рейса

    public Flight(Long flightDate, Long arrivalDate, String terminalToArrival, String timeToFly, String airline, String flight, int freeSeat, String id) {
        this.flightDate = flightDate;
        this.arrivalDate = arrivalDate;
        this.terminalToArrival = terminalToArrival;
        this.timeToFly = timeToFly;
        this.airline = airline;
        this.flight = flight;
        this.freeSeat = freeSeat;
        this.id = id;
    }

    public Long getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Long flightDate) {
        this.flightDate = flightDate;
    }

    public Long getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Long arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getTerminalToArrival() {
        return terminalToArrival;
    }

    public void setTerminalToArrival(String terminalToArrival) {
        this.terminalToArrival = terminalToArrival;
    }

    public String getTimeToFly() {
        return timeToFly;
    }

    public void setTimeToFly(String timeToFly) {
        this.timeToFly = timeToFly;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public int getFreeSeat() {
        return freeSeat;
    }

    public void setFreeSeat(int freeSeat) {
        this.freeSeat = freeSeat;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = super.getId();
    }

    @Override
    public String toString() {
        return String.format("Flight{flightDate=%s, arrivalDate=%s, terminalToArrival='%s', timeToFly='%s', airline='%s', flight='%s', freeSeat=%d, id='%s'}",
                flightDate, arrivalDate, terminalToArrival, timeToFly, airline, flight, freeSeat, id);
    }
}
