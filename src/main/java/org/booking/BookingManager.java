package org.booking;

import org.booking.controllers.Controller;
import org.booking.ui.menu.AuthMenu;
import org.booking.ui.menu.MainMenu;
import org.booking.ui.menu.Menu;
import org.booking.ui.menu.MenuStack;
import org.booking.utils.Console;
import org.booking.utils.Logger;

public class BookingManager {
    private final Controller controller = new Controller();

    /**
     * read data from files , create init data if db is empty
     */
    private void init() {
        Console.ln();
        String msg = "Loading data...";
        Logger.info(msg);
        Console.hide(msg);
        try {
            controller.load();
        } catch (RuntimeException ex) {
            String exMessage = String.format("Init data error. %s", ex.getMessage());
            Logger.error(msg);
            Console.error(exMessage);
        }
    }

    /**
     * saves all data to files
     */
    private void save() {
        Console.ln();
        String msg = "Data storage...";
        Logger.info(msg);
        Console.hide(msg);
        controller.save();
    }

    private void start() {
        Console.ln();
        Menu authMenu = new AuthMenu(controller);
        Menu mainMenu = new MainMenu(controller);

        authMenu.run();

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