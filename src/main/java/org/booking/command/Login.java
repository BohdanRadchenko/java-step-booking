package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.utils.Input;

// TODO: 18.04.2023 Example class. Remove.
public class Login extends Command {

    public Login(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new Login(controller);
    }

    @Override
    public void execute() {
        // TODO: 18.04.2023 example code. Remove
        System.out.println("Enter login");
        String login = Input.readString();
        System.out.println("Enter password");
        String pass = Input.readPassword();
        controller.user.login(login, pass);
    }
}
