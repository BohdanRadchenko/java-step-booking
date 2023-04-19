package org.booking.ui.menu;

import org.booking.command.Command;
import org.booking.enums.MenuName;
import org.booking.enums.Message;
import org.booking.interfaces.IMenu;
import org.booking.utils.Console;
import org.booking.utils.Parser;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu implements IMenu {
    private final Map<Integer, MenuItem> items;
    private final String title;

    private boolean isExit = false;

    public Menu(String title) {
        this.title = title;
        this.items = new HashMap<>();
    }

    public void add(Integer number, MenuName menuName, Command command) {
        items.put(number, new MenuItem(menuName, command));
    }

    private void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    private void displayItem(Integer number, MenuItem item) {
        Console.title(String.format("- %d. ", number));
        Console.msg(String.format("%s;\n", item.getTitle()));
    }

    private void displayMenu() {
        displayLogo();
        this.items.forEach(this::displayItem);
        Console.print("- or ");
        Console.msg("Exit\n");
        Console.hide(String.format("%s\n\n", sep2.repeat(repeatSpaceCount)));
    }

    private int enterMenu() {
        Console.input(String.format("%s:\n", Message.MENU_ENTER_NUMBER.message));
        String str = Console.readString();
        try {
            boolean isExit = Parser.parseIsExit(str);
            if (isExit) return exitCode;
            int n = Parser.parseInt(str);
            if (!items.containsKey(n)) {
                throw new RuntimeException(Message.MENU_NOT_EXIST.message);
            }
            return n;
        } catch (RuntimeException ex) {
            Console.error(String.format("Error: %s\n", ex.getMessage()));

            return enterMenu();
        }
    }

    public void run() {
        displayMenu();
        int menuNum = enterMenu();
        if (menuNum == exitCode) {
            setIsExit(true);
            return;
        }
        items.get(menuNum).run();
    }
}
