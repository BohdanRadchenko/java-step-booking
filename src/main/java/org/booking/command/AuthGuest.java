package org.booking.command;

import org.booking.controllers.Controller;

// TODO: 18.04.2023 Example class. Remove.
public class AuthGuest extends Command {

    public AuthGuest(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new AuthGuest(controller);
    }

    @Override
    public void execute() {
        // Nothing happens
    }
}
