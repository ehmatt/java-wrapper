package com.onepagecrm.models;

import java.io.Serializable;

public class Url implements Serializable {

    private String type;
    private String number;

    public Url(String type, String number) {
        this.setType(type);
        this.setNumber(number);
    }

    public Url() {
    }

    public String getType() {
        return type;
    }

    public Url setType(String type) {
        this.type = type;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Url setNumber(String number) {
        this.number = number;
        return this;
    }
}
