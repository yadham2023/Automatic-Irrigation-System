package com.bm.irrigationsystem.exception;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public InvalidInputException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public InvalidInputException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
