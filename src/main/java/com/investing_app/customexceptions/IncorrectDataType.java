package com.investing_app.customexceptions;

public class IncorrectDataType extends RuntimeException{
    public IncorrectDataType(String message) {
        super(message);
    }
}
