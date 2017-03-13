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
                " { " +
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
                " { " +
                Arrays.toString(parsedKeys) +
                " }";
    }

    public static Response read(String message) {
        String[] part1 = message.split(" ");
        String state = part1[0];
        String method = part1[1];

        String[] dataParts = {};

        for (int i = 0; i < (part1.length - 4); i++) {
            dataParts[i] = part1[i + 4].replace(",", "");
        }

        return new Response(state, method, dataParts);
    }

    public static class Response {

        private final String state;
        private final String method;
        private final String[] data;

        public Response(String state, String method, String... data) {
            this.state = state;
            this.method = method;
            this.data = data;
        }

        public String getState() {
            return this.state;
        }

        public String getMethod() {
            return this.method;
        }

        public String[] getData() {
            return this.data;
        }

    }

}
