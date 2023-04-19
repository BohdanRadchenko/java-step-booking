package org.booking.ui.menu;

import org.booking.command.Login;
import org.booking.enums.MenuName;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Main menu");
        add(1, MenuName.LOGIN, Login.of());
    }
}
