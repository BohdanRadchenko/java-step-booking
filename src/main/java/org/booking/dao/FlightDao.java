package org.booking.dao;


import org.booking.entity.Flight;

import java.util.List;

public interface FlightDao extends Dao<Flight> {
    //Получение информации о рейсе
    void getScoreboardFlight();

    //Получение информационного табло для бронирования
    void getScoreboard();

    //Получение свободных рейсов
    List<Flight> getAllFlight();

    //Получение рейса по ID
    void getFlightById(int idFlight);

    //Загрузка рейсов с базы данных
    void loadData();

    //Запись файлов в базе данных
    void writeFile();

}
