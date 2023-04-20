package org.booking.ui.menu;

import java.util.Stack;

public class MenuStack {
    private static final Stack<Menu> stack = new Stack<>();

    private static boolean isExit = false;

    private MenuStack() {
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
    }

    public static void back() {
        stack.pop();
        if (!isEmpty()) {
            stack.peek().run();
            return;
        }
        setIsExit(true);
    }
}