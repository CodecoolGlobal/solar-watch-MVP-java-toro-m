package com.codecool.solarwatch.exeptions;

public class InvalidSunTimeFormatException extends RuntimeException {
    public InvalidSunTimeFormatException() {
        super("Couldnt parse Sun set or rise Time Format")  ;
    }
}
