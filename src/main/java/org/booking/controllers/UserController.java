package org.booking.controllers;

import org.booking.entity.User;

// TODO: example controller.
public class UserController {

    //    private UserService service = new UserService();
    private User user;
    private boolean isAuth = true;

    private void setAuth(boolean set) {
        this.isAuth = set;

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
}
