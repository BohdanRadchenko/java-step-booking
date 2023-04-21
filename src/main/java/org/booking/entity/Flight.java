package org.booking.entity;

import org.booking.enums.Aircraft;
import org.booking.enums.Airline;
import org.booking.enums.Airport;
import org.booking.helpers.Utm;
import org.booking.utils.Console;

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



    // TODO: 20.04.2023 добавить aircraft в конструктор  и віевсти места вильни =взагали-рез'рведюсайз


    public Flight(long departureTimeStamp,
                  Airport departureAirport,
                  Airport arrivalAirport,
                  Airline airline, Aircraft aircraft, String flightId, List<String> reserved) {
        this.departureTimeStamp = departureTimeStamp;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airline = airline;
        this.flightId = flightId;
        this.reserved = reserved;
        this.aircraft = aircraft;
        this.freeSeats = aircraft.getSeats() - reserved.size();
        this.arrivalTimeStamp = arrivalTimeMls();
    }

    public Flight(long departureTimeStamp, Airport departureAirport, Airport arrivalAirport, Airline airline, Aircraft aircraft, String flightId) {
        this(departureTimeStamp, departureAirport, arrivalAirport, airline, aircraft, flightId, new ArrayList<>());
    }

    private long arrivalTimeMls() {
        int distance = Utm.distance(departureAirport, arrivalAirport);
        int speedFly = aircraft.getCoefficient() * 100;
        int result = distance/speedFly*60*60*100;
        return this.departureTimeStamp+result+3600000;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    //test
    public long getDepartureTimeStamp(){
        return this.departureTimeStamp;
    }
    public long getArrivalTimeStamp(){
        return this.arrivalTimeStamp;
    }
}
