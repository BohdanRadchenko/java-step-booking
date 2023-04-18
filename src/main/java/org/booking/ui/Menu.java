package org.booking.ui;

import org.booking.command.Command;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<Integer, MenuItem> items;
    private final String title;

    public Menu(String title) {
        this.title = title;
        this.items = new HashMap<>();
    }

    public Menu() {
        this("Main menu");
    }

    public void add(Integer number, String title, Command command) {
        items.put(number, new MenuItem(title, command));
    }

    private void displayItem(Integer number, MenuItem item) {
        //TODO: System.out to Console method;
        System.out.printf("%d - %s", number, item.getTitle());
    }

    private void displayMenu() {
        items.forEach(this::displayItem);
    }

    private void inputMenu() {
        
    }

    public void start() {
        displayMenu();
        inputMenu();
    }
}
