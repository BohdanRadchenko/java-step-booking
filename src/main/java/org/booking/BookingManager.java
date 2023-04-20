package org.booking;

import org.booking.controllers.Controller;
import org.booking.ui.menu.AuthMenu;
import org.booking.ui.menu.MainMenu;
import org.booking.ui.menu.Menu;
import org.booking.ui.menu.MenuStack;
import org.booking.utils.Console;

public class BookingManager {
    private final Controller controller = new Controller();

    /**
     * read data from files , create init data if db is empty
     */
    private void init() {
        Console.ln();
        Console.hide("Loading data...");
        try {
            controller.load();
        } catch (RuntimeException ex) {
            Console.error(String.format("Init data error. %s", ex.getMessage()));
        }
    }

    /**
     * saves all data to files
     */
    private void save() {
        Console.ln();
        Console.hide("Data storage...");
        controller.save();
    }

    private void start() {
        Console.ln();
        Menu authMenu = new AuthMenu(controller);
        Menu mainMenu = new MainMenu(controller);

        if (controller.user.canAuth()) {
            authMenu.run();
        }
        while (!MenuStack.isExit()) {
            mainMenu.run();
        }
    }

    public void run() {
        init();
        start();
        save();
    }
}