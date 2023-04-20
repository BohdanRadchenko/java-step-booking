package org.booking.controllers;

import org.booking.entity.User;
import org.booking.interfaces.IController;
import org.booking.services.ServiceUser;

public class UserController implements IController {

    private ServiceUser service = new ServiceUser();
    private User user;
    private boolean isAuth = true;

    private void setAuth(boolean set) {
        this.isAuth = set;

    }

    public boolean canAuth() {
        return service.size() > 0;
    }

    public User login(String login, String password) {
        // TODO: 20.04.2023 example code.  Create user service
        this.user = new User(login, password);
        setAuth(true);
        return user;
    }

    public User registration(String login, String password, String firstName, String lastName) {
        // TODO: 20.04.2023 refactor code;
        this.user = new User(login, password, firstName, lastName);
        setAuth(true);
        return user;
    }

    public boolean isAuth() {
        return this.isAuth;
    }

    @Override
    public void load() throws RuntimeException {
        // TODO: 20.04.2023 load data from file
    }

    @Override
    public void save() {
        // TODO: 20.04.2023 save data to file
    }
}
