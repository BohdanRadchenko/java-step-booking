package org.booking;

import org.booking.controllers.Controller;
import org.booking.entity.User;
import org.booking.helpers.PrettyFormat;
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
        Console.ln();
        try {
            controller.load();
        } catch (RuntimeException ex) {
            String exMessage = String.format("Init data error. %s", ex.getMessage());
            Logger.error(msg);
            Console.error(exMessage);
        }
        Logger.separator();
    }

    /**
     * saves all data to files
     */
    public void save() {
        Console.ln();
        String msg = "Data storage...";
        Logger.info(msg);
        Console.hide(msg);
        Console.ln();
        controller.save();
        User user = controller.user.getUser();
        String byeMsg = user != null
                ? String.format("See you %s!", user.getFullName())
                : "See you again!";
        Console.accept(byeMsg);
        Logger.separator();
    }

    private void start() {
        Console.ln();
        Menu authMenu = new AuthMenu(controller);
        Menu mainMenu = new MainMenu(controller);

        authMenu.run();

        while (!MenuStack.isExit()) {
            mainMenu.run();
        }

        Logger.separator();
    }

    public void run() {
        MenuStack.setApp(this);
        init();
        start();
        save();
    }
}