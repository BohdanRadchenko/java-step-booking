package org.booking.dao;

import org.booking.entity.Entity;
import org.booking.interfaces.IDao;

import java.util.*;

public abstract class Dao<T extends Entity> implements IDao<T> {
    private final Map<String, T> db = new HashMap<>();

    public boolean exist(String id) {
        return db.containsKey(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public T getById(String id) {
        return db.get(id);
    }

    @Override
    public boolean add(T entity) {
        if (exist(entity.getId())) return false;
        db.put(entity.getId(), entity);
        return true;
    }

    @Override
    public boolean update(T entity) {
        if (!exist(entity.getId())) return false;
        db.put(entity.getId(), entity);
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (!exist(id)) return false;
        db.remove(id);
        return true;
    }

    @Override
    public int size() {
        return db.size();
    }
}
