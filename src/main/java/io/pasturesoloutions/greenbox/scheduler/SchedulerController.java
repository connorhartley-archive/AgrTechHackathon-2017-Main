package io.pasturesoloutions.greenbox.scheduler;

import io.pasturesoloutions.greenbox.GreenBox;
import io.pasturesoloutions.greenbox.GreenBoxAPI;
import io.pasturesoloutions.greenbox.util.Task;

import java.util.ArrayList;
import java.util.Collection;

public class SchedulerController {

    private final GreenBoxAPI greenBox;

    private Collection<Task> tasks = new ArrayList<>();
    private Thread current;
    private Thread timer;
    private long tick;

    public SchedulerController(GreenBoxAPI greenBox) {
        this.greenBox = greenBox;
    }

    public void initialize() {
        this.current = Thread.currentThread();
        this.timer = new Thread(() -> {
            if (this.tick == Long.MAX_VALUE) {
                GreenBox.getConsole().println("Resetting tick timer.");
                this.tick = 0L;
            } else {
                for (this.tick = 0; this.tick < Long.MAX_VALUE; this.tick++) {
                    for (Task task : this.tasks) {
                        task.execute();
                    }
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public Thread addTask(Task task) {
        this.tasks.add(task);
        return this.timer;
    }

    public long getTicks() {
        return this.tick;
    }

}
