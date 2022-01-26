package com.investing_app.customexceptions;

public class UsernameOrPasswordError extends RuntimeException{
    public UsernameOrPasswordError(String message){
        super(message);
    }
}
