package org.booking;

import org.booking.command.Login;
import org.booking.ui.Menu;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Menu menu = new Menu();
        menu.add(1, "Online", Login.of());
        menu.run();
    }
}