package org.booking;

import org.booking.controllers.Controller;
import org.booking.ui.menu.AuthMenu;
import org.booking.ui.menu.MainMenu;
import org.booking.ui.menu.Menu;
import org.booking.ui.menu.MenuStack;

public class BookingManager {
    private final Controller controller = new Controller();

    /**
     * read data from files , create init data if db is empty
     */
    private void init() {
        // TODO: 17.04.2023 read data from files when file worker created and read
        // TODO: 17.04.2023 load data from files to controllers
    }

    /**
     * saves all data to files
     */
    private void save() {
        // TODO: 17.04.2023 save data from controller to files
    }

    private void start() {
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