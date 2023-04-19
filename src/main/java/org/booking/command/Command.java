package org.booking.command;

public abstract class Command {
    // TODO: 18.04.2023 Uncomment this code in SB-038
    //    private final Controller controller;
    //    public Command(Controller controller) {
    //        this.controller = controller;
    //    }

    public Command() {
    }

    /**
     * Start commands method
     */
    public abstract void execute();
}
