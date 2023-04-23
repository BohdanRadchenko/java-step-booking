package org.booking.entity;

import org.booking.helpers.Utm;
import org.booking.utils.DateUtil;
import org.booking.utils.StringWorker;

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

    @Override
    public int compareTo(Flight that) {
        if (this.departureTimeStamp < that.departureTimeStamp) return -1;
        if (this.departureTimeStamp > that.departureTimeStamp) return +1;
        return 0;
    }

    @Override
    public String toString() {
        System.out.println();
        String format = "| %1s | %-10s | %-7s | %-18s | %-31s | %-8s | %-10s |\n";
        String header = String.format(format, "№", "Date", "Time", "Arrival", "Airlines", "Flight", "Free Seats");
        String rowSeparator = "----------------------------------------------------------------------------------------------------------%n";
        String date = DateUtil.of(departureTimeStamp).formatter("yyyy-MM-dd");
        String time = DateUtil.of(departureTimeStamp).formatter("HH:mm");
        String to = String.format("%s", StringWorker.toUpperCase(arrivalAirport.city));
        String row = String.format(format, "1", date, time, to, airline.legalName, code, freeSeats);

        String table = "Information about the selected flight\n" +rowSeparator +header + rowSeparator + row + rowSeparator;

        return String.format(table);
    }
}

