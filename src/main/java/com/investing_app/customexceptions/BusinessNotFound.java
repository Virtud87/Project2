package com.investing_app.customexceptions;

public class BusinessNotFound extends RuntimeException {
    public BusinessNotFound(String message){
        super(message);
    }
}
