package io.pasturesoloutions.greenbox;

import com.pi4j.util.Console;

public class RaspbianBox {

    private static Console console;

    public static void main(String[] args) {

        // Create console connection for logging purposes.

        console = new Console();
        console.title("GreenBox % Initialization");
        console.promptForExit();
        console.println("Starting GreenBox - Raspbian");


    }

}
