package org.booking.entity;

import org.booking.helpers.Utm;

import java.util.*;

public class Flight extends Entity {
    public long departureTimeStamp;
    public long arrivalTimeStamp;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private Aircraft aircraft;
    private String flightId;
    private List<String> reserved;
    private int freeSeats;


    public Flight(long departureTimeStamp, Airport departureAirport, Airport arrivalAirport, Airline airline,
                  Aircraft aircraft, int flightId, List<String> reserved) {
        this.departureTimeStamp = departureTimeStamp;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airline = airline;
        this.flightId = String.format("%03d", flightId);
        this.reserved = reserved;
        this.aircraft = aircraft;
        this.freeSeats = aircraft.seats - reserved.size();
        this.arrivalTimeStamp = arrivalTimeMls();
    }

    public Flight(long departureTimeStamp, Airport departureAirport, Airport arrivalAirport,
                  Airline airline, Aircraft aircraft, int flightId) {
        this(departureTimeStamp, departureAirport, arrivalAirport, airline, aircraft, flightId, new ArrayList<>());
    }

    private long arrivalTimeMls() {
        int distance = Utm.distance(departureAirport, arrivalAirport);
        int cruiserTime = distance / aircraft.speed * 60 * 60 * 100;
        return this.departureTimeStamp + cruiserTime + 3600000;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    //test
    public long getDepartureTimeStamp() {
        return this.departureTimeStamp;
    }

    public long getArrivalTimeStamp() {
        return this.arrivalTimeStamp;
    }

    @Override
    public String toString() {
        return String.format("Flight{departureTimeStamp=%d, arrivalTimeStamp=%d, departureAirport=%s, arrivalAirport=%s}",
                departureTimeStamp, arrivalTimeStamp, departureAirport, arrivalAirport);
    }
}
