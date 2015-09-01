package com.onepagecrm.models;

import java.io.Serializable;

public class Phone implements Serializable {

    static final long serialVersionUID = 3927723378236473122L;

    private String type;
    private String number;

    public Phone(String type, String number) {
        this.setType(type);
        this.setNumber(number);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return type + ": \'" + number + "\'";
    }
}
