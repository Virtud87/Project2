package com.investing_app.customexceptions;

public class UsernameOrPasswordIncorrect extends RuntimeException{
    public UsernameOrPasswordIncorrect(String message) {
        super(message);
    }
}
