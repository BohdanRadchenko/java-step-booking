package org.booking;

import org.booking.helpers.Constants;
import org.booking.utils.Console;
import org.booking.utils.FileWorker;
import org.booking.utils.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static void init() throws RuntimeException {
        Path currentRelativePath = Paths.get("");
        String projectPath = currentRelativePath.toAbsolutePath().toString();
        String dbDir = String.format("%s/%s", projectPath, Constants.dbDir);
        FileWorker.createFolder(dbDir);
        if (FileWorker.exist(dbDir)) {
            throw new RuntimeException("Critical error. db folder is not exist");
        }
    }

    private static void start() {
        Logger.start();
        BookingManager bookingManager = new BookingManager();
        bookingManager.run();
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