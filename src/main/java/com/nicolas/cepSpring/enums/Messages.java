package com.nicolas.cepSpring.enums;

public enum Messages {

    ZIPCODE_NOT_FOUND("Zip-code not found!"),
    ZIPCODE_INVALID("Zip-code invalid!");

    private String message;
    Messages(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

}
