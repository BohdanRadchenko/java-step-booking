package org.booking.services;

import org.booking.dao.UserDao;
import org.booking.entity.User;
import org.booking.interfaces.IServices;

import java.util.List;

public class ServiceUser implements IServices<User> {
    private final UserDao db = new UserDao();

    public int size() {
        return db.size();
    }

    @Override
    public void upload(List<User> lists) {
        lists.forEach(db::add);
    }

    @Override
    public List<User> getAll() {
        return db.getAll();
    }

    @Override
    public User get(String id) throws RuntimeException {
        if (id.length() == 0) {
            throw new RuntimeException("Invalid id");
        }
        User f = db.getById(id);
        if (f == null) {
            throw new RuntimeException("No data in db");
        }
        return f;
    }

    @Override
    public void add(User entity) throws RuntimeException {
        throw new RuntimeException("Create method");
    }

    @Override
    public void delete(String id) throws RuntimeException {
        throw new RuntimeException("Create method");
    }
}
