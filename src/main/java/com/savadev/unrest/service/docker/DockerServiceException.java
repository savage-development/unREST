package com.savadev.unrest.service.docker;

import com.savadev.unrest.error.Error;
import com.savadev.unrest.error.UnrestException;
import org.springframework.http.HttpStatus;

import java.util.Collection;

public class DockerServiceException extends UnrestException {

    public DockerServiceException(HttpStatus status, String message) {
        super(status, message);
    }

    public DockerServiceException(HttpStatus status, String message, Error... errors) {
        super(status, message, errors);
    }

    public DockerServiceException(HttpStatus status, String message, Collection<Error> errors) {
        super(status, message, errors);
    }

    public DockerServiceException(HttpStatus status, String message, Throwable cause, Error... errors) {
        super(status, message, cause, errors);
    }

    public DockerServiceException(HttpStatus status, String message, Throwable cause, Collection<Error> errors) {
        super(status, message, cause, errors);
    }

}
