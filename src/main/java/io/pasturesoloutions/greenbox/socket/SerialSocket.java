package io.pasturesoloutions.greenbox.socket;

import com.pi4j.io.serial.*;
import io.pasturesoloutions.greenbox.util.ListenerFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SerialSocket {

    private final String name;
    private final Baud baud;
    private final DataBits dataBits;
    private final Parity parity;
    private final StopBits stopBits;
    private final FlowControl flowControl;
    private final Collection<ListenerFunction> listenerFunctions;

    private Serial serial;
    private SerialConfig serialConfig;

    public SerialSocket(Builder builder) {
        this.name = builder.name;
        this.baud = builder.baud;
        this.dataBits = builder.dataBits;
        this.parity = builder.parity;
        this.stopBits = builder.stopBits;
        this.flowControl = builder.flowControl;
        this.listenerFunctions = builder.listenerFunctions;

        this.serial = SerialFactory.createInstance();
        this.serialConfig = new SerialConfig();

        this.listenerFunctions.forEach(listenerFunction -> this.serial.addListener((SerialDataEventListener) listenerFunction::post));
    }

    public void open() throws IOException, InterruptedException {
        this.serialConfig.device(SerialPort.getDefaultPort())
                .baud(this.baud)
                .dataBits(this.dataBits)
                .parity(this.parity)
                .stopBits(this.stopBits)
                .flowControl(this.flowControl);

        this.serial.open(this.serialConfig);
    }

    public void write(String... strings) throws IOException {
        for (String string : strings) {
            this.serial.write(string);
        }
    }

    public void write(String string) throws IOException {
        this.serial.writeln(string);
    }

    public void close() throws IOException {
        this.serial.close();
    }

    public static class Builder {

        private String name;
        private Baud baud;
        private DataBits dataBits;
        private Parity parity;
        private StopBits stopBits;
        private FlowControl flowControl;

        private final Collection<ListenerFunction> listenerFunctions = new ArrayList<>();

        public Builder() {}

        public Builder baud(Baud baud) {
            this.baud = baud;
            return this;
        }

        public Builder dataBits(DataBits dataBits) {
            this.dataBits = dataBits;
            return this;
        }

        public Builder parity(Parity parity) {
            this.parity = parity;
            return this;
        }

        public Builder stopBits(StopBits stopBits) {
            this.stopBits = stopBits;
            return this;
        }

        public Builder flow(FlowControl flowControl) {
            this.flowControl = flowControl;
            return this;
        }

        public Builder listener(ListenerFunction listenerFunction) {
            this.listenerFunctions.add(listenerFunction);
            return this;
        }

        public SerialSocket build(String name) {
            this.name = name;
            return new SerialSocket(this);
        }

    }
}
