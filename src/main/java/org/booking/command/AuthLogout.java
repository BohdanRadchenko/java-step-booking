package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.ui.menu.MenuStack;

// TODO: 18.04.2023 Example class. Remove.
public class AuthLogout extends Command {

    public AuthLogout(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new AuthLogout(controller);
    }

    @Override
    public void execute() {
        MenuStack.back();
    }
}
