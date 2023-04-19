package org.booking.interfaces;

import org.booking.entity.Entity;

import java.util.List;

public interface IDao<T extends Entity> {
    //создание нового объекта
    void create(T object);

    //Получение объекта по идентификатору
    T getById(String id);

    //Получение всех объектов в базе данных
    List<T> getAll();

    //Обновление объектов
    void update(T object);

    //Удаление объектов
    void delete(T object);

    void save() throws RuntimeException;

    List<T> read() throws RuntimeException;

}
