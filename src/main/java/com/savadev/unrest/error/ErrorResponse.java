package com.savadev.unrest.error;

import java.util.ArrayList;
import java.util.Collection;

public class ErrorResponse {

    private final String version;

    private final Body error;

    public ErrorResponse(String version, int code, String message, Collection<Error> errors) {
        this.version = version;
        this.error = new Body(code, message, errors);
    }

    public String getVersion() {
        return version;
    }

    public Body getError() {
        return error;
    }

    protected static final class Body {

        private final int code;

        private final String message;

        private final Collection<Error> errors = new ArrayList<>();

        public Body(int code, String message, Collection<Error> errors) {
            this.code = code;
            this.message = message;
            if (errors != null) {
                this.errors.addAll(errors);
            }
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public Collection<Error> getErrors() {
            return errors;
        }
    }

}
