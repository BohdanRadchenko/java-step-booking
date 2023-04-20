package org.booking.interfaces;

import org.booking.entity.Entity;

import java.util.List;

public interface IServices<T extends Entity> {

    void upload(List<T> lists);

    List<T> getAll();

    T getById(String id) throws RuntimeException;

    void add(T entity) throws RuntimeException;

    void delete(String id) throws RuntimeException;

}
