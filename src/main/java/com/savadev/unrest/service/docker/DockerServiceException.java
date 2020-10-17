package com.savadev.unrest.service.docker;

import com.savadev.unrest.error.UnrestException;

public class DockerServiceException extends UnrestException {

    public DockerServiceException(String message) {
        super(message);
    }

    public DockerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
