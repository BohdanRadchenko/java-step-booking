package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.User;
import org.booking.enums.Message;
import org.booking.exceptions.ValidateException;
import org.booking.utils.Console;

// TODO: 18.04.2023 Example class. Remove.
public class AuthLogin extends Command {

    public AuthLogin(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new AuthLogin(controller);
    }

    private String enterLogin() {
        Console.input(Message.USER_ENTER_LOGIN);
        try {
            return Console.readLogin();
        } catch (ValidateException ex) {
            Console.error(ex.getMessage());
            return enterLogin();
        }
    }

    private String enterPassword() {
        Console.input(Message.USER_ENTER_PASSWORD);
        try {
            return Console.readPassword();
        } catch (ValidateException ex) {
            Console.error(ex.getMessage());
            return enterPassword();
        }
    }

    @Override
    public void execute() {
        String login = enterLogin();
        String pass = enterPassword();
        User user = controller.user.login(login, pass);
        if (user != null) {
            Console.accept(String.format("\nWelcome back %s", user.getFullName()));
        }
    }
}
