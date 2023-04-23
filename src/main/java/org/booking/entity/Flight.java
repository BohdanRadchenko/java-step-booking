package org.booking.entity;

import org.booking.helpers.Utm;
import org.booking.utils.DateUtil;
import org.booking.utils.StringWorker;

import java.util.*;

public class Flight extends Entity implements Comparable<Flight> {
    public long departureTimeStamp;
    public long arrivalTimeStamp;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private Aircraft aircraft;
    private String flightId;
    private Set<String> passengers;
    private int freeSeats;
    private final String code;


    public Flight(long departureTimeStamp, Airport departureAirport, Airport arrivalAirport, Airline airline,
                  Aircraft aircraft, int flightId, Set<String> reserved) {
        this.departureTimeStamp = departureTimeStamp;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airline = airline;
        this.flightId = String.format("%03d", flightId);
        this.passengers = reserved;
        this.aircraft = aircraft;
        this.freeSeats = aircraft.seats - reserved.size();
        this.arrivalTimeStamp = arrivalTimeMls();
        this.code = String.format("%s%s", this.airline.code, this.flightId);
    }

    public Flight(long departureTimeStamp, Airport departureAirport, Airport arrivalAirport,
                  Airline airline, Aircraft aircraft, int flightId) {
        this(departureTimeStamp, departureAirport, arrivalAirport, airline, aircraft, flightId, new HashSet<>());
    }

    private long arrivalTimeMls() {
        int distance = Utm.distance(departureAirport, arrivalAirport);
        int cruiserTime = distance / aircraft.speed * 60 * 60 * 100;
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

    public String prettyFormat() {
        String depTime = DateUtil.of(departureTimeStamp).formatter("yyyy-MM-dd HH:mm");
        String from = String.format("%s", StringWorker.toUpperCase(departureAirport.city));
        String to = String.format("%s", StringWorker.toUpperCase(arrivalAirport.city));
        String aLine = String.format("%s", StringWorker.toUpperCase(airline.legalName));
        return String.format("%s | %s | %-12s ---> %12s | %s\n", code, depTime, from, to, aLine);
    }

    @Override
    public int compareTo(Flight that) {
        if (this.departureTimeStamp < that.departureTimeStamp) return -1;
        if (this.departureTimeStamp > that.departureTimeStamp) return +1;
        return 0;
    }

    @Override
    public String toString() {
        String format = "| %1s | %-10s | %-7s | %-18s | %-31s | %-8s | %-10s |\n";
        String header = String.format(format, "№", "Date", "Time", "Arrival", "Airlines", "Flight", "Free Seats");
        String rowSeparator = "----------------------------------------------------------------------------------------------------------%n";
        String date = DateUtil.of(departureTimeStamp).formatter("yyyy-MM-dd");
        String time = DateUtil.of(departureTimeStamp).formatter("HH:mm");
        String to = String.format("%s", StringWorker.toUpperCase(arrivalAirport.city));
        String row = String.format(format, "1", date, time, to, airline.legalName, code, freeSeats);

        String table = header + rowSeparator + row + rowSeparator;

        return String.format(table);
    }
}

