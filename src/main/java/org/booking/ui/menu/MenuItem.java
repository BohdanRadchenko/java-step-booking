package org.booking.ui.menu;

import org.booking.command.Command;
import org.booking.enums.MenuName;
import org.booking.interfaces.IMenu;
import org.booking.utils.Console;
import org.booking.utils.StringWorker;

import java.util.Objects;

public class MenuItem implements IMenu {
    private final String title;
    private String description;
    private final Command command;
    private final boolean showTitle;

    public MenuItem(MenuName menuName, Command command, String description, boolean showTitle) {
        this.title = menuName.title;
        this.command = command;
        this.showTitle = showTitle;
        this.description = description;
    }

    public MenuItem(MenuName menuName, Command command, boolean showTitle) {
        this(menuName, command, null, showTitle);
    }

    public MenuItem(MenuName menuName, Command command, String description) {
        this(menuName, command, description, true);
    }

    public MenuItem(MenuName menuName, Command command) {
        this(menuName, command, null, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem that = (MenuItem) o;
        return showTitle == that.showTitle
                && Objects.equals(title, that.title)
                && Objects.equals(description, that.description)
                && Objects.equals(command, that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, command, showTitle);
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
        if (showTitle) {
            showTitle();
        }
        if (showTitle && description != null) {
            displayDescription(description);
        }
        Console.ln();
        command.execute();
        if (showTitle) {
            showSubSeparator();
        }
    }
}
