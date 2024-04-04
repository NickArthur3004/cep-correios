package com.nicolas.cepSpring.exceptions;

import com.nicolas.cepSpring.enums.Messages;
import org.springframework.http.HttpStatus;

import java.net.http.HttpClient;

public class ZipCodeNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public ZipCodeNotFoundException(HttpStatus status) {
        super(Messages.ZIPCODE_NOT_FOUND.getMessage());
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
