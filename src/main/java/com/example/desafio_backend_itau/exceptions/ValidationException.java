package com.example.desafio_backend_itau.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ValidationException extends RuntimeException{
    private final List<String> errors;
    private final HttpStatus errorCode = HttpStatus.UNPROCESSABLE_ENTITY;

    public ValidationException(List<String> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }
}
