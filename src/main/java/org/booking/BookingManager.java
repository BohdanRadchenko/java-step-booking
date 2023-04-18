package org.booking;

import org.booking.ui.menu.MainMenu;
import org.booking.ui.menu.Menu;

public class BookingManager {
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
        Menu menu = new MainMenu();
        while (!menu.isExit()) {
            menu.run();
        }
    }

    public void run() {
        init();
        start();
        save();
    }
}