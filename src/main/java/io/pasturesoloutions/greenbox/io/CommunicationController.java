package io.pasturesoloutions.greenbox.io;

import com.pi4j.io.serial.Serial;
import io.pasturesoloutions.greenbox.RaspbianBox;

import java.util.HashMap;

public class CommunicationController {

    private final RaspbianBox raspbianBox;
    private final HashMap<String, Serial> serialHashMap = new HashMap<>();

    public CommunicationController(RaspbianBox raspbianBox) {
        this.raspbianBox = raspbianBox;
    }



}
