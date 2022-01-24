package com.investing_app.customexceptions;

public class PasswordTooShort extends RuntimeException{
    public PasswordTooShort(String message) {
        super(message);
    }
}
