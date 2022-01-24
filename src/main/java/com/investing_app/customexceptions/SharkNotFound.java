package com.investing_app.customexceptions;

public class SharkNotFound extends RuntimeException{
    public SharkNotFound(String message) {
        super(message);
    }
}
