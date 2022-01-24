package com.investing_app.customexceptions;

public class UsernameTooShort extends RuntimeException{
    public UsernameTooShort(String message) {
        super(message);
    }
}
