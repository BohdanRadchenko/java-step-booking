package org.booking.interfaces;

import org.booking.entity.Entity;

import java.util.List;

public interface IDao<T extends Entity> {
    /**
     * Method for add entity to map.
     *
     * @param entity instance of entity
     */
    boolean add(T entity);

    /**
     * Method for get element from map by id
     *
     * @param id unique id in string format
     * @return instance of entity
     */
    T getById(String id);

    /**
     * Method for get all
     *
     * @return List of entity
     */
    List<T> getAll();

    /**
     * Method for update entity in map
     *
     * @param entity instance of entity
     */
    boolean update(T entity);

    /**
     * Method for delete instance from map
     *
     * @param id unique id in string format
     */
    boolean delete(String id);

    /**
     * @return int db size
     */
    int size();

    /**
     * Method to add all data
     *
     * @param entities List of t
     */
    void upload(List<T> entities);
}
