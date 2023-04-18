package org.booking.ui;

import org.booking.command.Command;
import org.booking.libs.Input;

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
        // TODO: 18.04.2023 System.out to Console method;
        System.out.printf("%d - %s", number, item.getTitle());
    }

    private void displayMenu() {
        items.forEach(this::displayItem);
    }

    private int inputMenu() {
        // TODO: 18.04.2023 System.out to Console method;
        System.out.println("En ter menu number");
        try {
            int n = Input.readInt();
            if (!items.containsKey(n)) {
                throw new RuntimeException("Menu is not exist");
            }
            return n;
        } catch (RuntimeException ex) {
            // TODO: 18.04.2023 System.out to Console method;
            System.out.printf("Parse string error. %s\n", ex.getMessage());
            return inputMenu();
        }
    }

    public void run() {
        displayMenu();
        int n = inputMenu();
        items.get(n).run();
    }
}
