package org.booking.dao;

import java.util.List;

public interface Dao<T> extends AutoCloseable {
    //создание нового объекта
    void create(T object);

    //Получение объекта по идентификатору
    void getById(int id);

    //Получение всех объектов в базе данных
    List<T> getAll();

    //Обновление объектов
    void update(T object);

    //Удаление объектов
    void delete(T object);

    //закрытие ресурса используемого для работы с базой данных
    void close();
}
