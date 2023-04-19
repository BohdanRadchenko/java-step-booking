package org.booking.entity;

public class Flight extends AbstractEntity {
    private String terminal;
    private String date;
    private String localTime;
    private String destination;
    private String airline;
    private String flight;
    private int freeSeat;


    public Flight(long id) {
        super(id);
    }

    public Flight(long id, String terminal, String date, String localTime, String destination, String airline, String flight) {
        super(id);
        this.terminal = terminal;
        this.date = date;
        localTime = localTime;
        this.destination = destination;
        this.airline = airline;
        this.flight = flight;
    }

    public Flight(long id, String terminal, String date, String localTime, String destination, String airline, String flight, int freeSeat) {
        this(id, terminal, date, localTime, destination, airline, flight);
        this.freeSeat = freeSeat;
    }


    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        localTime = localTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

//    @Override
//    public String toString() {
//        return "Flight{" +
//                "terminal='" + terminal + '\'' +
//                ", date='" + date + '\'' +
//                ", LocalTime='" + localTime + '\'' +
//                ", destination='" + destination + '\'' +
//                ", airline='" + airline + '\'' +
//                ", flight='" + flight + '\'' +
//                ", freeSeat=" + freeSeat +
//                '}';
//    }

}
