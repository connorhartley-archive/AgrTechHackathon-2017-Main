package io.pasturesoloutions.greenbox;

import com.pi4j.util.Console;
import io.pasturesoloutions.greenbox.scheduler.SchedulerController;

public class GreenBoxAPI {

    private Console console;
    private SchedulerController schedulerController;

    public GreenBoxAPI() {
        this.console = GreenBox.getConsole();
        this.schedulerController = new SchedulerController(this);
    }

    public SchedulerController getSchedulerController() {
        return this.schedulerController;
    }

}
