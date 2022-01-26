package com.investing_app.customexceptions;

public class NullValue extends RuntimeException{
    public NullValue(String message) {
        super(message);
    }
}
