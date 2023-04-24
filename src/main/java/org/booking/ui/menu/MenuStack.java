package org.booking.ui.menu;

import org.booking.App;
import org.booking.utils.Console;

import java.util.Stack;

public class MenuStack {
    private static final Stack<Menu> stack = new Stack<>();

    private static boolean isExit = false;

    private static App app;

    private MenuStack() {
    }

    public static void setApp(App app) {
        MenuStack.app = app;
    }

    public static void add(Menu menu) {
        if (!isEmpty() && menu.equals(stack.peek())) return;
        stack.push(menu);
    }

    private static void setIsExit(boolean value) {
        isExit = value;
    }

    public static boolean isExit() {
        return isExit;
    }

    private static boolean isEmpty() {
        return stack.empty();
    }

    public static void exit() {
        setIsExit(true);
        if (stack.peek().statusItem) {
            app.save();
            System.exit(0);
            Console.hide("Menu exit...");
        }
        stack.peek().refresh();
    }

    public static void back() {
        stack.peek().refresh();
        if (stack.size() == 1) {
            refresh();
            return;
        }
        stack.pop();
        Console.hide("Menu back...");
        stack.peek().run();
    }

    public static void refresh() {
        Console.hide("Menu back...");
        stack.peek().run();
    }
}