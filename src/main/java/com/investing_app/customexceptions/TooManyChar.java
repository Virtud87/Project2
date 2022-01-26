package com.investing_app.customexceptions;

public class TooManyChar extends RuntimeException{
    public TooManyChar(String message) {
        super(message);
    }
}
