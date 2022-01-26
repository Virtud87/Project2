package com.investing_app.customexceptions;

public class PitchNotFound extends RuntimeException{
    public PitchNotFound(String message) {
        super(message);
    }
}
