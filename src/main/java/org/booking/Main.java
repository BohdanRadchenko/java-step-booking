package org.booking;

import org.booking.helpers.Constants;
import org.booking.utils.Console;
import org.booking.utils.FileWorker;
import org.booking.utils.Logger;

public class Main {
    private static void init() throws RuntimeException {
        FileWorker.createFolder(Constants.DB_PATH);
        if (!FileWorker.exist(Constants.DB_PATH)) {
            throw new RuntimeException("Critical error. db folder is not exist");
        }
    }

    private static void start() {
        Logger.start();
        App app = new App();
        app.run();
        Logger.exit();
    }

    public static void main(String[] args) {
        try {
            init();
            start();
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            Console.error(exception.getMessage());
        }
    }
}