package com.codecool.solarwatch.exeptions;

public class InvalidSunTimeFormatException extends RuntimeException {
    public InvalidSunTimeFormatException(String message) {
        super("Couldnt parse Sun set or rise Time Format" + message);
    }
}
