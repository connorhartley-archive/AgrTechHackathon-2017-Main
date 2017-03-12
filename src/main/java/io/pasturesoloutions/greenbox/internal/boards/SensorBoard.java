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
            this.serial.write(Interpreter.post("mainStatus", "main", "0.0.1", String.valueOf(this.greenBox.getSchedulerController().getTicks())));
            this.serial.write(Interpreter.get("status", "id", "version", "ticks"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if (this.pingTime == this.greenBox.getSchedulerController().getTicks()) {
            try {
                this.serial.write(Interpreter.post("mainStatus", "main", "0.0.1", String.valueOf(this.greenBox.getSchedulerController().getTicks())));
                this.serial.write(Interpreter.get("status", "id", "version", "ticks"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void shutdown() {

    }

    private void readFrom(SerialDataEvent event) {

    }

}
