package org.booking.entity;

import org.booking.helpers.PrettyFormat;
import org.booking.helpers.Utm;
import org.booking.utils.DateUtil;
import org.booking.utils.StringWorker;

import java.time.Duration;
import java.time.Period;
import java.util.*;

public class Flight extends Entity implements Comparable<Flight> {
    private long departureTimeStamp;
    private long arrivalTimeStamp;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private Aircraft aircraft;
    private String flightId;
    private Set<User> passengers = new HashSet<>();
    private int freeSeats;
    private final String code;

    public Flight(long departureTimeStamp, Airport departureAirport, Airport arrivalAirport, Airline airline,
                  Aircraft aircraft, int flightId, Set<User> passengers) {
        this.departureTimeStamp = departureTimeStamp;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airline = airline;
        this.flightId = String.format("%03d", flightId);
        if (passengers != null) {
            this.passengers = passengers;
        }
        this.aircraft = aircraft;
        this.freeSeats = aircraft.seats - this.passengers.size();
        this.arrivalTimeStamp = arrivalTimeMls();
        this.code = String.format("%s%s", this.airline.code, this.flightId);
    }

    public Flight(long departureTimeStamp, Airport departureAirport, Airport arrivalAirport,
                  Airline airline, Aircraft aircraft, int flightId) {
        this(departureTimeStamp, departureAirport, arrivalAirport, airline, aircraft, flightId, new HashSet<>());
    }

    public boolean addPassenger(User passenger) {
        return passengers.add(passenger);
    }

    private long arrivalTimeMls() {
        int distance = Utm.distance(departureAirport, arrivalAirport);
        long cruiserTime = (long) (distance / aircraft.speed) * 3600000;
        return this.departureTimeStamp + cruiserTime + 3600000;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public long getDepartureTimeStamp() {
        return this.departureTimeStamp;
    }

    public long getArrivalTimeStamp() {
        return this.arrivalTimeStamp;
    }

    public String getCode() {
        return code;
    }

    public Airport getFrom() {
        return departureAirport;
    }

    public Airport getTo() {
        return arrivalAirport;
    }

    public Airline getAirline() {
        return airline;
    }

    public String toString() {
        String departureTime = String.format("departureTimeStamp=%d", departureTimeStamp);
        String arrivalTime = String.format("arrivalTimeStamp=%d", arrivalTimeStamp);
        String depAirport = String.format("departureAirport=%s", departureAirport);
        String arrAirport = String.format("arrivalAirport=%s", arrivalAirport);
        String airLine = String.format("airline=%s", airline);
        String airCraft = String.format("aircraft=%s", aircraft);
        String fltId = String.format("flightId=%s", flightId);
        String res = String.format("reserved=%s", passengers);
        String freeSeat = String.format("freeSeats=%d", freeSeats);
        return String.format("Flight{%s,%s,%s,%s, %s,%s,%s,%s,%s}",
                departureTime, arrivalTime, depAirport, arrAirport, airLine, airCraft, fltId, res, freeSeat);
    }

    @Override
    public int compareTo(Flight that) {
        if (this.departureTimeStamp < that.departureTimeStamp) return -1;
        if (this.departureTimeStamp > that.departureTimeStamp) return +1;
        return 0;
    }
}

