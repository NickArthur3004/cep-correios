package com.nicolas.cepSpring.models;


public class Address {

    private String zipCode;

    public Address() {
    }
    public Address(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
