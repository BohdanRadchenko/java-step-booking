package org.booking.controllers;

import org.booking.entity.User;
import org.booking.interfaces.IController;
import org.booking.services.ServiceUser;
import org.booking.utils.Console;

public class UserController implements IController {

    private final ServiceUser service = new ServiceUser();
    private User user;
    private boolean isAuth = true;

    private void in(User user) {
        this.isAuth = true;
        this.user = user;
    }

    private void out() {
        this.isAuth = false;
        this.user = null;
    }

    public boolean canAuth() {
        return service.size() > 0;
    }

    public User login(String login, String password) {
        try {
            User u = service.getByLogin(login);
            if (!user.getPassword().equals(password)) {
                throw new RuntimeException("Invalid password");
            }
            in(user);
            return user;
        } catch (RuntimeException ex) {
            // TODO: 21.04.2023 insert Logger.error(ex.getMessage)
            Console.error("Invalid login or password");
            return null;
        }
    }

    public User registration(String login, String password, String firstName, String lastName) {
        User u = new User(login, password, firstName, lastName);
        service.add(u);
        in(u);
        // TODO: 21.04.2023 insert Logger.
        return u;
    }

    public boolean logout() {
        out();
        // TODO: 21.04.2023 insert Logger.
        return true;
    }

    public boolean isAuth() {
        return this.isAuth;
    }

    @Override
    public void load() throws RuntimeException {
        // TODO: 20.04.2023 load data from file
        // service.upload();
    }

    @Override
    public void save() {
        // TODO: 20.04.2023 save data to file
    }
}
