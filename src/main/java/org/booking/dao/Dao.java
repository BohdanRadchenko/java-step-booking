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
        // TODO: 21.04.2023 insert Logger. Use getSimpleName() for get entity classname.
        return new ArrayList<>(db.values());
    }

    @Override
    public T getById(String id) {
        // TODO: 21.04.2023 insert Logger. Use getSimpleName() for get entity classname.
        return db.get(id);
    }

    @Override
    public boolean add(T entity) {
        if (exist(entity.getId())) return false;
        db.put(entity.getId(), entity);
        // TODO: 21.04.2023 insert Logger. Use getSimpleName() for get entity classname.
        return true;
    }

    @Override
    public boolean update(T entity) {
        if (!exist(entity.getId())) return false;
        db.put(entity.getId(), entity);
        // TODO: 21.04.2023 insert Logger. Use getSimpleName() for get entity classname.
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (!exist(id)) return false;
        db.remove(id);
        // TODO: 21.04.2023 insert Logger. Use getSimpleName() for get entity classname.
        return true;
    }

    @Override
    public int size() {
        return db.size();
    }

    @Override
    public void upload(List<T> entities) {
        // TODO: 21.04.2023 insert Logger. Use getSimpleName() for get entity classname.
        entities.forEach(this::add);
    }

}
