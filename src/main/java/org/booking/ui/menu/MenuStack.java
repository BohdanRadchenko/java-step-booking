package org.booking.ui.menu;

import org.booking.App;
import org.booking.utils.Console;

import java.util.Stack;

public class MenuStack {
    private static final Stack<Menu> MENU_STACK = new Stack<>();

    private static boolean isExit = false;

    private static App app;

    private MenuStack() {
    }

    public static void setApp(App app) {
        MenuStack.app = app;
    }

    public static void add(Menu menu) {
        if (!isEmpty() && menu.equals(MENU_STACK.peek())) return;
        MENU_STACK.push(menu);
    }

    private static void setIsExit(boolean value) {
        isExit = value;
    }

    public static boolean isExit() {
        return isExit;
    }

    private static boolean isEmpty() {
        return MENU_STACK.empty();
    }

    public static void exit() {
        setIsExit(true);
        if (MENU_STACK.peek().statusItem) {
            app.save();
            System.exit(0);
            Console.hide("Menu exit...");
        }
        MENU_STACK.peek().refresh();
    }

    public static void back() {
        MENU_STACK.peek().refresh();
        if (MENU_STACK.size() == 1) {
            refresh();
            return;
        }
        MENU_STACK.pop();
        Console.hide("Menu back...");
        MENU_STACK.peek().run();
    }

    public static void refresh() {
        Console.hide("Menu back...");
        MENU_STACK.peek().run();
    }
}