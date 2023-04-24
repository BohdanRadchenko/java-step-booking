package org.booking.dao;

import org.booking.entity.Entity;
import org.booking.interfaces.IDao;
import org.booking.utils.Logger;

import java.util.*;

public abstract class Dao<T extends Entity> implements IDao<T> {
    private final Map<String, T> db = new HashMap<>();

    public boolean exist(String id) {
        return db.containsKey(id);
    }

    @Override
    public List<T> getAll() {
        Logger.data("Entity: GET_ALL. OK");
        return new ArrayList<>(db.values());
    }

    @Override
    public T getById(String id) {
        T entity = db.get(id);
        if (entity == null) {
            Logger.data(String.format("GET_BY_ID '%s'. Nothing found!", id));
        } else {
            Logger.data(String.format("%s: GET_BY_ID '%s'. OK", entity.getClass().getSimpleName(), id));
        }
        return entity;
    }

    @Override
    public boolean add(T entity) {
        String className = entity.getClass().getSimpleName();
        if (exist(entity.getId())) {
            Logger.data(String.format("%s: ADD. ID:%s already exist", className, entity.getId()));
            return false;
        }
        db.put(entity.getId(), entity);
        Logger.data(String.format("%s: ADD. ID:%s. OK", className, entity.getId()));
        return true;
    }

    @Override
    public boolean update(T entity) {
        String className = entity.getClass().getSimpleName();
        if (!exist(entity.getId())) {
            Logger.data(String.format("%s: UPDATE. ID:%s already exist", className, entity.getId()));
            return false;
        }
        db.put(entity.getId(), entity);
        Logger.data(String.format("%s: UPDATE. ID:%s. OK", className, entity.getId()));
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (!exist(id)) {
            Logger.data(String.format("DELETE. ID:%s. Nothing found!", id));
            return false;
        }
        db.remove(id);
        Logger.data(String.format("DELETE. ID:%s. OK", id));
        return true;
    }

    @Override
    public int size() {
        return db.size();
    }

    @Override
    public void upload(List<T> entities) {
        if (entities.size() == 0) {
            Logger.data("UPLOAD. Nothing to upload!");
            return;
        }
        String className = entities.get(0).getClass().getSimpleName();
        Logger.data(String.format("%s: UPLOAD. OK!", className));
        entities.forEach(this::add);
    }
}
