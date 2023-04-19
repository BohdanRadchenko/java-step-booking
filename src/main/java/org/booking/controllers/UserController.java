package org.booking.controllers;

import org.booking.entity.User;

// TODO: example controller.
public class UserController {

    //    private UserService service = new UserService();
    private User user;
    private boolean isAuth = false;

    public User login(String login, String password) {
        // TODO: 20.04.2023 example code.  must be in user service
        this.user = new User(login, password);
        this.isAuth = true;
        return user;
//        return service.login()
    }

    public boolean isAuth() {
        return this.isAuth;
    }
}
