package com.nicolas.cepSpring.exceptions;

import com.nicolas.cepSpring.enums.Messages;

public class ZipCodeNotFoundException extends Exception {
    public ZipCodeNotFoundException() {
        super(Messages.ZIPCODE_NOT_FOUND.getMessage());
    }
}
