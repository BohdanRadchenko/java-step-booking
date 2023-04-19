package org.booking.ui.menu;

import org.booking.command.Command;
import org.booking.enums.MenuName;

public class MenuItem {
    private final String title;
    private final Command command;

    public MenuItem(MenuName menuName, Command command) {
        this.title = menuName.title;
        this.command = command;
    }

    public String getTitle() {
        return title;
    }

    public void run() {
        command.execute();
    }
}
