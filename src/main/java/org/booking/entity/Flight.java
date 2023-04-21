package org.booking.entity;

import org.booking.enums.Airline;
import org.booking.enums.Airport;
import org.booking.helpers.FlightDateTimeGenerator;

import java.util.*;

public class Flight extends Entity {
    public Long flightDate; //Дата вылета
    private Airport departureAirport; //Город вылета
    private Airport arrivalAirPortTo; //Город прилёта
    private Airline airline; // авиалинии
    private String flight; //номер рейса
    
    //добавить массив reserved мест(бронирование)
    Random random = new Random();

    // TODO: 20.04.2023 добавить aircraft в конструктор  и віевсти места вильни =взагали-рез'рведюсайз 
    public Flight(Long flightDate, Airport departureAirport, Airport arrivalAirPortTo, Airline airline, String flight) {
        this.flightDate = flightDate;
        this.departureAirport = departureAirport;
        this.arrivalAirPortTo = arrivalAirPortTo;
        this.airline = airline;
        this.flight = flight;
        
    }

    private Flight() {}

    // TODO: 20.04.2023 все генерация в контроллере 
    public long flightDate() {
        return FlightDateTimeGenerator.generateRandomFlightDateTimeInMillis();
    }

//    public String getRandomAirport() {
//        Airport[] airports = Airport.values();
//        int randomIndex = new Random().nextInt(airports.length);
//        return airports[randomIndex].city;
//    }
public Airport getRandomAirport() {
    Airport[] airports = Airport.values();
    Random random = new Random();
    Airport randomAirport = airports[random.nextInt(airports.length)];
    return randomAirport;
}


    public Airport getRandomArrivalAirport(Airport departureAirport) {
        Airport[] airports = Airport.values();
        int randomIndex;
        do {
            randomIndex = new Random().nextInt(airports.length);
        } while (airports[randomIndex].equals(departureAirport));
        return airports[randomIndex];
    }
    public Airline getAirline() {
        Airline[] airlines = Airline.values();
        Random random = new Random();
        return airlines[random.nextInt(airlines.length - 1)];
    }

    public String getRandomCodeWithRandomNumber() {
        Airline[] airlines = Airline.values();
        Airline randomAirline = airlines[new Random().nextInt(airlines.length)];
        String code = randomAirline.getCode();
        String randomNumber = String.format("%03d", new Random().nextInt(1000));
        return code + randomNumber;
    }
    public static Date getDateFromMillis(long millis) {
        // Convert milliseconds to a Date object
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


    @Override
    public String toString() {
        return String.format("Flight{flightDate=%s, departureAirport=%s, arrivalAirPortTo=%s, airline=%s, flight='%s'}",
                FlightDateTimeGenerator.getDateFromMillis(flightDate), departureAirport.name(), arrivalAirPortTo.name(), airline.getLegalName(), flight);
    }

}
