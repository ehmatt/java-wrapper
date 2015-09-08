package com.onepagecrm.models;

public class Email {

    private String type;
    private String value;

    public Email(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public Email() {
    }

    public String getType() {
        return type;
    }

    public Email setType(String type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Email setValue(String value) {
        this.value = value;
        return this;
    }
}
