package org.booking.dao.daoimpl;

import org.booking.dao.FlightDao;
import org.booking.entity.Flight;
import org.booking.entity.FlightList;

import java.util.List;

public class FlightDaoImpl implements FlightDao{
    FlightList flightList = new FlightList();
    List<Flight> getflightList = flightList.getAllFlight();




    @Override
    public void create(Flight object) {

    }

    @Override
    public void getById(int id) {

    }

    @Override
    public List<Flight> getAll() {
        return null;
    }

    @Override
    public void update(Flight object) {

    }

    @Override
    public void delete(Flight object) {

    }

    @Override
    public void close() {

    }

    @Override
    public void getScoreboardFlight() {

    }

    @Override
    public void getScoreboard() {

    }

    @Override
    public List<Flight> getAllFlight() {
        return null;
    }

    @Override
    public void getFlightById(int idFlight) {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void writeFile() {

    }
}
