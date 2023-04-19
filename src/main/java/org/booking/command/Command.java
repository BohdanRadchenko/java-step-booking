package org.booking.command;

import org.booking.controllers.Controller;

public abstract class Command {
    public final Controller controller;

    public Command(Controller controller) {
        this.controller = controller;
    }

    /**
     * Start commands method
     */
    public abstract void execute();

}
