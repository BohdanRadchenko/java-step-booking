package org.booking.ui.menu;

import org.booking.command.Command;
import org.booking.enums.MenuName;
import org.booking.interfaces.IMenu;
import org.booking.utils.Console;
import org.booking.utils.StringWorker;

public class MenuItem implements IMenu {
    private final String title;
    private String description;
    private final Command command;

    public MenuItem(MenuName menuName, Command command, String description) {
        this.title = menuName.title;
        this.command = command;
        this.description = description;
    }

    public MenuItem(MenuName menuName, Command command) {
        this(menuName, command, null);
    }

    public String getTitle() {
        return title;
    }

    public String getTitleUppercase() {
        return StringWorker.toUpperCase(title);
    }

    private void showTitle() {
        Console.ln();
        displayTitle(getTitleUppercase());
    }

    public void run() {
        showTitle();
        if (description != null) {
            displayDescription(description);
        }
        Console.ln();
        command.execute();
        showSubSeparator();
    }
}
