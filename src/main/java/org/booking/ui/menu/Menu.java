package org.booking.ui.menu;

import org.booking.command.Command;
import org.booking.enums.MenuName;
import org.booking.enums.Message;
import org.booking.interfaces.IMenu;
import org.booking.utils.Input;
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
        // TODO: 18.04.2023 System.out to Console method;
        System.out.printf("- %d - %s;\n", number, item.getTitle());
    }

    private void displayMenu() {
        displayLogo();
        this.items.forEach(this::displayItem);
        // TODO: 18.04.2023 System.out to Console method;
        // TODO: 18.04.2023 add help menu item to backlog in mvp2
        // System.out.print("- or Help;\n");
        System.out.print("- or Exit;\n\n");
        System.out.println("-".repeat(100));
    }

    private int enterMenu() {
        // TODO: 18.04.2023 System.out to Console method;
        System.out.println(Message.MENU_ENTER_NUMBER);
        // TODO: 18.04.2023 refactor to Console.readString
        String str = Input.readString();
        try {
            boolean isExit = Parser.parseIsExit(str);
            if (isExit) return exitCode;
            int n = Parser.parseInt(str);
            if (!items.containsKey(n)) {
                throw new RuntimeException(Message.MENU_NOT_EXIST.message);
            }
            return n;
        } catch (RuntimeException ex) {
            // TODO: 18.04.2023 System.out to Console error method;
            System.out.printf("%s\n", ex.getMessage());
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
