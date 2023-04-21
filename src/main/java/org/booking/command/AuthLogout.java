package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.ui.menu.MenuStack;

public class AuthLogout extends Command {

    public AuthLogout(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new AuthLogout(controller);
    }

    @Override
    public void execute() {
        if (controller.user.logout()) {
            MenuStack.back();
        }
    }
}
