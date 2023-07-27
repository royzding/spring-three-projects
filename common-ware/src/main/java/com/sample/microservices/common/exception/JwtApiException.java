package com.sample.microservices.common.exception;

import org.springframework.http.HttpStatus;

public class JwtApiException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public JwtApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public JwtApiException(String message, HttpStatus status, String message1) {
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