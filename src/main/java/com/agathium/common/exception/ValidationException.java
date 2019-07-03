package com.agathium.common.exception;

public class ValidationException extends RuntimeException {

    private Errors errors;

    public ValidationException(Errors errors) {
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
