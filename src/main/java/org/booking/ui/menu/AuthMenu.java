package org.booking.ui.menu;

import org.booking.command.*;
import org.booking.controllers.Controller;
import org.booking.enums.MenuName;

public class AuthMenu extends Menu {
    public AuthMenu(Controller controller) {
        super("Auth menu", controller, false);
    }

    @Override
    protected void set() {
        if (!controller.user.isEmpty()) {
            add(MenuName.LOGIN, AuthLogin.of(controller));
        }
        add(MenuName.REGISTER, AuthRegistration.of(controller));
        add(MenuName.GUEST, AuthGuest.of(controller), false);
    }
}
