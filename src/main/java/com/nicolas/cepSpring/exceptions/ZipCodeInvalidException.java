package com.nicolas.cepSpring.exceptions;

import org.springframework.http.HttpStatus;

public class ZipCodeInvalidException extends RuntimeException{

    private final HttpStatus status;

    public ZipCodeInvalidException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
