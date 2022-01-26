package com.investing_app.customexceptions;

public class CreateBusinessException extends RuntimeException{
    public CreateBusinessException(String message) {
        super(message);
    }
}
