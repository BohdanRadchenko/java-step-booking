package org.booking.controllers;

import org.booking.entity.User;
import org.booking.enums.FilePath;
import org.booking.interfaces.IController;
import org.booking.services.ServiceUser;
import org.booking.utils.Console;
import org.booking.utils.FileWorker;

import java.io.IOException;
import java.util.List;

public class UserController implements IController {

    private final ServiceUser service = new ServiceUser();
    private User user;
    private boolean isAuth = false;

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
            Console.error("Invalid login or password\n");
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

    @Override
    public void load() throws RuntimeException {
        FilePath path = FilePath.USER;
        if (!FileWorker.exist(path)) return;
        try {
            List<User> entities = FileWorker.readBinary(path);
            service.upload(entities);
        } catch (IOException | ClassNotFoundException ex) {
            Console.error(ex.getMessage());
        }
    }

    @Override
    public void save() {
        if (isEmpty()) return;
        try {
            FileWorker.writeBinary(FilePath.USER, service.getAll());
        } catch (IOException ex) {
            Console.error(ex.getMessage());
        }
    }
}
