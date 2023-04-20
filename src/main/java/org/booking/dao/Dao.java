package org.booking.dao;

import org.booking.entity.Entity;
import org.booking.interfaces.IDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Dao<T extends Entity> implements IDao<T> {
    private final Map<String, T> db = new HashMap<>();


    @Override
    public List<T> getAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public T getById(String id) {
        return db.get(id);
    }

    @Override
    public void create(T object) {
        db.put(object.getId(), object);
    }

    @Override
    public void update(T object) {
        db.put(object.getId(), object);
    }

    @Override
    public void delete(T object) {
        db.remove(object.getId());
    }

    public abstract void save() throws RuntimeException;

    public abstract List<T> read() throws RuntimeException;
}
