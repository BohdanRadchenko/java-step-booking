package org.booking;


import org.booking.entity.Flight;


import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {


        Flight fl  = new Flight();
       // Flight Flight = new Flight(fl.flightDate(), fl.getRandomAirport(),fl.getRandomArrivalAirport(fl.getRandomAirport()), fl.getAirline(), fl.getRandomCodeWithRandomNumber());
        for (int i = 0; i < 1000; i++) {
            Flight Flight1 = new Flight(fl.flightDate(), fl.getRandomAirport(),fl.getRandomArrivalAirport(fl.getRandomAirport()), fl.getAirline(), fl.getRandomCodeWithRandomNumber());
            System.out.println(Flight1);
        }


       BookingManager bookingManager = new BookingManager();
        bookingManager.run();



}
}