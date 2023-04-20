package org.booking;

import org.booking.dao.FlightDao;
import org.booking.entity.Flight;
import org.booking.enums.Aircraft;
import org.booking.enums.Airline;
import org.booking.enums.Airport;
import org.booking.enums.FilePath;
import org.booking.ui.menu.MainMenu;
import org.booking.ui.menu.Menu;
import org.booking.utils.DateUtil;
import org.booking.utils.FileWorker;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    /**
     * read data from files , create init data if db is empty
     */
    private void init() {
        if (FileWorker.exist(FilePath.FLIGHT)) {
//            load from file
        } else {
            int n = 10;
//            in controller.flight.generate();
            List<Flight> flights = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Airport from = Airport.KBP;
                Airport to = Airport.KBP;
                Airline al = Airline.QATAR_AIRWAYS;
                Aircraft ac = Aircraft.PC24;

                Flight flight = new Flight(DateUtil.of(), from, to, al, ac);
                flights.add(flight);
//                in service
//                flight.create(flight);
            }

            for (Flight flight : flights) {
                System.out.println(flight);
            }
//            generate random flights from flight controller
//            controller.flight.generate(1000);
//            ->
//            Flight flight = new Flight(Airport.FRA, Airport.FRA, Airline.QATAR_AIRWAYS, Aircraft.A320);
//            ->
//            service.crate(flight)
//            ->
//            FlightDao.create()

        }
        // TODO: 17.04.2023 read data from files when file worker created and read
        // TODO: 17.04.2023 load data from files to controllers
    }

    /**
     * saves all data to files
     */
    private void save() {
        // TODO: 17.04.2023 save data from controller to files
    }

    private void start() {
        Menu menu = new MainMenu();
        while (!menu.isExit()) {
            menu.run();
        }
    }

    public void run() {
        init();
        start();
        save();
    }
}