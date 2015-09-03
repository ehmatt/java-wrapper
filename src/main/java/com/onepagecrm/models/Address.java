package com.onepagecrm.models;

public class Address {

    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String countryCode;

    public Address() {
    }

    public String getAddress() {
        return address;
    }

    public Address setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Address setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Address setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }
}
