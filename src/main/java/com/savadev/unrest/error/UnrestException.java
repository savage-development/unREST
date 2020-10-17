package com.savadev.unrest.error;

public abstract class UnrestException extends RuntimeException {

    public UnrestException(String message) {
        super(message);
    }

    public UnrestException(String message, Throwable cause) {
        super(message, cause);
    }

}
