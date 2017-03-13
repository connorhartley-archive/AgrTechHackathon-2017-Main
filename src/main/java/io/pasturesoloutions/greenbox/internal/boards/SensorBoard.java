package io.pasturesoloutions.greenbox.internal.boards;

import com.pi4j.io.serial.*;
import io.pasturesoloutions.greenbox.GreenBoxAPI;
import io.pasturesoloutions.greenbox.util.Interpreter;
import io.pasturesoloutions.greenbox.component.Module;
import io.pasturesoloutions.greenbox.socket.SerialSocket;

import java.io.IOException;

public class SensorBoard implements Module {

    private final GreenBoxAPI greenBox;
    private final long pingTime = 40;

    private SerialSocket serial;
    private boolean close = false;

    public SensorBoard(GreenBoxAPI greenBox) {
        this.greenBox = greenBox;
    }

    @Override
    public void initialize() {
        this.serial = new SerialSocket.Builder()
                .baud(Baud._9600)
                .dataBits(DataBits._8)
                .parity(Parity.NONE)
                .stopBits(StopBits._1)
                .flow(FlowControl.NONE)
                .listener(this::readFrom)
                .build("sensor_board");
        try {
            this.serial.open();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if (this.pingTime == this.greenBox.getSchedulerController().getTicks()) {
            try {
                this.serial.write(Interpreter.post("mainStatus","UP", "main", "0.0.1", String.valueOf(this.greenBox.getSchedulerController().getTicks())));
                this.serial.write(Interpreter.get("status", "state", "id", "version", "ticks"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void shutdown() {
        try {
            this.serial.write(Interpreter.post("mainStatus", "DOWN", "main", "0.0.1", String.valueOf(this.greenBox.getSchedulerController().getTicks())));
            this.serial.write(Interpreter.get("status", "state", "id", "version", "ticks"));
            this.close = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFrom(SerialDataEvent event) {
        try {
            Interpreter.Response message = Interpreter.read(event.getAsciiString());
            if (message.getState() == "->") {
                this.handlePost(message);
            } else {
                this.handleGet(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlePost(Interpreter.Response response) {

    }

    public void handleGet(Interpreter.Response response) {

    }

}
