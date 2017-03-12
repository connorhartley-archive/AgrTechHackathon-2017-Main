package io.pasturesoloutions.greenbox.util;

import java.util.Arrays;

public class Interpreter {

    public static String post(String method, String... values) {
        String[] parsedValues = {};

        for (int i = 0; i < values.length; i++) {
            if (i < (values.length - 1)) {
                parsedValues[i] = values[i] + ", ";
            } else {
                parsedValues[i] = values[i];
            }
        }

        return "-> " +
                method +
                " {" +
                Arrays.toString(parsedValues) +
                " }";
    }

    public static String get(String method, String... keys) {
        String[] parsedKeys = {};

        for (int i = 0; i < keys.length; i++) {
            if (i < (keys.length - 1)) {
                parsedKeys[i] = "$" + keys[i] + ", ";
            } else {
                parsedKeys[i] = "$" + keys[i];
            }
        }

        return "<- " +
                method +
                " {" +
                Arrays.toString(parsedKeys) +
                " }";
    }

    public static Response read(String message) {
        // TODO: Inbound reading.
        return null;
    }

    public static class Response {

        public Response(String status, String method, String... data) {

        }

    }

}
