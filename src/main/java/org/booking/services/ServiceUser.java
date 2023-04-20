package org.booking.services;

import org.booking.dao.UserDao;
import org.booking.entity.User;
import org.booking.interfaces.IServices;

import java.util.List;
import java.util.Optional;

public class ServiceUser implements IServices<User> {
    private final UserDao db = new UserDao();

    public int size() {
        return db.size();
    }

    @Override
    public void upload(List<User> users) {
        if (users.size() == 0) return;
        db.upload(users);
    }

    @Override
    public List<User> getAll() {
        return db.getAll();
    }

    @Override
    public User getById(String id) throws RuntimeException {
        if (id.length() == 0) {
            throw new RuntimeException("Invalid id");
        }
        User f = db.getById(id);
        if (f == null) {
            throw new RuntimeException("No data in db");
        }
        return f;
    }

    public User getByLogin(String login) throws RuntimeException {
        Optional<User> user = getAll()
                .stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get();
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
