package com.investing_app.customexceptions;

public class ValueTooLarge extends RuntimeException{
    public ValueTooLarge(String message) {
        super(message);
    }
}
