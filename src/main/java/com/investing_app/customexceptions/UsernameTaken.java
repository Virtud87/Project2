package com.investing_app.customexceptions;

public class UsernameTaken extends RuntimeException{
    public UsernameTaken(String message) {
        super(message);
    }
}
