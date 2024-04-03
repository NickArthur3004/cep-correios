package com.nicolas.cepSpring.enums;

public enum Messages {

    ZIPCODE_NOT_FOUND("Zip code not found!");

    private String message;
    Messages(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

}
