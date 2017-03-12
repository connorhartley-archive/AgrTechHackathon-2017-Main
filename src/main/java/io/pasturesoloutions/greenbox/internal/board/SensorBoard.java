package io.pasturesoloutions.greenbox.internal.board;

import com.pi4j.io.serial.Baud;
import io.pasturesoloutions.greenbox.GreenBoxAPI;
import io.pasturesoloutions.greenbox.module.Module;
import io.pasturesoloutions.greenbox.socket.SerialSocket;

public class SensorBoard implements Module {

    private final GreenBoxAPI greenBox;
    private final long pingTime = 40;

    private SerialSocket serial;

    public SensorBoard(GreenBoxAPI greenBox) {
        this.greenBox = greenBox;
    }

    @Override
    public void initialize() {
        this.serial = new SerialSocket.Builder()
                .baud(Baud._2400)
                .build("sensor_board");
    }

    @Override
    public void update() {
        if (this.pingTime == this.greenBox.getSchedulerController().getTicks()) {

        }
    }

    @Override
    public void shutdown() {

    }
}
