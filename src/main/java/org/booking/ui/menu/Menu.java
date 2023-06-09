package org.booking.ui.menu;

import org.booking.command.Command;
import org.booking.controllers.Controller;
import org.booking.enums.MenuName;
import org.booking.enums.Message;
import org.booking.interfaces.IMenu;
import org.booking.helpers.Constants;
import org.booking.utils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Menu implements IMenu {
    public boolean statusMenu;
    public boolean statusItem;

    public final Controller controller;
    private final Map<Integer, MenuItem> items;
    private final String title;
    private final boolean hasExit;
    private int count = 0;

    public Menu(String title, Controller controller, boolean hasExit) {
        this.title = title;
        this.controller = controller;
        this.hasExit = hasExit;
        this.items = new HashMap<>();
    }

    public Menu(String title, Controller controller) {
        this(title, controller, true);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusMenu, statusItem, controller, items, title, hasExit, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu that)) return false;
        return Objects.equals(that.title, this.title)
                && that.hasExit == this.hasExit
                && that.count == this.count
                && that.items.equals(this.items);
    }

    public void add(Integer number, MenuItem menuItem) {
        items.put(number, menuItem);
    }

    public void add(Integer number, MenuName menuName, Command command, String description, boolean showTitle) {
        add(number, new MenuItem(menuName, command, description, showTitle));
    }

    public void add(MenuName menuName, Command command, String description, boolean showTitle) {
        count++;
        add(count, new MenuItem(menuName, command, description, showTitle));
    }

    public void add(MenuName menuName, Command command, String description) {
        count++;
        add(count, menuName, command, description, true);
    }

    public void add(MenuName menuName, Command command, boolean showTitle) {
        count++;
        add(count, menuName, command, null, showTitle);
    }

    public void add(MenuName menuName, Command command) {
        count++;
        add(count, menuName, command, null, true);
    }

    protected abstract void set();

    private void reset() {
        count = 0;
        items.clear();
    }

    private void displayItem(Integer number, MenuItem item) {
        Console.title(String.format("- %d. ", number));
        Console.msg(String.format("%s;\n", item.getTitle()));
    }

    private void displayExit() {
        Console.print("- or ");
        Console.msg("Exit\n");
    }

    private void displayMenu() {
        displayLogo();
        this.items.forEach(this::displayItem);
        if (hasExit) {
            displayExit();
        }
        showSubSeparator();
    }

    private int enterMenu() {
        Console.input(Message.MENU_ENTER_NUMBER);
        Logger.out(Message.MENU_ENTER_NUMBER.message);
        String str = Console.readString();
        Logger.input(str);

        if (Parser.parseIsExit(str)) {
            MenuStack.exit();
            return Constants.EXIT_CODE;
        }
        if (Parser.parseIsBack(str)) {
            MenuStack.back();
            return Constants.EXIT_CODE;
        }

        try {
            int n = Parser.parseInt(str);
            if (!items.containsKey(n)) {
                throw new RuntimeException(Message.MENU_NOT_EXIST.message);
            }
            return n;
        } catch (RuntimeException ex) {
            String exMsg = String.format("Enter menu error: %s", ex.getMessage());
            Logger.error(exMsg);
            Console.error(exMsg);
            return enterMenu();
        }
    }

    public void refresh() {
        statusMenu = true;
        statusItem = false;
        reset();
        set();
    }

    private void show() {
        Logger.info(String.format("Show: %s", title));
        Console.ln();
        displayMenu();
        int menuNum = enterMenu();
        statusMenu = false;
        statusItem = true;
        if (menuNum == Constants.EXIT_CODE) return;
        items.get(menuNum).run();
    }

    public void run() {
        MenuStack.add(this);
        refresh();
        show();
        refresh();
    }

    public void run(int i) {
        MenuStack.add(this);
        reset();
        set();
        items.get(i).run();
    }
}
