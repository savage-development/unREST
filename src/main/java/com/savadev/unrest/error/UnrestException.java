package com.savadev.unrest.error;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class UnrestException extends RuntimeException {

    private final HttpStatus status;

    private final List<Error> errors = new ArrayList<>();

    public UnrestException(HttpStatus status, String message) {
        this(status, message, Collections.emptyList());
    }

    public UnrestException(HttpStatus status, String message, Error... errors) {
        this(status, message, errors == null ? Collections.emptyList() : Arrays.asList(errors));
    }

    public UnrestException(HttpStatus status, String message, Collection<Error> errors) {
        super(message);
        this.status = status;
        if (errors != null) {
            this.errors.addAll(errors);
        }
    }

    public UnrestException(HttpStatus status, String message, Throwable cause, Error... errors) {
        this(status, message, cause, errors == null ? Collections.emptyList() : Arrays.asList(errors));
    }

    public UnrestException(HttpStatus status, String message, Throwable cause, Collection<Error> errors) {
        super(message, cause);
        this.status = status;
        if (errors != null) {
            this.errors.addAll(errors);
        }
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<Error> getErrors() {
        return new ArrayList<>(errors);
    }

}
