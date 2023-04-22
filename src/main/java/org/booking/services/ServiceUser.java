package org.booking.services;

import org.booking.dao.UserDao;
import org.booking.entity.User;
import org.booking.interfaces.IServices;
import org.booking.utils.StringWorker;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ServiceUser implements IServices<User> {
    private final UserDao db = new UserDao();

    @Override
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

    public User getByFullName(String fullName) throws RuntimeException {
        String[] strings = StringWorker.toLowerCase(fullName).split(" ");
        Optional<User> user = getAll()
                .stream()
                .filter(u -> Arrays.stream(strings).anyMatch(StringWorker.toLowerCase(u.getFirstName())::contains))
                .filter(u -> Arrays.stream(strings).anyMatch(StringWorker.toLowerCase(u.getLastName())::contains))
                .findFirst();
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get();
    }

    public User getByLogin(String login) throws RuntimeException {
        Optional<User> user = getAll()
                .stream()
                .filter(u -> u.getLogin() != null)
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get();
    }

    @Override
    public User add(User user) throws RuntimeException {
        if (db.add(user)) {
            return user;
        }
        return null;
    }
    
    public User add(String firstName, String lastName) throws RuntimeException {
        User user = new User(firstName, lastName);
        if (db.add(user)) {
            return user;
        }
        return null;
    }

    @Override
    public void delete(String id) throws RuntimeException {
        throw new RuntimeException("Create method");
    }
}
