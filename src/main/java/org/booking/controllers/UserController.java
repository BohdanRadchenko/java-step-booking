package org.booking.controllers;

import org.booking.entity.User;
import org.booking.enums.FilePath;
import org.booking.interfaces.IController;
import org.booking.services.ServiceUser;
import org.booking.utils.Console;
import org.booking.utils.FileWorker;
import org.booking.utils.Logger;

import java.io.IOException;
import java.util.List;

public class UserController implements IController {

    private final ServiceUser service = new ServiceUser();
    private User user;
    private boolean isAuth = false;

    public boolean hasUsers() {
        return service.getAll()
                .stream()
                .filter(u -> u.getLogin() != null && u.getPassword() != null)
                .toList()
                .size() != 0;
    }

    private void loginCurrentUser(User user) {
        this.isAuth = true;
        this.user = user;
        try {
            service.add(user);
        } catch (RuntimeException ignored) {
            save();
        }
    }

    private void logoutCurrentUser() {
        this.isAuth = false;
        this.user = null;
    }

    public boolean isEmpty() {
        return service.size() == 0;
    }

    public boolean canAuth() {
        return !isEmpty() && hasUsers();
    }

    public User checkLogin(String login) {
        try {
            return service.getByLogin(login);
        } catch (RuntimeException ignored) {
            return null;
        }
    }

    public User login(String login, String password) {
        try {
            User u = service.getByLogin(login);
            if (u == null) {
                throw new RuntimeException("User not found");
            }
            if (!u.getPassword().equals(password)) {
                throw new RuntimeException("Invalid password");
            }
            loginCurrentUser(u);
            return u;
        } catch (RuntimeException ex) {
            // TODO: 21.04.2023 insert Logger.error(ex.getMessage)
            Console.error("Invalid login or password");
            return null;
        }
    }

    public User registration(String login, String password, String firstName, String lastName) {
        User u = new User(login, password, firstName, lastName);
        service.add(u);
        loginCurrentUser(u);
        return u;
    }

    public boolean logout() {
        // TODO: 21.04.2023 insert Logger. Insert user id
        logoutCurrentUser();
        return true;
    }

    public boolean isAuth() {
        return this.isAuth;
    }

    public User getUser() {
        return user;
    }

    public User getUserByFullName(String fullName) {
        try {
            return service.getByFullName(fullName);
        } catch (RuntimeException ex) {
            Logger.error(ex.getMessage());
            return null;
        }
    }

    public User getOrAddByFull(String first, String last) {
        try {
            return service.getByFullName(String.format("%s %s", first, last));
        } catch (RuntimeException ignored) {
            return service.add(first, last);
        }
    }

    @Override
    public int load() throws RuntimeException {
        FilePath path = FilePath.USER;
        if (!FileWorker.exist(path)) return 0;
        try {
            List<User> entities = FileWorker.readBinary(path);
            service.upload(entities);
            return service.size();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public int save() {
        if (isEmpty()) return 0;
        try {
            FileWorker.writeBinary(FilePath.USER, service.getAll());
            return service.size();
        } catch (IOException ex) {
            Console.error(ex.getMessage());
            return 0;
        }
    }
}
