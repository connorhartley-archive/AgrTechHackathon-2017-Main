package io.pasturesoloutions.greenbox.util;

import com.pi4j.io.serial.SerialDataEvent;

public interface ListenerFunction {

    void post(SerialDataEvent event);

}
