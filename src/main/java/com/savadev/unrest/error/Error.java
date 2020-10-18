package com.savadev.unrest.error;

public class Error {

    private final String reason;

    private final String message;

    public Error(Exception exception) {
        this(exception.getClass().getSimpleName(), exception.getMessage());
    }

    public Error(String reason, String message) {
        this.reason = reason;
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public String getMessage() {
        return message;
    }

}
